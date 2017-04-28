# coding=utf-8
import urllib


def getHtml(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html
    html = getHtml("http://www.baidu.com")
    print(html)

isinstance([1, 2, 3], Iterable)
isinstance(123, Iterable)

for i, value in enumerate(['A', 'B', 'C']):
    print(i, value)

for x, y in[(1, 1), (2, 4), (3, 9)]:
    print(x, y)

[x * x for x in range(1, 11)]
[x for x in range(1, 11)]
[x * x for x in range(1, 11) if x % 2 == 0]
[a + b for a in '12w345' for b in '6q78e']

import os
[d for d in od.listdir('.')]

d = {'x': 'A', 'y': 'B', 'z': 'C'}
[k + '=' + v for k, v in d.items()]

L = ['ADF', 'NDS', 'JunctionView']
[l.lower() for l in L]

x = 'qwe'
y = 222
isinstance(x, str)
isinstance(y, str)

L = ['ADF', 1232, 'NDS', 'JunctionView', 123]
[l.lower() for l in L if isinstance(l, str)]

g = (x for x in range(10))
for n in g:
    print(n)


def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1
    return 'done'
