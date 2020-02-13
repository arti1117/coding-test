import nltk
import re
from nltk.tokenize import sent_tokenize, word_tokenize
import pandas as pd
from pandas import ExcelWriter
from pandas import ExcelFile
from collections import Counter
from itertools import chain
from functools import reduce
from sklearn.feature_extraction.text import CountVectorizer
import csv

# https://wikidocs.net/21698

# nltk.download()

df = pd.read_excel('input/attach_01.xlsx')

print("Column headings:")
print(df.columns)

print(df.head())

print(df['질의문장'])

# converting to dict
# https://www.geeksforgeeks.org/python-pandas-dataframe-to_dict/

data_dict = df['질의문장'].to_list()

print(data_dict)

print(word_tokenize(data_dict[0]))

result = []

for sentence in data_dict:
    sentence_instance = []

    sentence_instance.append(word_tokenize(sentence))
    result.append(sentence_instance)

print(result)

print(chain.from_iterable(result))

tokenized = []
for i in result:
    for j in i:
        for k in j:
            tokenized.append(k)

print(tokenized)

# https://www.geeksforgeeks.org/python-ways-to-flatten-a-2d-list/

print([a for b in result for a in b])
print(reduce(lambda z, y: z + y, [a for b in result for a in b]))

nonPunct = re.compile('.*[A-Za-z0-9가-힣].*')
filtered = [w for w in tokenized if nonPunct.match(w)]
counts = Counter(filtered)

# https://wikidocs.net/33661

vectorizer = CountVectorizer()
vectorizer.fit(tokenized)

print(vectorizer.vocabulary_)

# https://realpython.com/python-csv/
# https://www.tutorialspoint.com/How-to-save-a-Python-Dictionary-to-CSV-file

with open('output/old_result_problem1.csv', mode='w') as output_file:
    output_writer = csv.writer(output_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

    for key in vectorizer.vocabulary_.keys():
        output_file.write("%s,%s\n"%(key, vectorizer.vocabulary_[key]))

# https://cromboltz.tistory.com/18
# https://cceeddcc.tistory.com/8
# https://konlpy-ko.readthedocs.io/ko/v0.4.3/morph/
# https://pythonspot.com/read-excel-with-pandas/
