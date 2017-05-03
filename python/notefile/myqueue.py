#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-03 16:49:41
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
import time
import random
from multiprocessing import Process, Queue


def write(q):
    print('process to write:%s' % os.getpid())
    for value in ['a', 'b', 'c', 'd']:
        print('put %s to queue...' % value)
        q.put(value)
        time.sleep(random.random())


def read(q):
    print('process to read:%s' % os.getpid())
    while True:
        value = q.get(True)
        print('get %s from queue.' % value)

if __name__ == '__main__':
    q = Queue()
    pw = Process(target=write, args=(q,))
    pr = Process(target=read, args=(q,))

    pr.start()
    pw.start()
    pw.join()
    pr.terminate()
