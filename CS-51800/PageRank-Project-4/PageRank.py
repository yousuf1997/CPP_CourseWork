#Python 3.0
import re
import os
import collections
import time
#import other modules as needed
class PageRank:
    def __init__(self):
        self._adjListMap = {}

    def pagerank(self, input_file):
        self._parseTheGraph(input_file)
        pageRankVector = []
        ## for purpose of converge
        prevPageRankVector = []

        ## builds page initial page rank vector
        for i in range(self._totalNumberPages):
            pageRankVector.append(float(1.0/self._totalNumberPages))

        while prevPageRankVector != pageRankVector:
            prevPageRankVector = pageRankVector
            ## new vector will be obtained in the following
            pageRankVector = self._multiplyMatrixWithPagRankVector(pageRankVector)

        print("Page Ranked", pageRankVector)

        ## build new map and sort it eventually
        pageRankMap = {}

        for i in range(self._totalNumberPages):
            pageRankMap[i] = pageRankVector[i]
        print(pageRankMap)

    ''' This method reads the file, parse the data into adjacency list'''
    def _parseTheGraph(self, filePath):
        file = open(filePath, "r")
        for lineIndex, content in enumerate(file):
            content = content.rstrip()
            cList = content.split()
            if lineIndex == 0:
                self._totalNumberPages = int(cList[0])
            elif lineIndex == 1:
                self._totalNumberOfLinks = int(cList[0])
            else:
                ## the second index contains the destination
                if int(cList[0]) not in self._adjListMap.keys():
                    ## create new key and empty
                    self._adjListMap[int(cList[0])] = []
                ## append the value into the key
                self._adjListMap[int(cList[0])].append(int(cList[1]))
        print("Adjacency list :", self._adjListMap)
        print("Total Number Pages :", self._totalNumberPages)
        print("Total Number of links :", self._totalNumberOfLinks)
        ## build matrix
        self._buildMatrix(self._totalNumberPages, self._totalNumberPages, 0.0)
        ## plot the points
        self._plotMatrixWithOutGoingPath(self._adjListMap)

    '''This method builds the matrix with default value'''
    def _buildMatrix(self, M:int, N:int, placeHolder):
        matrix = []
        for x in range(M):
            row = []
            for y in range(N):
                row.append(placeHolder)
            matrix.append(row)
        self._matrix = matrix

    def printMatrix(self):
        for i in range(len(self._matrix)):
            self._documentRowFormatter(self._matrix[i], str(i))

    '''This method prints the row in pretty format'''
    def _documentRowFormatter(self, docsList, index:str):
            formatLiteral = " | "
            for i in range(len(docsList)):
                formatLiteral = formatLiteral + "{:<5} "
            print(index, formatLiteral.format(*docsList) + "|")

    '''This method sets value into cells with values from the adjacencyList'''
    def _plotMatrixWithOutGoingPath(self, adjacencyListDict):
        ## assume the structure of the adjcencyList is
        ## {source: [1,1,1]}
        for source, adjList in adjacencyListDict.items():
            ## iterate the list
            for node in adjList:
                self._matrix[int(source)][int(node)] = 1.0
        print("        [Matrix with outgoing path]")
        self.printMatrix()
        self._transformMatrixToTransitionState(adjacencyListDict)
        print("        [Matrix with transition probability]")
        self.printMatrix()

    '''This method the matrix into transition probability state'''
    def _transformMatrixToTransitionState(self, adjacencyListDict):
        for source, adjList in adjacencyListDict.items():
            transitionProbability = round(1.0/float(len(adjList)), 2)
            for node in adjList:
                self._matrix[int(source)][int(node)] = transitionProbability

    def _multiplyMatrixWithPagRankVector(self, pageRankVector) -> list:
        resultantVector = []
        index = 0
        length = len(pageRankVector)
        while index < length:
            ## get the current row from matrix
            row = self._matrix[index]
            ## dot product of row and column
            sum = 0
            for k in range(length):
                sum = sum + round(float(row[k] * pageRankVector[k]), 2)
            resultantVector.append(sum)
            index = index + 1
        return resultantVector