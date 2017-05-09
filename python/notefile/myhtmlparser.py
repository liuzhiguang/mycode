#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-09 17:24:19
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from html.parser import HTMLParser
from html.entities import name2codepoint

import urllib.request


class MyHTMLParser(HTMLParser):

    def __init__(self):
        super(MyHTMLParser, self).__init__()
        self.cout = 0
        self.python_event = {}
        self.li = False
        self.span1 = False
        self.span2 = False
        self.time = False
        self.h3 = False
        self.p = False
        self.a = False

    def handle_starttag(self, tag, attrs):
        if tag == 'li':
            self.li = True
        if tag == 'h3':
            for k, v in attrs:
                if k == 'class' and v == 'event-title':
                    self.h3 = True
        if tag == 'a':
            self.a = True
        if tag == 'p':
            self.p = True
        if tag == 'time':
            self.time = True
        if tag == 'span':
            for k, v in attrs:
                if k == 'class':
                    if v == 'say-no-more':
                        self.span1 = True
                    elif v == 'event-location':
                        self.span2 = True

    def handle_endtag(self, tag):
        if tag == 'li':
            self.li = False
        if tag == 'h3':
            self.h3 = False
        if tag == 'a':
            self.a = False
        if tag == 'p':
            self.p = False
        if tag == 'time':
            self.time = False
        if tag == 'span':
            self.span1 = False
            self.span2 = False

    def handle_startendtag(self, tag, attrs):
        pass

    def handle_data(self, data):
        if self.li:
            if self.h3 == True and self.a == True:
                self.cout += 1
                self.python_event[self.cout] = {}
                self.python_event[self.cout]['name'] = data

            elif self.p:
                if self.time:
                    if not self.span1:
                        self.python_event[self.cout]['time'] = data
                    else:
                        self.python_event[self.cout]['time'] += (',' + data)
                else:
                    if self.span2:
                        self.python_event[self.cout]['site'] = data

    def handle_comment(self, data):
        pass

    def handle_entityref(self, name):
        pass

    def handle_charref(self, name):
        pass


class pre(object):

    def gethtml(url):
        response = urllib.request.urlopen(url)
        a = response.read()
        return a

if __name__ == '__main__':
    url = 'https://www.python.org/events/python-events/'
    htmldata = pre.gethtml(url)
    htmldata = str(htmldata, 'utf-8')
    parser = MyHTMLParser()
    # parser.feed('''<html>
    # <head></head>
    # <body>
    # <!-- test html parser -->
    #     <p>Some <a href=\"#\">html</a> HTML&nbsp;tutorial...<br>END</p>
    # </body></html>''')
    parser.feed(htmldata)
    event = parser.python_event
    for i in range(1, parser.cout+1):
        print(event[i]['name'],'\n',event[i]['time'],'\n',event[i]['site'])
