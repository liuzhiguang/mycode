#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 11:02:29
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from sys import path
path.append("..\\notefile")

import _property_d
from _property_d import Screen

import Metaclass
from Metaclass import *


def main():
    '''#测试@property.'''
    print('=========测试@property=========')
    s = Screen()
    s.width = 1024
    s.height = 768
    print(s.resolution)
    assert s.resolution == 786432, '1024 * 768 = %d ? ' % s.resolution
    print('=========分割线=========')

    '''测试元类'''
    print('=========测试元类=========')
    h = Hello()
    h.hello()
    print(type(Hello))
    print(type(h))
    L = Mylist()
    L.add(1)
    L.add(2)
    L.dele(1)
    print(L)
    print(type(L))

    u = User(id=12345, name='a', email='test@orm.org', password='my-pwd')
    u.save1()

    print('=========分割线=========')


if __name__ == "__main__":
    main()
