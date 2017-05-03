#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-03 15:46:47
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os
import time
import random
from multiprocessing import Pool


def long_time_task(name):
    print('run task %s(%s)' % (name, os.getpid()))
    start = time.time()
    time.sleep(random.random() * 3)
    end = time.time()
    print('task %s runs %0.2f seconds.' % (name, (start - end)))

if __name__ == '__main__':
    print('parent process %s' % os.getpid())
    p = Pool(19)
    for i in range(20):
        p.apply_async(long_time_task, args=(i,))
    print('waiting fo all subprocess done...')
    p.close()
    p.join()
    print('all subprocess done.')
