#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-02 11:01:30
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

# try:
#     f = open('E:\文档\记录\\20160920.txt', 'r')
#     print(f.read())
# finally:
#     if f:
#         f.close()


with open('E:\mycode\\awesome-python3-webapp\www\\templates\\testio.txt', 'w+', encoding='gbk') as fw:
    for line in fw:
        fw.write(line)
        fw.write('12')


with open('E:\mycode\\awesome-python3-webapp\www\\templates\\testio.txt', 'r', encoding='gbk', errors='ignore') as f:
    for line in f.readlines():
        print(line.strip())


from io import StringIO, BytesIO
fio = StringIO()
fio.write('hello')
fio.write(' ')
fio.write('world!')
print(fio.getvalue())
fio = StringIO('hello\nhi\ngoodby')
while True:
    s = fio.readline()
    if s == '':
        break
    print(s.strip())

fio = BytesIO()
fio.write('zhongwen'.encode('utf-8'))
print(fio.getvalue())
fio = BytesIO(b'\xe4\xb8\xad\xe6\x96\x87')
print(fio.read())


import os
# print(os.environ)

str = os.path.abspath(".")
str = os.path.join(str, 'test')
# os.rmdir('E:\\mycode\\python\\notefile\\test')
print(str)

print([x for x in os.listdir('.') if os.path.isdir(x)])
print([x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1] == '.py'])


import pickle
d = dict(name = 'bob', age = 10, score = 100)
b = pickle.dumps(d)
print(b)
with open('E:\mycode\\awesome-python3-webapp\www\\templates\\testio.txt', 'wb',) as fw:
    pickle.dump(d, fw)
with open('E:\mycode\\awesome-python3-webapp\www\\templates\\testio.txt', 'rb',) as fr:
    d = pickle.load(fr)
    print(d)

import json
d = dict(name = 'bob', age = 10, score = 100)
print(json.dumps(d))