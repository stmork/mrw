#!/bin/bash

set -e

cd Steuerung
ant spotbugs

cd ../Stellwerk
ant spotbugs

cd ../AnlageZwei
ant spotbugs

cd ../Lichtprofile
ant spotbugs
