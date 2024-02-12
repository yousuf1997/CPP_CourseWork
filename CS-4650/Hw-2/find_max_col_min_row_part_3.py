
import re
import json
import sys

from mrjob.job import MRJob

class MaxColMinRow3(MRJob):
    
    def mapper(self, _, line):
        values = line.strip().split(",")
        # first value is the column
        # second value is the row
        # third value is cell value
        # return the column as key , object of value, one row name
        yield values[0] , {"value" : int(values[2]), "example": values[1]}
        ## return the row as key , object of value, one row name
        yield values[1] , {"value" : int(values[2]), "example": values[0]}
        
    def reducer(self, key, values):
        ## the key is either the column or row 
        ## we have to check if its column or row
        if self.isRow(key):
            ## find the min 
            lowestValue = sys.maxsize
            exampleColumn = []
            for row in values:
                lowestValue = min(lowestValue, row['value'])
                exampleColumn.append(row['example'])
                    
            yield key, {"value": lowestValue, "examples": list(set(exampleColumn))}
           
        if  self.isColumn(key):
            ## find the max 
            highestValue = 0
            exampleRow = []
            for column in values:
                highestValue = max(highestValue, column['value'])
                exampleRow.append(column['example'])
                                
            yield key, {"value": highestValue, "examples": list(set(exampleRow))}
        
   ## helper method to determine the colum 
    def isColumn(self, value):
        columnsRegex = re.compile(r"[A-J]")
        return re.match(columnsRegex, value)
    
    ## helper method to determine the row 
    def isRow(self, value):
        rowsRegex = re.compile(r"[K-T]")
        return re.match(rowsRegex, value)
    
if __name__ == '__main__':
    MaxColMinRow3.run()
