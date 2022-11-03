#!/bin/bash

rm -rf */bin api */*.emma */TEST*.xml
rm -rf *.deb target/

for DIR in mc canprog tools daemon
do
   make -C ${DIR} clean
done