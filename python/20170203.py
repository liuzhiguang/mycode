#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-02-03 15:29:04
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import functools


def endcall():
    print('end call')


def log(*args):
    text = ''

    def decorator(func):
        functools.wraps(func)

        def wrapper(*args1, **kw):
            print('%s begin call: %s' % (text, func.__name__))

            func(*args1, **kw)
            print('%s end call: %s' % (text, func.__name__))
        return wrapper

    if len(args) > 0 and callable(args[0]):
        return decorator(*args), print('w')
    else:
        if len(args) > 0:
            text = '%s' % args[0]
        return decorator


@log()
def f(s):
    s = '2017-2-3'
    return s


ls = ''
ls = f(ls)
print(ls)
