# Tokenize

# Import regular expression
import re

# Way to read and write data
import csv
import pandas as pd

# Database connecting
import mysql.connector
from sqlalchemy import create_engine

# 형태소분석기
from konlpy.tag import Okt
from konlpy.tag import Kkma

# For counting frequency
from collections import Counter
from sklearn.feature_extraction.text import CountVectorizer

# Uploading data to mysql database from excel data
engine = create_engine('mysql+pymysql://tars:tars@localhost/test')
df = pd.read_excel('input/attach_01.xlsx')
with engine.connect() as conn, conn.begin():
    df.to_sql('tokenize', conn, if_exists='replace', index=False)

# Access to mysql
mydb = mysql.connector.connect(
    host = 'localhost',
    user = 'tars',
    passwd = 'tars',
    database = 'test'
)

# read from database and extract 질의문장
mycursor = mydb.cursor()
mycursor.execute('select 질의문장 from tokenize')

# using 형태소분석기
okt = Okt()
kkma = Kkma()

# Making 질의문장 list
question = []
for x in mycursor.fetchall():
    question.append(list(x))

# Bye DB
mycursor.close()
mydb.close()

# Tokenizing by 형태소분석기 and insert into 형태소단위 list
tokenized = []
for x in question:
    for y in x:
        z = okt.morphs(y)
        for i in z:
            tokenized.append(i)

# Make sure for counting only word, not for special letters
nonPunct = re.compile('.*[A-Za-z0-9가-힣].*')
filtered = [w for w in tokenized if nonPunct.match(w)]
counts = Counter(filtered)

# Fast way to counting word frequency
vectorizer = CountVectorizer()
vectorizer.fit(tokenized)

# Write word and frequency down csv file as output
with open('output/result_problem1.csv', mode='w') as output_file:
    output_writer = csv.writer(output_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    output_writer.writerow(["토큰", "갯수"])

    # Only for word, BUT THIS IS NOT A PERFECT WAY TO SEPARATE WORD AND 형태소
    for key in vectorizer.vocabulary_.keys():
        output_file.write("%s,%s\n" % (key, vectorizer.vocabulary_[key]))

# The End
