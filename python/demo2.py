#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-03-17 10:12:10
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import urllib.request

url = 'http://i.baidu.com/welcome/'
try:
    data = {}
    data['username'] = ''
    data['password'] = ''
    user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    postdata = urllib.parse.urlencode(data).encode('utf-8')
    header = {'User_Agent': user_agent}
    res = urllib.request.urlopen(url, postdata)

    print(res.read())
except Exception as err:
    print(err)
