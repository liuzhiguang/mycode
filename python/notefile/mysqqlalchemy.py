#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-11 10:29:58
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import sqlite3
from sqlalchemy import Column, String, create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class User(Base):
    __tablename__ = 'user'

    id = Column(String(20), primary_key = True)
    name = Column(String(20))

engine = create_engine('sqlite3 + test.db')
DBSession = sessionmaker(engine)
        
