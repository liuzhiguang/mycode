#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-10 14:38:53
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import socket
import threading
import time


def tcplink(sock, addr):
    print('accept new connection from %s:%s' % addr)
    sock.send(b'welcome!')
    while True:
        data = sock.recv(1024)
        time.sleep(1)
        if not data or data.decode('utf-8') == 'exit':
            break
        sock.send(('hello, %s' % data.decode('utf-8')).encode('utf-8'))
    sock.close()
    print('connection from %s:%s closed.' % addr)


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('127.0.0.1', 10001))
s.listen(5)
print('waiting for connection...')
while True:
    sock, addr = s.accept()
    t = threading.Thread(target=tcplink, args=(sock, addr))
    t.start()
