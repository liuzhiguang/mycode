#!/usr/bin/env ruby
from UTC_sd import UTC
from datetime import datetime,tzinfo
now = datetime.now()
tzinfo=UTC(8)
dt = datetime(2017, 4, 26, 10, 38, 20, 12,tzinfo)
dt1 = dt.timestamp()
print(now)
print(type(now))
print(dt)
print(dt1)
print(datetime.fromtimestamp(dt1))
print(datetime.utcfromtimestamp(dt1))
