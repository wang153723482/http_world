# encoding=utf8
import urllib
import urllib2
from pyquery import PyQuery as pq
import StringIO, gzip
import time
import logging
import json
import random

# 1.定时执行  （使用系统定时任务）
# 2.爬取多个目标url
# 3.n个代理随机使用
# 4.与上一次的价格对比，如果降价发通知
# 5.日志（每次的价格）



url = 'http://www.uniqlo.cn/item.htm?spm=0.0.0.0.WyizUE&id=556952180526&frm=&'

urls = ['http://www.uniqlo.cn/item.htm?spm=0.0.0.0.WyizUE&id=556952180526&frm=&',
        'http://www.uniqlo.cn/item.htm?id=556207231623&frm=&']

proxies = [
    '10.7.13.155:11080'
]

logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                    datefmt='%Y-%m-%d %a %H:%M:%S',
                    filename='uniqlo.log',
                    filemode='a')  # %d %b


# 
def main():
    logging.info("ssss")
    stander()

def main1():
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

    pfr = open('result.json')
    prices = json.load(pfr)
    pfr.close()

    proxy_support = urllib2.ProxyHandler({'http':
                                          # 'http://127.0.0.1:9080'
                                          # 'http://10.7.13.155:10080'
                                              proxies[random.randint(0, len(proxies) - 1)]
                                          })
    opener = urllib2.build_opener(proxy_support)
    urllib2.install_opener(opener)

    for url in urls:
        print url
        req = urllib2.Request(url, None, headers)
        resp = urllib2.urlopen(req,timeout=5)
        html = gzdecode(resp.read())
        pq_html = pq(html)
        # print html
        title = pq_html('title').html()
        price = pq_html('#J_StrPrice').text()
        logging.info(title + ',' + price)

        if not prices.has_key(title):
            prices[title] = price
        elif float(prices.get(title, 999999999999)) < float(price):
            print 'send email'
        else:
            print '价格没有变化。。。。。。。。'

    pfw = open('result.json', 'w')
    json.dump(prices, pfw)
    pfw.close()


# 简版
def sample():
    response = urllib2.urlopen(url)
    html = response.read()
    print html
    pq_html = pq(html)
    print pq_html('#J_StrPrice').text()


# 代理版
def proxy():
    proxy_support = urllib2.ProxyHandler({'http': 'http://127.0.0.1:9080'})
    opener = urllib2.build_opener(proxy_support)
    urllib2.install_opener(opener)
    html = urllib2.urlopen(url).read()
    print html
    pq_html = pq(html)
    print pq_html('#J_StrPrice').text()


# 标准版
def stander():
    headers = {
        # 'Host': 'www.uniqlo.cn',
        'Connection': 'keep-alive',
        'Cache-Control': 'max-age=0',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'DNT': '1',
        'Accept-Encoding': 'gzip, deflate',
        # 'Referer': 'http://www.uniqlo.cn',
        'Accept-Language': 'zh-CN,zh;q=0.8'
    }
    req = urllib2.Request(url, None, headers)
    response = urllib2.urlopen(req)
    print response.read()
    html = gzdecode(response.read())
    print html
    pq_html = pq(html)
    print pq_html('#J_StrPrice').text()


# 解压gzip  
def gzdecode(data):
    compressedstream = StringIO.StringIO(data)
    gziper = gzip.GzipFile(fileobj=compressedstream)
    data2 = gziper.read()  # 读取解压缩后数据   
    DD = data2.decode('gbk', 'ignore')
    return DD


def getTime():
    return time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))


if __name__ == '__main__':
    main()
    pass
