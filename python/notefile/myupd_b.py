#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-10 15:34:12
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
for data in [b'Michael', b'Tracy', b'Sarah']:
    s.sendto(data, ('127.0.0.1', 10001))
    print(s.recv(1024).decode('utf-8'))
s.close()