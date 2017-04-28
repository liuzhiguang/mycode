#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-03-14 17:34:30
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import urllib.request
# import re<br>

# page = 1
# urlstr = 'http://www.qiushibaike.com/hot/page/' + str(page)
# user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
# headers = {'User-Agent': user_agent}
# url = urllib.request.urlopen(urlstr)
# print(url.read())
# word = ['a', 'b', 'c', 'd', 'e']
# x = {'a': 1, 'b': '1', 'c': "q"}
# a = word[-1:]
# word.append('h')
# print(x['b']+'\r\n'+word[1] +'\r')
# print(a)
# c=int(input("kongzhitai"))
# print(c)
try:
    a = range(1, 10)
    for i in a:
        print(m)
        a = range(-2, -11, -3)
# for i in a:
    # print(i)
except Exception as err:
    err = 'test'
    print(err)
spath = 'E:\mycode\python\\test.txt'
f = open(spath, 'w')
f.write('first line 1\n')
f.writelines('first line 2\n')
f.close()
f = open(spath, 'r')
for line in f:
    print('每一行数据是：%s' % line)
f = open(spath, 'a')
f.write('as')
f.close()
f = open(spath, 'r')
for line in f:
    print('每一行数据是：%s' % line)
spath = 'E:\mycode\python\\test.txt'
f = open(spath, 'w')
f.write('end')
f = open(spath, 'r')
for line in f:
    print('仅一行了：%s' % line)

import sys
print(sys.path)
print(dir(list))