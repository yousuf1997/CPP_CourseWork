# Python 2.7.3
import math
import operator
import os
import random
import time

class Index:

    def __init__(self, path):
        self._path = path
        ## document id map
        self._documentIdMap = {}
        # inverted index dictionary
        self._indexedMap = {}
        # document id starts with 1000 and increament by 1
        self._currentDocumentId: int = 1000
        # total documents
        self._totalDocuments = 0
        # r documents for champion list
        self._r_documents_range = 10
        # number of words in each document
        self._wordsCountMap = {}
        # initialize the stop word list
        self._initializeStopWords()
        # building the index
        self.buildIndex()
        # computer leaders for cluster pruning
        self._computeLeadersAndFollowers()

    def buildIndex(self):
        # function to read documents from collection, tokenize and build the index with tokens
        # index should also contain positional information of the terms in the document --- term: [(ID1,[pos1,pos2,..]), (ID2, [pos1,pos2,…]),….]
        # use unique document IDs
        startTime = time.time()
        self._process(self._path)
        endTime = time.time()
        print("Index Built in ", float(endTime - startTime), " seconds")

    def exact_query(self, query_terms, k):
        # #function for exact top K retrieval (method 1)
        # # Returns at the minimum the document names of the top K documents ordered in decreasing order of similarity score
        startTime = time.time()
        self._processQuery(query_terms, k, False, False)
        endTime = time.time()
        print("Exact Query processed in ", float(endTime - startTime), " seconds")

    def inexact_query_champion(self, query_terms, k):
        startTime = time.time()
        self._processQuery(query_terms, k, True, False)
        # time to process the exact query using the index
        endTime = time.time()
        print("In exact Query with champion list processed in ", float(endTime - startTime), " seconds")

    def inexact_query_index_elimination(self, query_terms, k):
        # #function for exact top K retrieval using index elimination (method 3)
        # #Returns at the minimum the document names of the top K documents ordered in decreasing order of similarity score
        startTime = time.time()
        self._processQuery(query_terms, k, False, True)
        # time to process the exact query using the index
        endTime = time.time()
        print("In exact Query with Index elimination processed in ", float(endTime - startTime), " seconds")

    def inexact_query_cluster_pruning(self, query_terms, k):
        # #function for exact top K retrieval using cluster pruning (method 4)
        # #Returns at the minimum the document names of the top K documents ordered in decreasing order of similarity score
        startTime = time.time()
        tempVector = self._convertQueryIntoVector(query_terms)
        queryVector = self._buildNewVectorWithValues()
        leaderAndQueryCosineSimList = []
        for key, value in tempVector.items():
            ## check if the key is in the queryVector
            if key in queryVector.keys():
                ## set the tf-idfk value
                queryVector[key] = value
        ## compute cosine similarity between leaders
        for leader in self._leadersList:
            tempLeaderVector = self._leaderVectors[leader]
            cosineSim = self._computeCosine(list(queryVector.values()), list(tempLeaderVector.values()))
            leaderAndQueryCosineSimList.append({"leaderCollectionId" : leader, "cosineSim":cosineSim})
        ## sort
        leaderAndQueryCosineSimList = sorted(leaderAndQueryCosineSimList, key=operator.itemgetter("cosineSim"), reverse=True)
        ## travese the leaders and compute consine sims for each of its followers
        queryAndFollowerCosineList = []
        result = []
        for leader in leaderAndQueryCosineSimList:
            followersVectors = self._leaderAndFollowerMap[leader["leaderCollectionId"]]["followers"]
            for fVector in followersVectors:
                ## consie sim between the query and follower
                cosineSim = self._computeCosine(list(queryVector.values()), list(fVector["vector"].values()))
                queryAndFollowerCosineList.append({
                    "followerCollectionId" : fVector["followerCollectionId"],
                    "cosineSim" : cosineSim
                })
            ## sort the queryAndFollowerCosineList
            queryAndFollowerCosineList = sorted(queryAndFollowerCosineList, key=operator.itemgetter("cosineSim"), reverse=True)
            if len(queryAndFollowerCosineList) > 0:
                ind = 0
                while ind < len(queryAndFollowerCosineList) and ind < k:
                    print(self._getCollectionName(queryAndFollowerCosineList[ind]["followerCollectionId"]))
                    ind = ind + 1
                endTime = time.time()
                print("In exact Query with cluster pruning processed in ", float(endTime - startTime), " seconds")
                return result
        endTime = time.time()
        print("No Match --> In exact Query with cluster pruning processed in ", float(endTime - startTime), " seconds")
        ## nothing found
        return result

    def print_dict(self):
        # function to print the terms and posting list in the index
        for word, collection in self._indexedMap.items():
            print("Word : ", word, " Document Id and Postining List : ", collection)

    def print_doc_list(self):
        # function to print the documents and their document id
        for documentName, documentId in self._documentIdMap.items():
            print("Doc ID: ", documentId, " ==> ", documentName)

    def _processQuery(self, query_terms, k,championListMode=False, indexElimination=False):
        queryVector = self._convertQueryIntoVector(query_terms)
        documentVector = self._constructVectorsForDocumentsFromTheQueryVector(queryVector, championListMode,indexElimination)
        cosScoreVecForDocs = {}
        # for each document compute sine similarity with query vector
        for documentName, vector in documentVector.items():
            cosScoreVecForDocs[documentName] = self._computeCosine(list(queryVector.values()), list(vector.values()))
        ## sort by dec. order
        cosScoreVecForDocs = sorted(cosScoreVecForDocs.items(), key=operator.itemgetter(1), reverse=True)
        ## print only top k
        for i in range(k):
            print(cosScoreVecForDocs[i][0])

    # this private method file reads the collections list, and returns the files as dictionary
    def _process(self, path):
        collectionList = os.listdir(path)
        self._totalDocuments = len(collectionList)
        ## read each file and append the name of the file, and the content as a list to the map
        for collection in collectionList:
            # read the current file
            filePath = str(path) + str("//") + str(collection)
            file = open(filePath, "r")
            ## position reset for newly read document
            position = 0
            for lineIndex, line in enumerate(file):
                contentList = line.split()
                # tokenize each items and append the words, frequency, posting list into the indexedMap
                for contentIndex, content in enumerate(contentList):
                    # word count for the collection
                    self._appendWordCountToDocument(str(self._getDocumentId(collection)))
                    position = position + 1
                    currentTerm = self._normalizeToken(content)
                    # make sure the current term is not in the stop list
                    if currentTerm not in self._stopWordList:
                        # append new element if the current term does not exist
                        if currentTerm not in self._indexedMap.keys():
                            self._indexedMap[currentTerm] = {
                                    "inverseDocumentFrequency" : self._calculateInverseDocumentFrequency(1, self._totalDocuments),
                                    "documentList" : [
                                        {
                                            "collectionId": self._getDocumentId(str(collection)),
                                            "frequencyWeight": self._calculateLogFrequencyWeight(1),
                                            "positionSet": set(str(position))
                                        }
                                    ],
                                    "championList" : [{
                                        "collectionId": self._getDocumentId(str(collection)),
                                        "frequencyWeight": self._calculateLogFrequencyWeight(1),
                                        "positionSet": set(str(position))
                                    }]
                                }
                        else:
                            ## get the current list
                            currentTermDocumentList = self._indexedMap.get(currentTerm).get("documentList")
                            present = False
                            ## this document will be used to recalculate the champion list
                            modifiedDocument = {}
                            ## check if the current collection is present in the list
                            for dIndex, document in enumerate(currentTermDocumentList):
                                    ## check if the collection is already present
                                    if document.get("collectionId") == self._getDocumentId(collection):
                                        present = True
                                        currentTermIndexItemPositionSet: set= document.get("positionSet")
                                        currentTermIndexItemPositionSet.add(str(lineIndex))
                                        document["positionSet"] = currentTermIndexItemPositionSet
                                        document["frequencyWeight"] = self._calculateLogFrequencyWeight(len(currentTermIndexItemPositionSet))
                                        currentTermDocumentList[dIndex] = document
                                        modifiedDocument = document

                            if not present:
                                    ## add new collection into the document List
                                    currentTermDocumentList.append(
                                        {
                                            "collectionId": self._getDocumentId(str(collection)),
                                            "frequencyWeight": self._calculateLogFrequencyWeight(1),
                                            "positionSet": set(str(position))
                                        }
                                    )
                            ## calculate inverse document frequency
                            self._indexedMap[currentTerm]["inverseDocumentFrequency"] = self._calculateInverseDocumentFrequency(len(currentTermDocumentList), self._totalDocuments)
                            self._indexedMap[currentTerm]["documentList"] = currentTermDocumentList
                            # compute champion list
                            self._indexedMap[currentTerm]["championList"] = self._computeChampionList(currentTerm, modifiedDocument)
            # close the current file
            file.close()

    def _normalizeToken(self, token: str) -> str:
        punctuations = [".", ",", "?", ";", "!", ":", "'", "(", ")", "[", "]", "\"", "-", "_", "/", "@", "{", "}", "*",
                        "$"]
        for punctuation in punctuations:
            token = token.replace(punctuation, "")
        return token.lower()

    # this method returns id of a document
    def _getDocumentId(self, documentName: str):
        # check if aleready present in the map
        if documentName not in self._documentIdMap.keys():
            # add new document
            self._documentIdMap[documentName] = self._currentDocumentId
            # increament the id
            self._currentDocumentId = self._currentDocumentId + 1
        # return id of the document
        return self._documentIdMap.get(documentName)

    def _getCollectionName(self, collectionId):
        for key, value in self._documentIdMap.items():
            if value == collectionId:
                return key
        return ""

    # this private method returns the posting list from the index
    def _getIndex(self, word) -> list:
        emptyList = []
        for key, val in self._indexedMap.items():
            if key == word:
                return val
        return emptyList

    # this method calculates the log weighting of a term
    def _calculateLogFrequencyWeight(self, termFrequency: int):
        if termFrequency != 0:
            return 1 + math.log(termFrequency, 10)
        return 0

    # this method calculates the inverse document frequency
    def _calculateInverseDocumentFrequency(self, documentFrequency: int, totalDocuments: int):
            if documentFrequency != 0 and totalDocuments != 0:
                return math.log(totalDocuments / documentFrequency, 10)

    # this method appends the word count to the specific document
    def _appendWordCountToDocument(self, documentId : str):
            for key, value in self._wordsCountMap.items():
                    if key == documentId:
                        self._wordsCountMap[documentId] = self._wordsCountMap[documentId] + 1
                        return
            self._wordsCountMap[documentId] = 1

    # this method computes the consine simalarity
    def _computeCosine(self, vector1: list, vector2: list):
            vec1len = len(vector1)
            vec2len = len(vector2)
            ## make sure both of the vectors are same size
            ## otherwise append zeros if they are not same
            if vec1len != vec2len:
                    if vec1len > vec2len:
                        for i in range(vec1len - vec2len):
                                vector2.append(0)
                    else:
                        for i in range(vec2len - vec1len):
                                vector1.append(0)
            ## compute the cross product
            ## bottom portion (length)
            vec1Squared = 0
            vec2Squared = 0
            crossProduct = 0
            squareRootAndMultiplied = 0
            for index in range(len(vector1)):
                    crossProduct = crossProduct + vector1[index] * vector2[index]
                    vec1Squared = vec1Squared + math.pow(vector1[index], 2)
                    vec2Squared = vec2Squared + math.pow(vector2[index], 2)
            ## cosine simularity
            return crossProduct / (math.sqrt(vec1Squared) * math.sqrt(vec2Squared))

    # this method calculates the tf_idf weight
    def _calculateTF_IDFweight(self, termFrequency, documentFrequency, totalDocuments):
            return self._calculateLogFrequencyWeight(termFrequency, 0) * self._calculateInverseDocumentFrequency(documentFrequency, totalDocuments)

    # this method extracts the value from the tupe
    def _getList(self, tupleList):
        result = []
        for i in tupleList:
            result.append(i[1])
        return  result

    # this method returns top 3 highest weighted champion list
    def _updateChampionList(self, currentTermDocumentList: list):
        currentTermDocumentList.sort(key=operator.itemgetter("frequencyWeight"))
        return currentTermDocumentList[:3]

    # this method converts the query into vector
    def _convertQueryIntoVector(self, query: str):
        # index for the query
        queryIndex = {}
        # split the query into array
        queryList = query.split(' ')
        for word in queryList:
            word = self._normalizeToken(word)
            dfFromIndex = self._getDfFromIndex(word)
            if word not in queryIndex.keys():
                queryIndex[word] = {
                    ## since we consider the query as document, then we need to add 1 to the existing total document
                    ## df frequency as needs to add 1 since query itself a document
                    "idf" : self._calculateInverseDocumentFrequency(dfFromIndex + 1, self._totalDocuments + 1),
                    "wordFreqWeight": self._calculateLogFrequencyWeight(1),
                    ## since this is only one document, going to only care about the frequency of the term, not the posting list
                    "tf" : 1
                }
            else:
                ## since the idf will remain same because the total number of document is one
                ## we only care about the wordweight freq
                ## get the tf and append 1
                queryIndex[word]["tf"] = queryIndex[word]["tf"] + 1
                ## re calcualte the word frequency
                queryIndex[word]["wordFreqWeight"] = self._calculateLogFrequencyWeight(queryIndex[word]["tf"])
        ## build the vector with term as a key
        ## the value should be wf * idf
        queryVector = {}
        for term, data in queryIndex.items():
            queryVector[term] = data["wordFreqWeight"] * data["idf"]
        return queryVector

    def _constructVectorsForDocumentsFromTheQueryVector(self, queryVector, championMode=False, indexElimination=False):
        documentVector = {}
        listType = "documentList" if championMode else "championList"
        indexEliminationList = []
        for term in queryVector.keys():
            # get the document from the index
            if term in self._indexedMap:
                    documentIndex = self._indexedMap[term]
                    if not indexElimination:
                        for documentItem in documentIndex[listType]:
                            documentName = self._getCollectionName(documentItem["collectionId"])
                            ## check if the collection is present int he documentVector
                            if documentName not in documentVector:
                                ## build document with vector
                                documentVector[documentName] = self._buildNewVector(queryVector)
                            ## get the document
                            ## calculate the tf-idf for the term
                            ## document frequency
                            docFreWeight = self._calculateLogFrequencyWeight(len(documentItem["positionSet"]))
                            docIdf = self._calculateInverseDocumentFrequency(len(documentIndex["documentList"]) + 1, self._totalDocuments + 1)
                            documentVector[documentName][term] = docFreWeight * docIdf
                    else:
                        ## index elimination
                        indexEliminationList.append({"term":term, "docData" : documentIndex})

        if indexElimination:
            # sort the indexEliminationMap by the idf
            # we only need the half of the list so return half of the list
            indexEliminationList = sorted(indexEliminationList, key=lambda docIndex: docIndex["docData"]["inverseDocumentFrequency"], reverse=True)[:int(len(indexEliminationList)/2)]
            ## now build the vector for the above index eliminated list
            for index in indexEliminationList:
                for documentItem in index["docData"]["documentList"]:
                    documentName = self._getCollectionName(documentItem["collectionId"])
                    ## check if the collection is present int he documentVector
                    if documentName not in documentVector:
                        ## build document with vector
                        documentVector[documentName] = self._buildNewVector(queryVector)
                    ## get the document
                    ## calculate the tf-idf for the term
                    ## document frequency
                    docFreWeight = self._calculateLogFrequencyWeight(len(documentItem["positionSet"]))
                    docIdf = self._calculateInverseDocumentFrequency(len(index["docData"]["documentList"]) + 1, self._totalDocuments + 1)
                    documentVector[documentName][term] = docFreWeight * docIdf
        return documentVector

    # this method finds the df from the index
    def _getDfFromIndex(self, word):
        if word in self._indexedMap.keys():
            return len(self._indexedMap[word]["documentList"])
        return 0

    def _buildNewVector(self, queryVector):
        vector = {}
        for term in queryVector.keys():
            vector[term] = 0
        return  vector

    ## this method computes the champion list for the term
    def _computeChampionList(self, currentTerm, modifiedDocument):
        ## the modified document will be empty if the document that was processed does not exists in the document list
        ## hence new document with single posting list will contain smallest value so we just return the existing championList
        currentChampionList = self._indexedMap[currentTerm]["championList"]
        if modifiedDocument == {}:
            return currentChampionList
        ## add the modified document into the existing championList
        ## perform sort, and return only the top r documents
        currentChampionList.append(modifiedDocument)
        ## sort the list by length of the posting list for each document since it denotes the tf and return only the r documents
        ## sorted list will be in descending order, the first being the doc with highest term frequency
        return sorted(currentChampionList, key=lambda doc: len(doc["positionSet"]), reverse=True)[:self._r_documents_range]

    ## this method initilizes the stop word list
    def _initializeStopWords(self):
        self._stopWordList = ["a","an","and","are","as","at","be","by","for","from","has","he","in","is","it","its",
                                "of","on","that","the","to","was","were","will","with"]

    def _computeLeadersAndFollowers(self):
        startTime = time.time()
        ## this list will contain all the document ids of leaders
        self._leadersList = []
        ## leader vectors
        self._leaderVectors = {}
        ## sqrt of N
        nRange = int(math.sqrt(len(self._documentIdMap)))
        ## pick random leaders
        documentIdList = list(self._documentIdMap.values())
        ## pick random nRange leaders
        while len(self._leadersList) < nRange:
            rIndex = random.randint(0, nRange)
            if documentIdList[rIndex] not in self._leadersList:
                self._leadersList.append(documentIdList[rIndex])

        ## construct vectors for the leaders
        for collectionIdLeader in self._leadersList:
            vector = self._buildNewVectorWithValues()
            for key in vector.keys():
                ## find the tf weight of the term in the index map
                vector[key] = self._findtfWeightWithidf(key, collectionIdLeader)
            ## add the leader vector in the leaderVectors list
            self._leaderVectors[collectionIdLeader] = vector

        ## construct vectors for followers
        tempFollowerVector = {}
        for collection in documentIdList:
            if collection not in self._leadersList:
                    vector = self._buildNewVectorWithValues()
                    for key in vector.keys():
                        ## find the tf weight of the term in the index map
                        vector[key] = self._findtfWeightWithidf(key, collection)
                    tempFollowerVector[collection] = vector

        ## compute cosine similarity between leaders and followers --> each leader should have the top sqrt(n) followers
        self._leaderAndFollowerMap = {}
        for leader in self._leadersList:
                leaderVector = list(self._leaderVectors[leader].values())
                self._leaderAndFollowerMap[leader] = {
                    "leaderCollectionId" : leader,
                    "followers" : []
                }
                ## for each follower compute consine
                for key, value in tempFollowerVector.items():
                    self._leaderAndFollowerMap[leader]["followers"].append({
                        "followerCollectionId":key,
                        "vector": value,
                        "cosineSim" : self._computeCosine(leaderVector, list(value.values()))
                    })
        ## for each leader store the top nRange followers , the make sure they are in descending order
        for leader in self._leadersList:
                self._leaderAndFollowerMap[leader]["followers"] = sorted(self._leaderAndFollowerMap[leader]["followers"], key=operator.itemgetter("cosineSim"), reverse=True)
                ## store only top nRange Followers
                self._leaderAndFollowerMap[leader]["followers"] = self._leaderAndFollowerMap[leader]["followers"][:nRange]
        # time to process the exact query using the index
        endTime = time.time()
        print("The process for building leader and follower took ", (endTime - startTime), " seconds")


    # helper method to build new vector
    def _buildNewVectorWithValues(self):
            vector = {}
            for term in self._indexedMap.keys():
                vector[term] = 0
            return vector

    def _findtfWeightWithidf(self, term, collectionId):
        docIndex = self._indexedMap[term]
        ## check if the collectionId is present in this term then return term frequency weight
        for doc in docIndex["documentList"]:
            if doc["collectionId"] == collectionId:
                return doc["frequencyWeight"] * docIndex["inverseDocumentFrequency"]
        return 0