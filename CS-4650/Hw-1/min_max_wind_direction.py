
import re
import json
import sys

from mrjob.job import MRJob

QUALITY_RE = re.compile(r"[01459]")

class MinMaxWindirection(MRJob):
    
    def mapper(self, _, line):
        val = line.strip()
        (windDirection, temp, q) = (val[60:63], val[87:92], val[63:64])
        if (windDirection != "999" and temp != "+9999" and re.match(QUALITY_RE, q)):
            ## key is windirection 
            yield int(windDirection), int(temp)

    def reducer(self, key, values):
        ## higher number as a starting point 
        minTemperature = sys.maxsize
        ## lower number as a starting point
        maxTemperature = -1 * sys.maxsize
        ## count
        counter = 0
        for data in values:
            minTemperature = min(minTemperature, data)
            maxTemperature = max(maxTemperature, data)
            counter = counter + 1
        yield key, {"low": minTemperature, "high" : maxTemperature, "count": counter}

if __name__ == '__main__':
    MinMaxWindirection.run()
