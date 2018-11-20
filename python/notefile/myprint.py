#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-06-08 09:22:58
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os

def fib(n):
    a, b = 0, 1
    while a < n:
        print(a, end = ' ')
        a, b = b, a+b
    print()
fib(1000)
