#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-07-05 15:28:27
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import xlrd
import os


def loop(file_dir):
    for filedir in os.walk(fiel_dir):
        file_path = 'D:\\big_box\\data\\PDB2.3\\CN\\LOCTBL\\LOCTBL'
        file_path = file_path + '\\' + filedir + '\\RdsTmc.dbf'
        data = xlrd.open_workbook(file_path)
        table = data.sheets()[0]
        cell_C2 = table.cell(2, 1).value
        print(cell_C2)

if __name__ == "__main__":
    file_dir = 'D:\\big_box\\data\\PDB2.3\\CN\\LOCTBL\\LOCTBL'
    loop(file_dir)