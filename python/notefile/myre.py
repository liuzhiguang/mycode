#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-08 09:51:14
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import re

test = '正则表达式'
if re.match(r'正则表达式', test):
    print('ok')
else:
    print('fail')


t = '19:59:53'
m = re.compile(r'^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:([0-5][0-9])\:([0-5][0-9])$')
mm = m.match(t)
print(mm.groups())


t1 = 'someone@gmail.com'
t2 = 'bill.gates@microsoft.com'
t3 = '<Tom Paris> tom@voyager.org'
emailr = re.compile(r'((^[\w]+[.]?[\w]+|^[\w]+)@[\w]+[.]{1}[\w]+$)')
m1 = emailr.match(t1)
m2 = emailr.match(t2)
# m3 = emailr.match(t3)
# for i in (m1, m2, m3):
#     if i.group(1) == None:
#         print('email:'+i.group(2))
#     else:
#         print('name:'+i.group(1)+'email:'+i.group(2))
for i in (m1, m2):
    print('email:',i.group(1))

emailr1 = re.compile(r'^(<[\w\s]+>)\s+([\w]+@[\w]+[.]{1}[\w]+$)')
m3 = emailr1.match(t3)
if emailr1.match(t3):
    print('ok')
else:
    print('fail')
print('name:',m3.group(1),'email:',m3.group(2))