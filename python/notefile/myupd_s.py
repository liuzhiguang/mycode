#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-10 15:29:10
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('127.0.0.1',10001))

print('bind upd on 10001...')

while True:
    data, addr = s.recvfrom(1024)
    print('recevied from %s:%s' % addr)
    s.sendto(b'hello, %s!' % data, addr)

