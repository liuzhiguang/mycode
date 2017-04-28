#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 12:03:01
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from enum import Enum, unique

Month = Enum('A',('a', 'b', 'c', 'd'))

@unique
class Weekday(Enum):
    Sun = 0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6


if __name__ == '__main__':
    for name, member in Month.__members__.items():
        print(name, '=>', member, member.value)
    month = Month.a
    print(month)