#Python 2.7.3
import re
import os
import collections
import time

class Index:

    def __init__(self,path):
        self._path = path
        ## document id map
        self._documentIdMap = {}
        # inverted index dictionary
        self._indexedMap = {}
        # document id starts with 1000 and increament by 1
        self._currentDocumentId : int = 1000
        # building the index
        self.buildIndex()

    def buildIndex(self):
    #function to read documents from collection, tokenize and build the index with tokens
    #index should also contain positional information of the terms in the document --- term: [(ID1,[pos1,pos2,..]), (ID2, [pos1,pos2,…]),….]
    #use unique document IDs
        startTime = time.time()
        self._process(self._path)
        endTime = time.time()
        print("Index Built in ", (endTime - startTime) , " seconds")

    def and_query(self, query_terms):
    #function for identifying relevant docs using the index
        startTime = time.time()
        mergedList = self._indexedMap.get(query_terms[0])
        emptyList = []
        for query in query_terms[1:]:
                mergedList = self._mergeList(mergedList, self._getIndex(query))

        print("Resutls for the Query : ", " AND ".join(query_terms) )
        print("Total Docs retrieved ", len(mergedList))

        for collection in mergedList:
            print(self._getCollectionName(collection["collectionId"]))

        endTime = time.time()
        print("Retrieved in ", (endTime - startTime) , " seconds")

    def print_dict(self):
    #function to print the terms and posting list in the index
        for word, collection in self._indexedMap.items():
                print("Word : ", word, " Document Id and Postining List : ", collection)

    def print_doc_list(self):
    # function to print the documents and their document id
        for documentName, documentId in self._documentIdMap.items():
                print("Doc ID: ", documentId , " ==> ", documentName)

    # merge the list which has common document id
    def _mergeList(self, list1, list2):
        if list1 is None or list2 is None:
                return []
        mergedList = []
        index1 = 0
        index2 = 0

        while index1 < len(list1) and index2 < len(list2):
                if list1[index1]["collectionId"] == list2[index2]["collectionId"]:
                    ## found common document
                    mergedList.append({"collectionId": list1[index1]["collectionId"]})
                    # incr. pointer
                    index1 = index1 + 1
                    index2 = index2 + 1
                elif list1[index1]["collectionId"] > list2[index2]["collectionId"]:
                    # incr. index2
                    index2 = index2 + 1
                else:
                    # incr. index1
                    index1 = index1 + 1
        return mergedList

    # this private method file reads the collections list, and returns the files as dictionary
    def _process(self, path):
        collectionList = os.listdir(path)
        ## read each file and append the name of the file, and the content as a list to the map
        for collection in collectionList:
            # read the current file
            filePath = str(path) + str("//") + str(collection)
            file = open(filePath, "r")
            ## position reset for newly read document
            position = 0
            for lineIndex, line in enumerate(file):
                contentList =  line.split()
                # tokenize each items and append the words, frequency, posting list into the indexedMap
                for contentIndex, content in enumerate(contentList):
                        position = position + 1
                        currentTerm = self._normalizeToken(content)
                        # append new element if the current term does not exists
                        if currentTerm not in self._indexedMap.keys():
                            self._indexedMap[currentTerm] = [{"collectionId" : self._getDocumentId(str(collection)), "positionSet" : set(str(position))}]
                        else:
                            ## get the current list
                            currentTermIndexList = self._indexedMap.get(currentTerm)
                            present = False
                            ## check if the current collection is present in the list
                            for index, currentTermIndexItem  in enumerate(currentTermIndexList):
                                if currentTermIndexItem.get("collectionId") == self._getDocumentId(collection):
                                    currentTermIndexItemPositionSet: set= currentTermIndexItem.get("positionSet")
                                    currentTermIndexItemPositionSet.add(str(position))
                                    currentTermIndexItem["positionSet"] = currentTermIndexItemPositionSet
                                    currentTermIndexList[index] = currentTermIndexItem
                                    present = True
                            if not present:
                                   currentTermIndexList.append({"collectionId" : self._getDocumentId(collection), "positionSet" : set(str(position))})
                            self._indexedMap[currentTerm] = currentTermIndexList
            # close the current file
            file.close()

    def _normalizeToken(self, token: str) -> str:
        punctuations = [".",",","?",";","!",":","'","(",")","[","]","\"", "-","_","/","@","{","}","*","$"]
        for punctuation in punctuations:
                token = token.replace(punctuation, "")
        return token.lower()

    # this method returns id of a document
    def _getDocumentId(self, documentName : str):
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

