#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2018-01-19 15:21:09
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$


import sqlite3


class sqliteDB(object):
    """docstring for sqliteDB"""

    def __init__(self, arg):
        super(sqliteDB, self).__init__()
        self.arg = arg

    #连接数据库
    def connectDB(self):
        with sqlite3.connect(self.arg) as conn:
            self.conn = conn
        return None

    #插入新记录
    def insert(self, data, tablename):
        cursor = self.conn.cursor()
        self.cursor = cursor
        field = "("
        value = "("
        for key in data:
            field = field + key + ","
            value = value + "\'" + data[key] + "\'" + ","
        field = field[0:-1] + ")"
        value = value[0:-1] + ")"
        sql = "insert into " + tablename + field + " values" + value + ";"
        cursor.execute(sql)

    #提交事务
    def commit(self):
        self.conn.commit()



