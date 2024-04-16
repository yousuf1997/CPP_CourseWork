#-------------------------------------------------------------------------
# AUTHOR: Mohamed Yousuf Abdul Rahman
# FILENAME: knn.py
# SPECIFICATION: Read the "weather_training.csv" file and use the trained model to classify each instance in the "weather_test.csv" file.
# FOR: CS 5990- Assignment #3
# TIME SPENT: 2hr
#-----------------------------------------------------------*/

import pandas as pd
import numpy as np
from sklearn.model_selection import GridSearchCV
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import LabelEncoder, StandardScaler  
from sklearn.metrics import accuracy_score

# 11 classes after discretization
classes = [i for i in range(-22, 40, 6)]

# Defining the hyperparameter values of KNN
k_values = [i for i in range(1, 20)]
p_values = [1, 2]
w_values = ['uniform', 'distance']


# Function to read data and convert values to float
def read_data(filename):
    df = pd.read_csv(filename)
    X = np.array(df.values)[:, 1:-1].astype('f')  # Features, convert to float
    y = np.array(df.values)[:, -1].astype('f')  # Labels, convert to float
    return X, y


# Read the training and test data
X_training, y_training = read_data('weather_training.csv')
X_test, y_test = read_data('weather_test.csv')

# Initialize variables to store the highest accuracy and corresponding hyperparameters
highest_accuracy = 0
best_parameters = {}

# loop over the hyperparameter values (k, p, and w) for KNN
for k in k_values:
    for p in p_values:
        for w in w_values:
            # fit the KNN to the training data
            clf = KNeighborsClassifier(n_neighbors=k, p=p, weights=w)
            clf.fit(X_training, y_training)

            # make predictions for each test sample and compute accuracy
            correct_predictions = 0
            for x_testSample, y_testSample in zip(X_test, y_test):
                prediction = clf.predict([x_testSample])[0]
                if abs(prediction - y_testSample) <= 0.15 * abs(y_testSample):
                    correct_predictions += 1

            # calculate accuracy
            accuracy = correct_predictions / len(y_test)

            # check if the calculated accuracy is higher than the previously highest accuracy
            if accuracy > highest_accuracy:
                highest_accuracy = accuracy
                best_parameters = {'k': k, 'p': p, 'w': w}
                print(
                    "Highest KNN accuracy so far: {:.2f}, Parameters: k={}, p={}, w='{}'".format(highest_accuracy, k, p,
                                                                                                 w))
