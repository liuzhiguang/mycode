#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-05-19 16:12:55
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

try:
    import xml.etree.cElementTree as ET
except ImportError:
    import xml.etree.ElementTree as ET


def process_buffer(buf):
    tree = ET.fromstring(buf)
    attrib = tree.attrib
    print(attrib)
    return attrib

def process_buffer_NAME(buf):
    tree = ET.fromstring(buf)
    tag = tree.tag
    text = tree.text.strip()
    return tag, text

str1 = ''
temp = ''
def complie(buf):
    context = process_buffer_NAME(buf)
    str1 += temp +'<=' + 'namedObjectId<' + text + '(' + context[tag] + '>' +'\n\r'
    temp = text
    
inputbuffer = ''
# with open('D:\\big_box\\data\\PDB2.2\\NDS\\CN\\cw19\\Beijing.xml', 'r', encoding='utf8') as inputfile:
    # append = False
    # for line in inputfile:
    #     if '<CITY ' in line and '>' in line:
    #         inputbuffer = line.replace('">', '"/>')
    #         append = False
    #         process_buffer(inputbuffer)
    #         inputbuffer = None
    #         # del inputbuffer
    #     elif '<POI' in line and '">' in line:
    #         inputbuffer = line
    #         append = False
    #         inputbuffer = line.replace('">', '"/>')
    #         process_buffer(inputbuffer)
    #         inputbuffer = None
    #         # del inputbuffer
    #     elif append:
    #         inputbuffer += line

with open('D:\\big_box\\data\\PDB2.2.1\\CN\\CFG\\Configuration.xml','r',encoding = 'utf8') as inputfile1:
    append = False
    for line in inputfile1:
        if '<NameMid>' in line:
            pass
        if append:
            inputbuffer = line
            process_buffer(inputbuffer)
            inputbuffer = None
        if '<StartNamedObjectId>' in line:
            append = True
        
        if '</StartNamedObjectId>' in line:
            append = False

    #root = ET.fromstring()
    # root = tree.getroot()
# except Exception as e:
#     print()
#     sys.exit(1)
# state = tree.find("STATE").text
# print(state)
# {'WGS_LATITUDE': '39.868491', 'NAME': '神州\xad租车\xad(\xad北京\xad南站\xad店\xad)', 'POI_TYPE': '4736', 'NATIONAL_IMPORTANCE': '0', 'WGS_LONGITUDE': '116.382395', 'LANG': '40', 'POI_ID': '172700', 'STREET': '地铁4号线北京南站北广场东侧200米路北'}

