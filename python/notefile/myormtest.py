#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-06-07 18:18:44
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
import sqlite3

with sqlite3.connect('D:\\big_box\\data\\PDB2.2.1\\TW\\NDS\\GLOBE\\UR_0\\DTM.NDS') as conn:
    cursor = conn.cursor()
    conn.commit()
    cursor.execute('SELECT * FROM "dtmBdamTextureMapTable"')
    value = cursor.fetchall()

