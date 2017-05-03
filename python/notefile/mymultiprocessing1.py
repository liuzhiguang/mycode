#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-03 15:12:59
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from multiprocessing import Process
import os


def run_proc(name):
    print('run child process %s (%s)' % (name, os.getpid()))

if  __name__ == '__main__':
    print('parent process %s' % os.getpid())
    p = Process(target=run_proc, args=('test',))
    print('child process will start.')
    p.start()
    p.join()
    print('child process end.')
