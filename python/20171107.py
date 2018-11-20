#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-11-07 11:47:27
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
import csv


def main():
    arg = {}
    with open("", "r") as objf:
        with open("", "w", newline="") as objw:
            writer = csv.writer(objw)
            writer.writerow(["序号", "模块", "数量", pell])
            i = 0
            for line in objf:
                if line[0,-] in arg:
                    pass
                    # continue
                else:

                    arg[i] = line.strip("\n")
                    i = i + 1
            while i < len(rawindex):
                i = i + 1
                # print(rawindex[i],raw[rawindex[i]][0])
                writer.writerow(
                    [str(i), rawindex[i], raw[rawindex[i]][0], raw[rawindex[i]][1]])
