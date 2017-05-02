#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-02 10:35:40
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$


def fact(n):
    '''
    This is a simple recursive function.

    >>> fact(0)
    Traceback (most recent call last):
    ...
    ValueError
    >>> fact(1)
    1
    >>> fact(3)
    6
    '''
    if n < 1:
        raise ValueError()
    if n == 1:
        return n
    return n * fact(n - 1)

if __name__ == '__main__':
    import doctest
    doctest.testmod()
