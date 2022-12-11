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
        iterationCount = 0
        ## builds page initial page rank vector
        for i in range(self._totalNumberPages):
            pageRankVector.append(float(1.0/self._totalNumberPages))

        ## transform the matrix with teleport
        self._transformMatrixIntoStochasticMatrix()

        print("Iterating until converges")
        ## perform multiplication until it converges
        while prevPageRankVector != pageRankVector:
            prevPageRankVector = pageRankVector
            ## new vector will be obtained in the following
            pageRankVector = self._multiplyMatrixWithPagRankVector(pageRankVector)
            print("--------------------------------------------------------------")
            print("Iteration : ", iterationCount)
            self.printMatrix()
            iterationCount = iterationCount + 1

        print("Total Iteration performed ", iterationCount)

        ## build new map and sort it eventually
        pageRankMap = {}

        for i in range(self._totalNumberPages):
            pageRankMap[i] = pageRankVector[i]

        ## sort the dictionary
        pageRankMap = dict(sorted(pageRankMap.items(), key=lambda item: item[1], reverse=True))
        print(pageRankMap)

        ## write the page ranked into the out.txt
        file = open("out.txt", "w")
        for page, rank in pageRankMap.items():
            file.write("Page : " + str(page) + ", Rank : " + str(rank) + "\n")
        file.close()

    '''
        The following method transform the current matrix into the stochastic matrices with teleport
        A = p * M + (1 - p) * [1/N]
    '''
    def _transformMatrixIntoStochasticMatrix(self):
        ## default teleport
        teleport_a = 0.15

        ## build matrix for 1/N , and sets placeholder 1/total_pages
        matrixB = self._buildMatrix(self._totalNumberPages, self._totalNumberPages, float(1.0/self._totalNumberPages))

        ## multipy original matrix, and matrixB with teleport
        self._matrix = self._multiplyMatrixWithX(self._matrix, teleport_a)
        matrixB = self._multiplyMatrixWithX(matrixB, 1 - teleport_a)

        ## add two matrix as per the formula
        self._matrix = self._addMatrixOfEqualSize(self._matrix, matrixB)


    '''The following method multiplies matrix with x'''
    def _multiplyMatrixWithX(self, matrix, x):
        for m in range(len(matrix)):
            for k in range(len(matrix[0])):
                matrix[m][k] = matrix[m][k] * x
        return matrix

    '''The following method adds two equal size matrix'''
    def _addMatrixOfEqualSize(self,matrix1, matrix2):
        rMatrix = []
        for k in range(len(matrix1)):
            row = []
            for m in range(len(matrix1[0])):
                row.append(float(matrix1[k][m] + matrix2[k][m]))
            rMatrix.append(row)
        return rMatrix

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
        self._matrix = self._buildMatrix(self._totalNumberPages, self._totalNumberPages, 0.0)
        ## plot the points
        self._plotMatrixWithOutGoingPath(self._adjListMap)
        file.close()

    '''This method builds the matrix with default value'''
    def _buildMatrix(self, M:int, N:int, placeHolder):
        matrix = []
        for x in range(M):
            row = []
            for y in range(N):
                row.append(placeHolder)
            matrix.append(row)
        return matrix

    def printMatrix(self):
        for i in range(len(self._matrix)):
            self._documentRowFormatter(self._matrix[i], str(i))

    '''This method prints the row in pretty format'''
    def _documentRowFormatter(self, docsList, index:str):
            formatLiteral = " | "
            for i in range(len(docsList)):
                formatLiteral = formatLiteral + "{:<7} "
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

    '''
        This method multiplies the original matrix with pagerankvector
        It is being used to until the pagerankVector converges to some point
    '''
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