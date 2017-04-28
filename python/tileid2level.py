#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2017-04-05 18:52:09
# @Author  : lzg (wb-lzg228465@autonavi.com)
# @Link    : ${link}
# @Version : $Id$

import os

DEF_NDS_BASE_LEVEL = 16


def get_level_from_tile_id(tile_id):
    level = None
    tile_number = None
    for i in range(15, -1, -1):
        judge_lv = (tile_id & (1 << (i + DEF_NDS_BASE_LEVEL)))

        if judge_lv != 0:
            level = i

            mask = (1 << (level * 2 + 1)) - 1
            tile_number = tile_id & mask
            break

    return level, tile_number

tile_id = 4195907

result = get_level_from_tile_id(tile_id)

print(result[0])
print(result[1])

# strl=1<<16
# print(strl)
# print(bin(strl))
