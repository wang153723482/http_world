# encoding=utf8
import urllib
import urllib2
from pyquery import PyQuery as pq
import StringIO, gzip
import time

url = 'http://www.uniqlo.cn/item.htm?spm=0.0.0.0.WyizUE&id=556952180526&frm=&'


def main():
    stander()

# 简版
def sample():
    response = urllib2.urlopen(url)
    html = response.read()
    # print html
    pq_html = pq(html)
    print pq_html('#J_StrPrice').text()

# 标准版

def stander():

    headers = {
        'Host': 'www.uniqlo.cn',
        'Connection': 'keep-alive',
        'Cache-Control': 'max-age=0',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'DNT': '1',
        'Accept-Encoding': 'gzip, deflate',
        'Referer': 'http://www.uniqlo.cn/search.htm?orderType=_newOn&viewType=grid&baobei_type=&keyword=%B7%C2%D1%F2%B8%E1%C8%DE&price1=&price2=',
        'Accept-Language': 'zh-CN,zh;q=0.8'
    }
    
    req = urllib2.Request(url, None, headers)
    response = urllib2.urlopen(req)
    html = gzdecode( response.read() )
    pq_html = pq(html)
    print pq_html('#J_StrPrice').text()

#解压gzip  
def gzdecode(data) :
    compressedstream = StringIO.StringIO(data)
    gziper = gzip.GzipFile(fileobj=compressedstream)
    data2 = gziper.read()   # 读取解压缩后数据   
    return data2

def getTime():
    return time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))


if __name__ == '__main__':
    main()
    pass
