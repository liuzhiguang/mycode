#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-11 14:11:26
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from wsgiref.simple_server import make_server
from myhello import application

httpd = make_server('', 10001, application)
print('saveing http on port 10001...')
httpd.serve_forever()