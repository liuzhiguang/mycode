#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-21 17:16:37
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os

a = 1.3 * (10**9)
b = 0
for i in range(0, 1300000000):
    a = a / 2
    b = b + 1
    if a < 2:
        break
print(a, b)
