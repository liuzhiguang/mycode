#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-23 15:29:30
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from orm import Model, StringField, IntegerField

def User(Model):
    __table__ = 'user'
    id = IntegerField(primary_key = True)
    name = StringField()

class Model(dict, metaclass = ModelMetaclass):
    """docstring for Model"""
    def __init__(self, **kw):
        super(Model, self).__init__(**kw)

    def __getattr__(self, key):

        try:
            return self[key]
        except KeyError:
            raise AttributeError(r"'Model' object has no attribute '%s'" % key)



        
