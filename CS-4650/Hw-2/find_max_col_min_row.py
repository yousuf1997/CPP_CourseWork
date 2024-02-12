
import re
import json
import sys

from mrjob.job import MRJob

class MaxColMinRow(MRJob):
    
    def mapper(self, _, line):
        values = line.strip().split(",")
        # first value is the column
        # second value is the row
        # third value is cell value
        # return the column
        yield values[0], int(values[2])
        ## return the row 
        yield values[1], int(values[2])
        
    def reducer(self, key, values):
        ## the key is either the column or row 
        ## we have to check if its column or row
        if self.isRow(key):
            ## find the min 
            lowestValue = sys.maxsize
            for value in values:
                lowestValue = min(lowestValue, value)
            yield key, lowestValue    
        if  self.isColumn(key):
            ## find the max 
            highestValue = 0
            for value in values:
                highestValue = max(highestValue, value)
            yield key, highestValue       
        
   ## helper method to determine the colum 
    def isColumn(self, value):
        columnsRegex = re.compile(r"[A-J]")
        return re.match(columnsRegex, value)
    
    ## helper method to determine the row 
    def isRow(self, value):
        rowsRegex = re.compile(r"[K-T]")
        return re.match(rowsRegex, value)
    
if __name__ == '__main__':
    MaxColMinRow.run()
