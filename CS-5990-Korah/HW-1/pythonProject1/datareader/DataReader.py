## Reader class reads data to contruct graph, and it returns edges as list
from datareader.DataEdge import DataEdge

class DataReader:
    def __init__(self):
        pass

    def readData(self, filePath, dataType) -> list[DataEdge]:
        if (filePath == None or filePath == ' '):
            return None
        ## open the file
        file = open(filePath, "r")
        ## all the lines as a graph
        lines = file.readlines()
        ## result
        edges: list[DataEdge] = []
        if (dataType == "Amazon"):
            for index, line in enumerate(lines):
                if index > 3:
                    lineData =  line.split('\t')
                    ## vertex, edge
                    edge = DataEdge(lineData[0].strip(), lineData[1].strip())
                    edges.append(edge)
        return edges