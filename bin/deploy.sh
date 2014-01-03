#!/bin/bash

MRW=${MRW:=AnlageYakindu}

echo "Deploying $MRW..."

cd Steuerung
ant clean package || exit 1
cd ../$MRW || exit 1
ant clean generate deploy || exit 1
cd ../Stellwerk
ant clean deploy || exit 1
