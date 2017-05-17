#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-11 10:11:13
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import sqlite3

with sqlite3.connect('test.db') as conn:
    cursor = conn.cursor()
    # cursor.execute('insert into user (id, name) values (\'5\',\'lzg\')')
    # cursor.execute('insert into user (id, name) values (\'6\',\'lzg\')')
    conn.commit()
    print(cursor.rowcount)
    cursor.execute('select * from user where id = ?' , ('1',))
    value = cursor.fetchall()
    print(value)
    cursor.close()