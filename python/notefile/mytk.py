#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-10 11:44:33
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

from tkinter import *
import tkinter.messagebox as messagebox
class Application(Frame):
    """docstring for Application"""
    def __init__(self, master = None):
        Frame.__init__(self, master)
        self.pack()
        self.createWidgets()

    # def createWidgets(self):
    #     self.helloLabel = Label(self, text = 'hello, world!')
    #     self.helloLabel.pack()
    #     self.quitButton = Button(self, text = 'Quit', command = self.quit)
    #     self.quitButton.pack()
    def createWidgets(self):
        self.nameInput = Entry(self)
        self.nameInput.pack()
        self.alterButton = Button(self, text = 'hello', command = self.hello)
        self.alterButton.pack()

    def  hello(self):
        name = self.nameInput.get() or 'bob'
        messagebox.showinfo('Message', 'Hello, %s' % name)

app = Application()
app.master.title('')
app.mainloop()