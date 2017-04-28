#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-01-17 15:27:30
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
from functools import reduce


def normalize(name):
    '''
    AADFAD
    '''
    return name[0].upper() + name[1:].lower()


L1 = ['lAFSDs', 'EWsdaw', 'ADFEWF', 'sdfwef']
L2 = list(map(normalize, L1))
print(L2)
L = [1, 2, 4, 5, 6, 9, 10, 15]


def prod(L):
    def fn(x, y):
        return x * y
    return reduce(fn, L)


print('3 * 5 * 7 * 9 =', prod(L))


def str2float(s):
    def fn(x, y):
        return x * 10 + y
    n = s.index('.')
    s1 = list(map(int, [x for x in s[:n]]))
    s2 = list(map(int, [x for x in s[n + 1:]]))
    return reduce(fn, s1) + reduce(fn, s2) / (10**len(s2))


print('\'121212.1212\'=', str2float('121212.1212'))


def is_odd(n):
    return n % 2 == 1


list(filter(is_odd, L))


print(list(filter(is_odd, L)))


def not_empty(s):

    return s and s.strip()


L3 = ['qwe', '', 'qweqweeqweqw', '  ', None, '']
l4 = list(filter(not_empty, L3))

print(l4)


print(12 // 10)


L5 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11]
print(L5[::-1])


def is_palindrom(n):
    ss = str(n)
    return ss == ss[::-1] and len(ss) > 2


output = list(filter(is_palindrom, range(1, 1000)))
print(output)

L6 = ['bob', 'about', 'Zoo', 'Credit']
L7 = sorted(L6, key=str.upper, reverse=True)
print(L7)

L = [('Bob', 75), ('Adam', 92), ('Bart', 66), ('Lisa', 88)]


def by_name(t):
    return t[0]


def by_code(t):
    return t[1]


L1 = sorted(L, key=by_name, reverse=True)
L2 = sorted(L, key=by_code)
print(L1, '\n', L2)




def now():
    print('2017-2-3')
f=now()

