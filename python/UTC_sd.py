#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-26 11:11:53
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from datetime import datetime, tzinfo, timedelta


class UTC(tzinfo):
    """docstring for UTC"""

    def __init__(self, offset=0):
        # super(UTC, self).__init__()
        self.offset = offset

    def utcoffset(self, dt):
        return timedelta(hours=self.offset)

    def tzname(self, dt):
        return "UTC + %s" % self.offset

    def dst(self, dt):
        return timedelta(hours=self.offset)
