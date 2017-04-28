#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 18:00:28
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$


class Dict(dict):
    """docstring for Dict"""

    def __init__(self, **kw):
        super().__init__(**kw)

    def __getattr__(self, key):
        try:
            return self[key]
        except KeyError:
            raise AttributeError('')

    def __setattr__(self, key, value):
        self[key] = value
