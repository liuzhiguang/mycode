#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-02 10:14:42
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$


class Dict(dict):
    '''
    simple dict but also support access as x.y style.

    >>> d1 = Dict()
    >>> d1['x1'] = 100
    >>> d1.x1
    100
    >>> d1.y1 = 200
    >>> d1['y1']
    200
    >>> d2 = Dict(a = 1, b = 2, c = '3')
    >>> d2.c
    '3'
    >>> d2['empty']
    Traceback (most recent call last):
        ...
    KeyError: 'empty'
    >>> d2.empty
    Traceback (most recent call last):
        ...
    AttributeError: 'Dict' object has no attribute 'empty'
    '''
    def __init__(self, **kw):
        super(Dict, self).__init__(**kw)

    # def __getattr__(self, key):
    #     try:
    #         return self[key]
    #     except KeyError:
    #         raise AttributeError("'dict' object has no attribute '%s'" % key)

    def __setattr__(self, key, value):
        self[key] = value

if __name__ == '__main__':
    import doctest
    doctest.testmod()