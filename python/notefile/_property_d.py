#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 11:02:29
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

# 使用@property


class Student(object):
    """docstring for Student"""

    def __init__(self, arg):
        super(Student, self).__init__()
        self.arg = arg

    @property
    def score(self):
        return self._score

    @score.setter
    def score(self, value):
        if not isinstance(value, int):
            raise ValueError('分数必须是数值')
        if value < 0 or value > 100:
            raise ValueError('分数必须在0到100之间')
        self._score = value


class Screen(object):
    @property
    def width(self):
        return self._width

    @width.setter
    def width(self, value):
        self._width = value

    @property
    def height(self):
        return self._height

    @height.setter
    def height(self, value):
        self._height = value

    @property
    def resolution(self):
        self._resolution = self._width * self._height
        return self._resolution
