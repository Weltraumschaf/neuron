#!/bin/bash

#
# LICENSE
#
# "THE BEER-WARE LICENSE" (Revision 43):
# "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
# As long as you retain this notice you can do whatever you want with
# this stuff. If we meet some day, and you think this stuff is worth it,
# you can buy me a non alcohol-free beer in return.
#
# Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
#

BASE_DIR="$(dirname $0)"
java -jar "${BASE_DIR}/neuron.jar" $*