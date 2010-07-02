#!/bin/bash

if [ ! -d bestellung ]; then 
	echo "lege bestellungsverzeichnis an"
	mkdir bestellung
fi

echo "Kopiere die Dateien"
cp railsegments.{sch,brd} bestellung
cp signal.{sch,brd} bestellung
cp switch.{sch,brd} bestellung
cp mc.{sch,brd} bestellung
cp README.txt bestellung

MYDATE=`date +%F-%H-%M`
ZIPNAME=platinen-itemis-$MYDATE.zip

echo "Erzeuge Datei $ZIPNAME"

echo "Packe zusammen nach platinen-itemis.zip"
zip  $ZIPNAME bestellung/*


echo "entferne Verzeichnis"
rm bestellung/*.*
rmdir bestellung 



echo "Fertig."


