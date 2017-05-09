#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-08 18:17:06
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from collections import Counter
c = Counter()
for ch in 'programming':
    print(c[ch])
    print(c)
    c[ch] = c[ch] + 1
    print(c[ch])
    print(ch)
print(c['a'])
print(c)

b = Counter('qwqwewewqw')
print(b['q'])

print(b)

d = Counter('qqqqeeeee')
b.subtract(d)
print(b)
b.update(d)
print(b)
b.update(d)
print(b)
b = Counter({'a': 2, 'b': 3, 'c': 4})
d = Counter({'a': 1, 'b': 4, 'c': 4})
print(b + d)
print(b - d)
print(b & d)
print(b | d)
print(sum(b.values()))
b.clear()
print(b)
b = Counter({'a': -1, 'b': 2, 'c': 0, 'd': -1, 'e': 2})
print(b.most_common()[:-4:-1])
b += Counter()
print(b)
