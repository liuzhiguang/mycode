#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-10 09:52:56
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from urllib import request, parse

# with request.urlopen('https://api.douban.com/v2/book/2129650') as f:
#     data = f.read()
#     # data.encoding = 'utf-8'
#     print('status:', f.status, f.reason)
#     for k, v in f.getheaders():
#         print('%s:%s'%(k, v))
#     print('data:', data.decode('utf-8'))

# req = request.Request('http://www.douban.com/')
# req.add_header('User-Agent', 'Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25')
# with request.urlopen(req) as f:
#     print('status:', f.status, f.reason)
#     for k, v in f.getheaders():
#         print('%s:%s' % (k, v))
#     print('data:', f.read().decode('utf-8'))

print('login to weibo.cn...')
email = input('email:')
password = input('password:')
login_data = parse.urlencode([
    ('username:', email),
    ('password', password),
    ('entry', 'mweibo'),
    ('client_id', ''),
    ('savestate', '1'),
    ('ec', ''),
    ('pagerefer', 'https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fm.weibo.cn%2F')
])

req = request.Request('https://passport.weibo.cn/sso/login')
req.add_header('Origin', 'https://passport.weibo.cn')
req.add_header('User-Agent', 'Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25')
req.add_header(
    'Referer', 'https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fm.weibo.cn%2F')

with request.urlopen(req, data=login_data.encode('utf-8')) as f:
    print('status:', f.status, f.reason)
    for k, v in f.getheaders():
        print('%s:%s' % (k, v))
    print('data:', f.read().decode('utf-8'))
