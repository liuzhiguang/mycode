#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-03-14 17:18:12
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import urllib.request

page = 1
# url = 'http://www.qiushibaike.com/hot/page/' + str(page)
# user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
# headers = {'User-Agent': user_agent}
# try:
# request = urllib.request(url, headers=headers)
url='https://www.python.org/events/python-events/'
response = urllib.request.urlopen(url)
a=response.read()
print(a)
# except urllib2.URLError, e:
#     if hasattr(e, "code")
#         print e.code
#     if hasattr(e, "reason")
#         print e.reason
