# !/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-07-18 14:04:11
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
import sys
import csv


def main():
    value = {}
    value_1 = {}
    allsize = 0.0
    allnu = 0
    strl = ""
    # arg = sys.argv
    # print(arg[1])
    arg = {}
    with open('./config.ini', "r") as fileobject:
        i = 0
        for line in fileobject:
            i = i + 1
            arg[i] = line
    print("检索目录:%s，输出目录:%s;" % (arg[1][:-1], arg[2]))
    # r'D:\big_box\data\PDB2.3.2\JP\LOADABLE'
    for parent, dirnames, filenames in os.walk(arg[1][:-1]):
        for filename in filenames:
            currentpath = os.path.join(parent, filename)
            filesize = os.path.getsize(currentpath)
            temp = 0.0
            temp_1 = 0
            if filename in value:
                temp = value[filename]
                temp_1 = value_1[filename]
            value[filename] = temp + filesize / 1024 / 1024
            value_1[filename] = temp_1 + 1
    # for line in value:
    #     print(line[:-4])
    #     allsize = allsize + value[line]
    #     allnu = allnu + value_1[line]
    #     strl = strl + line[:-4] + " " + value_1[line] + str(value[line]) + "\n"
        # r'E:\大客户\文档\规则需求\发布资料\test.txt'
    with open(arg[2], "w", newline="") as fobj:
        # fobj.write(strl)
        writer = csv.writer(fobj)
        writer.writerow(["序号", "模块", "数量", "大小/Mb"])
        i = 0
        for line in value:
            allsize = allsize + value[line]
            allnu = allnu + value_1[line]
            i = i + 1
            writer.writerow(
                [str(i), line[:-4], str(value_1[line]), str(value[line])])

        writer.writerow([str(i + 1), "总数", str(allnu), str(allsize)])
    # with open(arg[2], "a") as fobj1:
    #     strl = "allsize" + " " + str(allsize)
    #     fobj1.write(strl)
if __name__ == "__main__":
    main()
