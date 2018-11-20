#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2018-01-19 10:41:47
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import re
import os
import sqlite_handle

# 打开文件,读取一行
def read_file_byline(str_path):
    with open(str_path, 'r', encoding='utf-8') as objf:
        line = objf.readline()
        return line

# 打开文件,读取所有行
def read_file_bylinelist(str_path):
    with open(str_path, 'r', encoding='utf-8') as objf:
        lines = objf.readlines()
        return lines

# 正则处理文本
def re_file(line):
    pattern = re.compile(r'^.*(Raw\[.*\])')
    re_result = pattern.match(line)
    # re_result_sub = {"None"}
    if re_result:
        pattern_sub = re.compile(
            r'^.*MESH:([\w]+).*RoadID:([\d]+).*tileid:([\d]+).*tpid:([\d]+).*差(.*)\(>10\)')
        re_result_sub = pattern_sub.match(re_result.group(1))
        return(re_result_sub.groups())
    else:
        return re_result


# 按行处理文件
def handle_file_byline(str_path):
    #获取当前UR
    pattern_ur = re.compile(r'^.*FULLLOG\.ur_([\d]+)\.log')
    ur = pattern_ur.match(str_path)
    
    db_path = r'E:\key_account\nds-20170829\test.db'
    tablename = "linklength"
    DB = sqlite_handle.sqliteDB(db_path)
    DB.connectDB()
    lines = read_file_bylinelist(str_path)
    for line in lines:
        result = re_file(line)
        if result:
            dict_data = {'UR':ur.group(1),'MESH':result[0],'ROADID':result[1],'Tileid':result[2],'Tpid':result[3],'DifValue':result[4]}
            DB.insert(dict_data,tablename)
    DB.commit()



def main():
    str_path = "D:\\big_box\\log\\"
    for parent, dirnames, filenames in os.walk(str_path):
        for filename in filenames:
            currentpath = os.path.join(parent, filename)
            handle_file_byline(currentpath)

if __name__ == "__main__":
    main()
