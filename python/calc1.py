#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-03-22 14:12:17
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$


# str1 = 139.012497,35.551674
# str1=500444.9892 , 127986.02639999999
str1=503182.021 ,  128227.870
# str1 = '503182021 +043:03:43.45'

i = 0
if isinstance(str1, str):
    i = 2
elif int(str1[0]) > 360:
    i = 1
else:
    i = 0
# str_s = str.split(',')
if i == 1:
    lon = float(str1[0])
    lat = float(str1[1])
    lat_1 = lat / 3600
    lon_1 = lon / 3600
    s1 = str(lon_1) + "," + str(lat_1) + '\n'
    s2 = '\r\n' + str(lon_1) + '\r\n' + str(lat_1)
    print(s1)
    print(s2.strip())
if i == 0:
    lon = float(str1[0])
    lat = float(str1[1])
    lat_1 = lat * 3600
    lon_1 = lon * 3600
    print(lon_1, ",", lat_1)
if i == 2:
    str1 = str1.replace(' ', '')
    str_s1 = str1[str1.index('+') + 1:str1.index('+', 2)]
    str_s1_s = str_s1.split(':')
    str_s2 = str1[str1.index('+', 2) + 1:]
    str_s2_s = str_s2.split(':')
    lon = float(str_s1_s[0]) + float(str_s1_s[1]) / \
        60 + float(str_s1_s[2]) / 3600
    lat = float(str_s2_s[0]) + float(str_s2_s[1]) / \
        60 + float(str_s2_s[2]) / 3600
    print(lon, ',', lat)
# class A:
#     pass
# class B(A):
#     pass
# print(type(B()))
# print(type(B())==B)
# print(type(A()))
# print(isinstance(B(),A))