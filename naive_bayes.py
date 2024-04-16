#-------------------------------------------------------------------------
# AUTHOR: Mohamed Yousuf Abdul Rahman
# FILENAME: naive_bayes.py
# SPECIFICATION: This function computes the accuracy achieved by applying naive Bayes to weather data.
# FOR: CS 5990- Assignment #3
# TIME SPENT: 2hr
#-----------------------------------------------------------*/

import pandas as pd
import numpy as np
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score

# define classes after discretization
classes = [i for i in range(-22, 41, 6)]

# read training and test data
def read_data(filename):
    df = pd.read_csv(filename)
    X = df.iloc[:, 1:].values
    y = df.iloc[:, -1].values
    y_discretized = np.digitize(y, classes) - 1  # Subtract 1 to start from index 0
    return X, y_discretized

X_training, y_training_discretized = read_data('weather_training.csv')
X_test, y_test_discretized = read_data('weather_test.csv')

# fit the Naive Bayes model to the training data
clf = GaussianNB()
clf.fit(X_training, y_training_discretized)

# make predictions
predictions = clf.predict(X_test)

# calculate accuracy
def calculate_accuracy(predictions, y_test_discretized):
    accurate_count = sum(abs(pred - actual) <= 0.15 * actual for pred, actual in zip(predictions, y_test_discretized))
    total_count = len(y_test_discretized)
    accuracy = accurate_count / total_count
    return accuracy

accuracy = calculate_accuracy(predictions, y_test_discretized)

# print accuracy
print("Naive Bayes accuracy: {:.2f}".format(accuracy))
