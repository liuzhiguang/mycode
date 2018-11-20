#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-06-27 16:28:35
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import sqlite3

with sqlite3.connect('D:\\big_box\\data\\PDB3.0\\CN\\MID\\110000\\routing.midf') as conn:
    cursor = conn.cursor()
    conn.commit()
    print(cursor.rowcount)
    strl = 'select * from road limit 10'
    cursor.execute(strl)
    value_ = cursor.fetchall()
    print(value_)
    cursor.close()
