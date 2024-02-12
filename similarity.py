# -------------------------------------------------------------------------
# AUTHOR: Mohamed Yousuf Abdul Rahman
# FILENAME: similarity.py
# SPECIFICATION: This program finds cosine similarity and find the highest similarities between the given documents.
# FOR: CS 5990 (Advanced Data Mining) - Assignment #1
# TIME SPENT: I think I spent about an hour and half.
# -----------------------------------------------------------*/

# Importing some Python libraries
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity

# Defining the documents
doc1 = "soccer is my favorite sport"
doc2 = "I like sports and my favorite one is soccer"
doc3 = "support soccer at the olympic games"
doc4 = "I do like soccer, my favorite sport in the olympic games"

# Use the following words as terms to create your document-term matrix
# [soccer, favorite, sport, like, one, support, olympic, games]
# --> Add your Python code here
terms = ["soccer", "favorite", "sport", "sports","like", "one", "support", "olympic", "games"]
stopWords = ["is", "i", "the", "my", "do", "at", "and", "in", "and"]
## this will contain all the matrix for each document
documentMatrix = []
documents = [doc1, doc2, doc3, doc4]

for document in documents:
    currentDocument = document.split()
    currentDocumentCountMatrix = {}
    ## fill the matrix with zeros
    for term in terms:
        currentDocumentCountMatrix[term] = 0
    ## iterate through the text of the current document
    ## and populate the matrix by using the key as the term and increament the value
    for term in currentDocument:
        term = term.replace(",", "")
        term = term.lower()
        if term not in stopWords:
            ## increament the key in the map
            currentDocumentCountMatrix[term] = currentDocumentCountMatrix[term] + 1
    ## lets append the map into the documentMatrix
    ## we do not care about the keys we only care about the values
    documentMatrix.append(list(currentDocumentCountMatrix.values()))

# Compare the pairwise cosine similarities and store the highest one
# Use cosine_similarity([X], [Y]) to calculate the similarities between 2 vectors only
# Use cosine_similarity([X, Y, Z]) to calculate the pairwise similarities between multiple vectors
# --> Add your Python code here
cosineSimilarityByDocumentCombination = {}
for index, row in enumerate(documentMatrix):
    ## iterate through the rest of the rows in the matrix
    ## then compute the consine similari
    subIndex = index + 1
    while subIndex < len(documentMatrix):
        documentCombination = "Doc_" + str((index + 1)) + " - Doc_" + str(subIndex + 1)
        documentCombinationCosineSimilarity = list(cosine_similarity([np.array(documentMatrix[index])],[np.array(documentMatrix[subIndex])]))[0][0]
        subIndex = subIndex + 1
        ## append to the final map
        cosineSimilarityByDocumentCombination[documentCombination] = documentCombinationCosineSimilarity
# Print the highest cosine similarity following the information below
# The most similar documents are: doc1 and doc2 with cosine similarity = x
# --> Add your Python code here

## print all the combination
print("Document cosine similarity by document combination")
for docCombination, cosineSimilarity in cosineSimilarityByDocumentCombination.items():
    print("Document Combination : " + str(docCombination), "Cosine similarity : " + str(cosineSimilarity))

## sort the dictionary by decreasing order
sortedCosineSimlarity = sorted(cosineSimilarityByDocumentCombination.items(), key=lambda x: x[1], reverse=True)


print("The most similar documents are: " + str(sortedCosineSimlarity[0][0].replace("-", "and"))  + ", cosine similarity is " + str(sortedCosineSimlarity[0][1]))