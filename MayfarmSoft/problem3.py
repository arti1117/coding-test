# Clustering Problem

import re
import pandas as pd
from sklearn.datasets import make_blobs
import numpy as np
import matplotlib.pyplot as plt

# 두 변수의 거리를 계산할 수 있는 모든 것은 clustering이 가능하다
# 3차원 이상의 고차원 feature도 클러스터링 가능 다만 시각화에서는 reshape가 필요하다고 느낀다

# one-hot encoding -> pca -> clustering?

# read input data
df = pd.read_excel('input/attach_03.xls')

# delete duplication and get rid of nan data
pure_df = df.drop_duplicates(subset=['has출연자', 'has연출감독']).dropna()

genres = []
for i in pure_df['has장르']:
    for j in i.split(','):
        if j not in genres:
            genres.append(j)

actors = []
for i in pure_df['has출연자']:
    for j in i.split(','):
        if j not in actors:
            actors.append(j)

directors = []
for i in pure_df['has연출감독']:
    for j in i.split(','):
        if j not in directors:
            directors.append(j)

keywords = []
for i in pure_df['Ccube_Meta_what']:
    for j in i.split(','):
        if j not in keywords:
            keywords.append(j)

emotions = []
for i in pure_df['Ccube_Meta_emotion']:
    for j in i.split(','):
        if j not in emotions:
            emotions.append(j)

# print(len(genres)) 28
# print(len(actors)) 16673
# print(len(directors)) 6806
# print(len(keywords)) 2835
# print(len(emotions)) 620

# actor has five different features
# each feature's type is one-hot encoding
variables = {'genre': [len(genres)],
             'actor': [len(actors)],
             'director': [len(directors)],
             'keyword': [len(keywords)],
             'emotion': [len(emotions)]}

vars_list = [len(actors)]

for index, row in pure_df.iterrows():
    print(row[0].split(','))
    print(row[1].split(','))
    print(row[2].split(','))
    print(row[3].split(','))
    print(row[4].split(','))
    exit()


# 시간이 부족하고, 지식의 한계로 인해 원핫 인코딩 후 각 변수들(배우들) 간의 상관관계를 조사하지 못함
# 상관관계에 따른 대표값을 각 변수의 피처값으로 삼으려고 했고, 5가지 대표값들을 PCA 방법을 통하여 2,3 차원으로 축소
# 축소한 차원에서 K-Means 클러스터링을 통해 각 변수들간의 상관성을 분석하고자 하였음.
