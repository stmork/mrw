#!/bin/bash

set -e

cd Steuerung
ant findbugs

cd ../Stellwerk
ant findbugs

cd ../AnlageZwei
ant findbugs

cd ../Lichtprofile
ant findbugs
