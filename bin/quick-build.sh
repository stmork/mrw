#!/bin/bash

set -e

CPUS=`nproc`

cd Lichtprofile
ant clean run
cd ../Steuerung
ant clean package
cd ../AnlageYakindu
ant clean generate package
cd ../AnlageZwei
ant clean generate package
cd ../Stellwerk
ant clean package javadoc

cd ../Microcontroller
ant clean generate

cd ../mc
make clean depend
make -j $CPUS
cd ../canprog
make clean depend
make  -j $CPUS
cd ../tools
make clean depend
make -j $CPUS
cd ../daemon
make clean depend
make -j $CPUS
