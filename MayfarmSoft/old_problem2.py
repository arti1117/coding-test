import re
import requests
from bs4 import BeautifulSoup

# https://www.fun-coding.org/crawl_basic1.html

# 1) reqeusts 라이브러리를 활용한 HTML 페이지 요청
# 1-1) res 객체에 HTML 데이터가 저장되고, res.content로 데이터를 추출할 수 있음
res = requests.get('https://github.com/search?utf8=%E2%9C%93&q=%EB%94%A5%EB%9F%AC%EB%8B%9D&ref=simplesearch')
# print(res.content)

# 2) HTML 페이지 파싱 BeautifulSoup(HTML데이터, 파싱방법)
# 2-1) BeautifulSoup 파싱방법
soup = BeautifulSoup(res.content, 'html.parser')

# 3) 필요한 데이터 검색
repo_list = soup.find_all('li', {'class':'repo-list-item d-flex flex-column flex-md-row flex-justify-start py-4 public source'})
# print(repo_list)

for item in repo_list:
    title = item.find('h3').text.strip()
    frame = item.find('div', {'class':'mr-3'}).text.strip() if item.find('div', {'class':'mr-3'}) else ''
    counting = int(item.find('div', {'class':'pl-2 pl-md-0 text-right flex-auto min-width-0'}).text.strip()
                   if item.find('div', {'class':'pl-2 pl-md-0 text-right flex-auto min-width-0'}) else '0')
    comment = item.find('p', {'class':'col-12 col-md-9 d-inline-block text-gray mb-2 pr-4'}).text.strip() \
        if item.find('p', {'class':'col-12 col-md-9 d-inline-block text-gray mb-2 pr-4'}) else ''
    keyword = []
    for keys in item.find_all('a', {'class':'topic-tag topic-tag-link f6 my-1'}):
        keyword.append(keys.text.strip())

    # 4) 필요한 데이터 추출
    print(title)
    print(frame)
    print(counting)
    print(comment)
    print(keyword)

