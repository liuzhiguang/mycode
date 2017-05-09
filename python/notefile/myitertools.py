#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-09 16:14:28
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import itertools
natuals = itertools.count(0)
ns = itertools.takewhile(lambda x:x<10, natuals)
print(list(ns))
