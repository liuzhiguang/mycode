#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 18:04:31
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import unittest
from Mydict import Dict


class testdict(unittest.TestCase):
    """docstring for testdict"""

    def test_init(self):
        d = Dict(a=1, b='test')
        self.assertEqual(d.a, 1)
        self.assertEqual(d.b, 'test')
        self.assertTrue(isinstance(d, dict))

    def test_key(self):
        d = Dict()
        d['key'] = 'value'
        self.assertEqual(d.key, 'value')

    def test_attr(self):
        d = Dict()
        d.key = 'value'
        self.assertTrue('key' in d)
        self.assertEqual(d['key'], 'value')

    def test_keyerror(self):
        d = Dict()
        with self.assertRaises(KeyError):
            value = d['empty']

    def test_attarerror(self):
        d = Dict()
        with self.assertRaises(AttributeError):
            value = d.empty


if __name__ == '__main__':
    unittest.main()
