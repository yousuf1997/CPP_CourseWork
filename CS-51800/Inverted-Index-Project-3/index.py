# Python 3.0
import math
import re
import os
import collections
import time
import lucene
from org.apache.lucene.document import Document, Field, FieldType
from java.nio.file import Paths
from org.apache.lucene.analysis.standard import StandardAnalyzer
from org.apache.lucene.queryparser.classic import QueryParser
from org.apache.lucene.document import Document, Field, FieldType
from org.apache.lucene.index import DirectoryReader, IndexReader, PostingsEnum, IndexOptions, IndexWriter,IndexWriter,IndexWriterConfig,Term
from org.apache.lucene.store import MMapDirectory
from org.apache.lucene.search import IndexSearcher, TermQuery
from org.apache.lucene.util import BytesRef, BytesRefIterator

class Index:
    def __init__(self, path):
        self._path = path
        self._documentId = 1000
        # initialize lucene related objects
        lucene.initVM(vmargs=['-Djava.awt.headless=true'])
        # analyzer
        self._analyzer = StandardAnalyzer()
        # index storage
        self._indexDirectory = MMapDirectory(Paths.get("index"))
        # config for the library
        self._config = IndexWriterConfig(self._analyzer)
        # writer
        self._writer = IndexWriter(self._indexDirectory, self._config)

    def buildIndex(self):
        startTime = time.time()
        self._process()
        endTime = time.time()
        print("Index built in ", (endTime - startTime), " seconds")

    def rocchio(self, query_terms, pos_feedback, neg_feedback, alpha, beta, gamma):
        # function to implement rocchio algorithm
        # pos_feedback - documents deemed to be relevant by the user
        # neg_feedback - documents deemed to be non-relevant by the user
        # Return the new query  terms and their weights
        startTime = time.time()
        relevantDocumentsCentroidVector = self._calculateCenteriod(pos_feedback)
        nonRelevantDocumentsCentroidVector = self._calculateCenteriod(neg_feedback)
        queryVector = self._transformQueryVectorTotfidf(self._convertQueryToVector(query_terms))
        # print("Query Vector", queryVector.values())
        # print("relevant vectors ", pos_feedback)
        # print("non relevant vectors ", neg_feedback)
        ## perform roccio calculation
        rocchioVector = self._performVectorArthimetic(self._multiplyVectorByX(list(queryVector.values()), alpha), self._multiplyVectorByX(relevantDocumentsCentroidVector, beta), "ADD")
        rocchioVector = self._performVectorArthimetic(rocchioVector, self._multiplyVectorByX(nonRelevantDocumentsCentroidVector, gamma), "SUB")
        ## remove the negative terms

        print("{:<20} {:<15}".format('Term', 'Weight'))
        index = 0
        while index < len(queryVector):
            val = round(rocchioVector[index], 2)
            if(val > 0):
                ##print("[Term :", list(queryVector.keys())[index], ", Weight :", val, "]")
                print("{:<20} {:<15}".format(list(queryVector.keys())[index],val))
            index = index + 1
        endTime = time.time()
        print("Calculated Roccio in ", (endTime - startTime), " seconds")

    def query(self, query_terms, k):
        # function for exact top K retrieval using cosine similarity
        # Returns at the minimum the document names of the top K documents ordered in decreasing order of similarity score
        startTime = time.time()
        queryVector = self._convertQueryToVector(query_terms)
        docIds = []
        docVectors = {}
        computedSimilarity = []
        topKDocInformation = {}
        ## get all docIds for the query
        for term in queryVector.keys():
            docIds = docIds + self._getAllDocIdsWithTheTerm(term)

        ## build vector for each docIds
        for docId in docIds:
            docVectors[docId] = self._buildVectorForDocument(docId, queryVector)

        ## transform the doc vectors to tf-idf values
        for docId, vector in docVectors.items():
            docVectors[docId] = self._transformVectorTotfidf(vector, docId)

        ## transform query vector to tf-idf
        queryVector = self._transformQueryVectorTotfidf(queryVector)

        ## calculate cosine sims for query against each doc
        for docId, vector in docVectors.items():
            computedSimilarity.append({"docId": docId, "cosineSimValue": self._computeCosine(list(queryVector.values()), list(vector.values()))})

        ## sort the computedSimilarity
        computedSimilarity = sorted(computedSimilarity, key=lambda docIndex: docIndex["cosineSimValue"],reverse=True)

        ## print top k
        index = 0
        print("{:<20} {:<15}".format('Document Name', 'Doc Id'))
        while index < k and k < len(computedSimilarity):
            ##print("["+ str(computedSimilarity[index]["docId"]) +"]", self._getDocumentName(computedSimilarity[index]["docId"]))
            print("{:<20} {:<15}".format(self._getDocumentName(computedSimilarity[index]["docId"]), str(computedSimilarity[index]["docId"])))
            topKDocInformation[computedSimilarity[index]["docId"]] = docVectors[computedSimilarity[index]["docId"]]
            index = index + 1
        endTime = time.time()
        print("Query Fetched in ", (endTime - startTime), " seconds")
        return topKDocInformation

    def print_doc_list(self):
        startTime = time.time()
        dict = {}
        totalDocs = self._indexReader.numDocs()
        docId = 0
        while docId < totalDocs:
                ## get all terms in this doc
                ## obtain the terms in the document
                termsList = self._indexReader.getTermVector(docId, "content")
                ## traverse the terms
                if termsList != None:
                    terms = termsList.iterator()
                    ## first postion
                    for term in BytesRefIterator.cast_(terms):
                        dpEnum = terms.postings(None)
                        dpEnum.nextDoc()
                        freq = dpEnum.freq()
                        pos = []
                        for i in range(freq):
                            pos.append(dpEnum.nextPosition())
                        if term.utf8ToString() in dict.keys():
                            postingDic = dict[term.utf8ToString()]
                            if docId in postingDic.keys():
                                dict[term.utf8ToString()][docId] = dict[term.utf8ToString()][docId] + pos
                            else:
                                dict[term.utf8ToString()][docId] = pos
                        else:
                            dict[term.utf8ToString()] = {}
                            dict[term.utf8ToString()][docId] = pos
                docId = docId + 1
        print("{:<30} {:<50}".format('Term', 'Posting List'))
        for key in dict.keys():
            print("{:<30} {:<50}".format(key,  str(dict[key])))
        endTime = time.time()
        print("Printed in  ", (endTime - startTime), " seconds")

    def print_dict(self):
        startTime = time.time()
        totalDoc = self._indexReader.numDocs()
        index = 0
        print("{:<20} {:<15}".format('Document Name', 'Doc Id'))
        while index < totalDoc:
            print("{:<20} {:<15}".format(self._getDocumentName(index), index))
            index = index + 1
        endTime = time.time()
        print("Printed in  ", (endTime - startTime), " seconds")
    ## this method prompts the user to input queries
    ## and performs the roccio stuff
    def queryPrompt(self):
        userQuery = ""
        while userQuery != "done":
            userQuery = input("Please enter the query : ")
            maxResult = input("Max results : ")
            queryResult = self.query(userQuery, int(maxResult))
            relevant = input("What is relevant (seperate the docId by comma): ")
            nonRelevant = input("What is non relevant (seperate the docId by comma): ")
            self._calculateRoccio(userQuery, queryResult, relevant.split(','), nonRelevant.split(','))
            userQuery = ""
            maxResult = 0

    # helpers
    def _calculateRoccio(self, userQuery, queryResult, relevant, nonRelevant):
            relevantVectors = []
            nonRelevantVectors = []
            queryVector = self._convertQueryToVector(userQuery)
            print("User Relevant ", relevant)
            print("User Irelevant", nonRelevant)
            print("Query Vector ", queryVector.keys())
            for docId in relevant:
                ##relevantVectors.append(list(queryResult[int(docId)].values()))
                termFrequencies = []
                for query in queryVector.keys():
                    listOfDocs = self._getAllDocIdsWithTheTerm(query)
                    if int(docId) in listOfDocs:
                        termFrequencies.append(self._tfidf(self._idf(query), self._getTermFrequencyInDocument(query, docId)))
                    else:
                        termFrequencies.append(0)
                relevantVectors.append(termFrequencies)

            # for docId in nonRelevant:
            #     nonRelevantVectors.append(list(queryResult[int(docId)].values()))
            for docId in nonRelevant:
                ##relevantVectors.append(list(queryResult[int(docId)].values()))
                termFrequencies = []
                for query in queryVector.keys():
                    listOfDocs = self._getAllDocIdsWithTheTerm(query)
                    if int(docId) in listOfDocs:
                        termFrequencies.append(self._tfidf(self._idf(query), self._getTermFrequencyInDocument(query, docId)))
                    else:
                        termFrequencies.append(0)
                nonRelevantVectors.append(termFrequencies)
            ## calculate roccio
            self.rocchio(userQuery, relevantVectors, nonRelevantVectors, 1, 0.75, 0.15)

    ## building the inverted index using lucene library
    def _process(self):
        documentList = os.listdir(self._path)
        ## this method returns the document frequency for the term (df)  documentList = os.listdir(self._path)
        ## fields
        documentNameField = self._getDocumentNameField()
        documentIdField = self._getDocumentIdField()
        documentContentField = self._getContentField()
        ## read each file
        for document in documentList:
                documentPath = str(self._path) + str("//") + str(document)
                documentReader = open(documentPath, "r")
                contents = documentReader.read()
                documentReader.close()
                luceneDocument = Document()
                luceneDocument.add(Field("documentName", document, documentNameField))
                luceneDocument.add(Field("documentId", self._documentId, documentIdField))
                luceneDocument.add(Field("content", contents, documentContentField))
                ## appending the document to the inverted index
                self._writer.addDocument(luceneDocument)
                ## increment the document id
                self._documentId = self._documentId + 1

        # searcher
        self._indexReader = DirectoryReader.open(self._writer)
        self._searcher = IndexSearcher(self._indexReader)

    def _getDocumentFrequency(self, term):
        return self._indexReader.docFreq(self._getTerm("content", self._normalizeToken(term)))

    ## this method returns the term frequency in the current document
    def _getTermFrequencyInDocument(self, termText, docId):
        ## obtain the terms in the document
        termsList = self._indexReader.getTermVector(int(docId), "content")
        ## traverse the terms
        terms = termsList.iterator()
        for term in BytesRefIterator.cast_(terms):
            dpEnum = terms.postings(None)
            dpEnum.nextDoc()
            freq = dpEnum.freq()
            if term.utf8ToString() == self._normalizeToken(termText):
                return freq
        return 0

    def _normalizeToken(self, token: str) -> str:
        punctuations = [".", ",", "?", ";", "!", ":", "'", "(", ")", "[", "]", "\"", "-", "_", "/", "@", "{", "}", "*","$"]
        for punctuation in punctuations:
            token = token.replace(punctuation, "")
        return token.lower()

    def _getTerm(self, field, term):
        return Term(field, term)

    # convert the query in to vector
    def _convertQueryToVector(self, query):
            queryTerms = query.split(' ')
            queryVector = {}
            for term in queryTerms:
                    normTerm = self._normalizeToken(term)
                    ## check if its already present in the vector
                    if normTerm in queryVector.keys():
                        # increase the term frequency by 1
                        queryVector[normTerm] = queryVector[normTerm] + 1
                    else:
                        queryVector[normTerm] = 1
            return queryVector

    ## this method build vector for given document in order of query vector, and terms
    def _buildVectorForDocument(self, docId, queryVector):
            documentVector = {}
            for term in queryVector.keys():
                ## calcualte the term frequency for the term
                documentVector[term] = self._getTermFrequencyInDocument(term, docId)
                ## print("Process ", term, self._getTermFrequencyInDocument(term, docId))
            return documentVector

    # following method calculates the idf
    def _idf(self, term):
        return math.log((self._indexReader.numDocs() + 1) /(self._getDocumentFrequency(term) + 1), 10)

    # following method calculates the tf weight
    def _wtd(self, term, docId):
        tf = self._getTermFrequencyInDocument(term, docId)
        if tf == 0:
            return 0
        return 1 + math.log(float(tf), 10)

    # following method calculates the tf-idf
    def _tfidf(self, idf, wtd):
        return idf * wtd

    # this method transforms the vector to tf-idf for the given doc
    def _transformVectorTotfidf(self, vector, docId):
        for term in vector.keys():
            vector[term] = self._tfidf(self._idf(term), self._wtd(term, docId))
        return vector

    # this method transforms the query vector to tf-idf values
    def _transformQueryVectorTotfidf(self, queryVector):
        for term, tf in queryVector.items():
            queryVector[term] = self._tfidf(self._idf(term), 1 + math.log(tf, 10))
        return queryVector

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
        ## cosine similarity
        return crossProduct / (math.sqrt(vec1Squared) * math.sqrt(vec2Squared))

    ## the following method will return all the docIds that contains the given term
    def _getAllDocIdsWithTheTerm(self, term) -> list:
        ## get the maximum number of docs that can be returned by the indexer
        termQuery = TermQuery(Term("content", term))
        hits = self._searcher.search(termQuery, self._indexReader.numDocs()).scoreDocs
        docIds = []
        i = 0
        while i < len(hits):
            ## append all the doc ids returned by the system
            docIds.append(hits[i].doc)
            i = i + 1
        return docIds

    ## helper method to fectch name of the document
    def _getDocumentName(self, docId):
        return self._searcher.doc(docId).get("documentName")

    ## helper method to calculate the centeriods
    ## takes list of list of term frequencies
    ## helper for roccios algoritms
    def _calculateCenteriod(self, tfs):
            finalVector = [0] * (len(tfs[0]))
            index = 0
            while index < len(tfs):
                tfIndex = 0
                while tfIndex < len(tfs[index]):
                    finalVector[tfIndex] = finalVector[tfIndex] + tfs[index][tfIndex]
                    tfIndex = tfIndex + 1
                index = index + 1
            ## divide each coordinates by size of tfs
            index = 0
            while index < len(finalVector):
                finalVector[index] = (finalVector[index]) / len(tfs)
                index = index + 1
            return finalVector

    ## helper method to perform vector arithmetic
    def _performVectorArthimetic(self,vector1, vector2, operation):
        vecIndex1 = 0
        vecIndex2 = 0
        result = []

        while vecIndex1 < len(vector1) and vecIndex2 < len(vector2):
            result.append(self._operation(vector1[vecIndex1], vector2[vecIndex2], operation))
            vecIndex1 = vecIndex1 + 1
            vecIndex2 = vecIndex2 + 1
        #
        # ## left overs
        # while vecIndex1 < len(vector1):
        #     result.append(vector1[vecIndex1])
        #     vecIndex1 = vecIndex1 + 1
        #
        # while vecIndex2 < len(vector2):
        #     result.append(vector2[vecIndex2])
        #     vecIndex2 = vecIndex2 + 1

        return result

    def _operation(self, num1, num2, operation):
        if operation == "ADD":
            return num1 + num2
        if operation == "SUB":
            return num1 - num2

    def _multiplyVectorByX(self, vector, x):
        index = 0
        while index < len(vector):
            vector[index] = vector[index] * x
            index = index + 1
        return vector

    ## document field setup for the index
    def _getDocumentNameField(self):
        f = FieldType()
        f.setStored(True)
        f.setTokenized(True)
        f.setIndexOptions(IndexOptions.DOCS)
        return f

    def _getDocumentIdField(self):
        f = FieldType()
        f.setStored(True)
        f.setTokenized(True)
        f.setIndexOptions(IndexOptions.DOCS)
        return f

    def _getContentField(self):
        f = FieldType()
        f.setStored(True)
        f.setTokenized(True)
        f.setStoreTermVectors(True)
        f.setStoreTermVectorPositions(True)
        f.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS)
        return f