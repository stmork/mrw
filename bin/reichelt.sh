#!/bin/bash

tail -n+2 $1 | sed -e s/\"//g -e s/\;/\,/g |fgrep -v ",,,,,,,"| cut -d"," -f 2,8|head -n-2
