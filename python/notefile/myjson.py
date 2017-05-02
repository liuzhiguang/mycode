#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-02 16:44:15
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import json


class Student(object):
    """docstring for Student"""

    def __init__(self, name, age, score):
        self.name = name
        self.age = age
        self.score = score


def student2dict(std):
    return{
        'name': std.name,
        'age': std.age,
        'score': std.score
    }


def dict2student(d):
    return Student(d['name'], d['age'], d['score'])

s = Student('bob', 20, 100)
print(json.dumps(s, default=student2dict))

json_str = '{"age":20, "name":"bob", "score":100}'
print(json.loads(json_str, object_hook=dict2student))
