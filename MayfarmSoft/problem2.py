# Web Crawling

import requests
import pandas as pd
from bs4 import BeautifulSoup
from sqlalchemy import create_engine

data = {
    'infox': [],
    'title': [],
    'frame': [],
    'count': [],
    'intro': [],
    'words': []
}

basic = 'https://github.com'
search = '/search?utf8=%E2%9C%93&q=%EB%94%A5%EB%9F%AC%EB%8B%9D&ref=simplesearch'

res = requests.get(basic+search)
soup = BeautifulSoup(res.content, 'html.parser')
pages = int(soup.find('em', {'class': 'current'}).get('data-total-pages')) + 1
# pages = 2
indexs= 0

for p in range(1, pages):
    res = requests.get(basic + search + '&p=' + str(p))
    soup = BeautifulSoup(res.content, 'html.parser')

    repo_list = soup.find_all('li',
                              {'class': 'repo-list-item d-flex flex-column '
                                        'flex-md-row flex-justify-start py-4 public source'})
    for item in repo_list:
        data['infox'].append(indexs)
        indexs += 1
        data['title'].append(item.find('h3').text.strip())

        data['frame'].append(item.find('div', {'class': 'mr-3'}).text.strip()
                             if item.find('div', {'class':'mr-3'}) else '')

        data['count'].append(int(item.find('div', {'class': 'pl-2 pl-md-0 text-right flex-auto min-width-0'}).text.strip()
                                 if item.find('div', {'class': 'pl-2 pl-md-0 text-right flex-auto min-width-0'}) else '0'))

        data['intro'].append(item.find('p', {'class': 'col-12 col-md-9 d-inline-block text-gray mb-2 pr-4'}).text.strip()
                             if item.find('p', {'class': 'col-12 col-md-9 d-inline-block text-gray mb-2 pr-4'}) else '')

        keyword = ''
        for keys in item.find_all('a', {'class': 'topic-tag topic-tag-link f6 my-1'}):
            keyword = keyword + keys.text.strip() + ','
        keyword = keyword[:-1]

        data['words'].append(keyword)

# Uploading data to mysql database from excel data
engine = create_engine('mysql+pymysql://tars:tars@localhost/test')
df = pd.DataFrame.from_dict(data)
with engine.connect() as conn, conn.begin():
    df.to_sql('crawling', conn, if_exists='append', index=False)
