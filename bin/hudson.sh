#!/bin/bash

set -e

cd Steuerung
ant clean package
cd ../AnlageYakindu
ant clean generate package
cd ../AnlageZwei
ant clean generate package
cd ../Stellwerk
ant clean package javadoc
#ant deploy

cd ../Microcontroller
ant clean generate

cd ../mc
make clean depend
make 
cd ../canprog
make clean depend
make 
