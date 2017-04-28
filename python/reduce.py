#!/use/bin/env python3
# -*- coding:utf-8 -*-
from functools import reduce
def str2int(s):
	def f(x,y):
		return x*10+y
	def char2num(s):
		return {'0':0,'1':1,'2':2,'3':3,'5':5}[s]
	return reduce(f,map(char2num,s))
b=str2int('512312312')
print(b)
	

