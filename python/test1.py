#!/use/bin/env python3
# -*- coding:utf-8 -*-
# name=input('username:')
# money=input('paymoney:')
#print('Hi,%s,you need pay $%s.' %(name,money))
# s3=input('姓名：')
# s1=input('去年的成绩：')
# s2=float(input('今年的成绩：'))
# s1i=float(s1)
# s4=(s2-s1i)*100/s1i
# print('%s成绩提升的百分比：%.1f%%'%(s3,s4))
# L=[
# 	['Apple','Goole','Microsoft'],
# 	['Java','Python','Ruby','PHP'],
# 	['Adam','Batr','Lisa']
# 	]
# print('%s\n%s\n%s'%(L[0][0],L[1][1],L[2][2]))
# height=float(input('身高(m):'))
# weight=float(input('体重(kg):'))
# BMI=weight/(height**2)
# jiegou=''
# if BMI<18.5:
# 	jiegou='过轻'
# elif 18.5<=BMI<=25:
# 	jiegou='正常'
# elif 25<BMI<=28:
# 	jiegou='过重'
# elif 28<BMI<=32:
# 	jiegou='肥胖'
# elif BMI>32:
# 	jiegou='严重肥胖'
# print('%s'%(jiegou))
# L=['Bart','Lisa','Adam']
# x=4
# while x>0:
# 	print(x)
# d={'Michael':99,'Bob':80,'Tracy':12}
# name=input('name:')
# print('%s:%i'%(name,d.get(name)))
# i=int(input('输入整数：'))
# b=hex(i)
# print(b)
# def my_abs(x):
# 	if x>=0:
# 		return x
# 	else:
# 		return -x
# x=int(input('x='))
# print(my_abs(x))
# import math

# def move(x,y,step,angle=0):
# 	nx=x+step*math.cos(angle)
# 	ny=y-step*math.sin(angle)
# 	return nx,ny
# def quadratic(a,b,c):
# 	m=b**2-4*a*c
# 	x1=(math.sqrt(m)-b)/(2*a)
# 	x2=(-math.sqrt(m)-b)/(2*a)
# 	return x1,x2
# print(quadratic(2,3,1))
# print(quadratic(1,3,-4))

#汉诺塔游戏
def move(n,a,b,c):
	if n==1:
		print(a,'-->',c)
	else:
		move(n-1,a,c,b)
		print(a,'-->',c)
		move(n-1,b,a,c)
	return None


	