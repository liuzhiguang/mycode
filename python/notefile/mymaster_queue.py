#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-04 11:40:54
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import random
import time
import queue
from multiprocessing.managers import BaseManager
from multiprocessing import freeze_support

task_queue = queue.Queue()
result_queue = queue.Queue()


def return_task_queue():
    global task_queue
    return task_queue


def return_result_queue():
    global result_queue
    return result_queue


class QueueManager(BaseManager):
    pass

def test():
    # QueueManager.register('get_task_queue', callable=lambda: task_queue)
    # QueueManager.register('get_result_queue', callable=lambda: result_queue)
    QueueManager.register('get_task_queue', callable=return_task_queue)
    QueueManager.register('get_result_queue', callable=return_result_queue)
    manager = QueueManager(address=('127.0.0.1', 5000), authkey=b'abc')

    manager.start()

    task = manager.get_task_queue()
    result = manager.get_result_queue()

    for i in range(10):
        n = random.randint(0, 1000)
        print('put task %d...' % n)
        task.put(n)

    print('try get result...')

    for i in range(10):
        r = result.get(timeout=1000)
        print('get result %d...' % r)
    manager.shutdown()
    print('master exit...')

if __name__ == '__main__':
    freeze_support()
    test()
