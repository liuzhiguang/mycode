#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-28 14:31:28
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$
import pdb
import logging
logging.basicConfig(level = logging.INFO)

def function(self, name='world'):
    print('hello,%s' % name)


Hello = type('Hello', (object,), dict(hello=function))
# h = Hello()
# h.hello()


# class ListMetaclass(type):
#     def __new__(cls, name, bases, attrs):
#         attrs['add'] = lambda self, value: self.append(value)
#         return type.__new__(cls, name, bases, attrs)
def __new__(cls, name, bases, attrs):
    attrs['add'] = lambda self, value: self.append(value)
    attrs['dele'] = lambda self, value: self.remove(value)
    return type.__new__(cls, name, bases, attrs)

ListMetaclass = type('ListMetaclass', (type,), dict(__new__=__new__))


class Mylist(list, metaclass=ListMetaclass):
    def __init__(self, args=1):
        self._args = args


# def __init__(self,args = 1):
#     self._args = args

# metaclass=ListMetaclass
# Mylist = type('Mylist', (list,metaclass,),dict(__init__=__init__))


class Field(object):
    """docstring for Field"""

    def __init__(self, name, column_type):
        self.name = name
        self.column_type = column_type

    def __str__(self):
        return '<%s:%s>' % (self.__class__.__name__, self.name)


class StringField(Field):
    def __init__(self, name):
        super(StringField, self).__init__(name, 'varchar(100)')


class IntegerField(Field):
    """docstring for IntegerField"""

    def __init__(self, name):
        super(IntegerField, self).__init__(name, 'bigint')


class ModelMetaclass(type):
    def __new__(cls, name, bases, attrs):
        if name == 'Model':
            return type.__new__(cls, name, bases, attrs)
        print('Found model %s' % name)
        mappings = dict()
        for k, v in attrs.items():
            if isinstance(v, Field):
                print('Found mapping:%s==%s' % (k, v))
                mappings[k] = v
        for k in mappings.keys():
            attrs.pop(k)
        attrs['__mappings__'] = mappings
        attrs['__table__'] = name
        pdb.set_trace()
        return type.__new__(cls, name, bases, attrs)


class Model(dict, metaclass=ModelMetaclass):
    """docstring for Model"""

    def __init__(self, **kw):
        super(Model, self).__init__(**kw)

    def __getattr__(self, key):
        try:
            return self[key]
        except KeyError:
            raise AttributeError(
                r"'model' object has no attribute ' % s'" % key)

    def __setattr__(self, key, value):
        self[key] = value

    def save1(self):
        fields = []
        params = []
        args = []
        for k, v in self.__mappings__.items():
            fields.append(v.name)
            params.append("?")
            args.append(getattr(self, k, None))
        sql = 'insert into %s (%s) value (%s)' % (
            self.__table__, ','.join(fields), ','.join(params))
        print('SQL:%s' % sql)
        print('ARGS:%s' % str(args))


class User(Model):
    id = IntegerField('id')
    name = StringField('username')
    email = StringField('email')
    password = StringField('password')


if __name__ == '__main__':
    # function()
    h = Hello()
    h.hello()
    print(type(Hello))
    print(type(h))
    L = Mylist()
    L.add(1)
    L.add(2)
    L.dele(1)
    print(L)
    print(type(L))
    u = User(id=12345, name='name', email='test@orm.org', password='my-pwd')
    u.save1()
    logging.info('a=%s'%'a')
