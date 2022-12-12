# Modell(-bau-)basierte Eisenbahnsteuerung

Herzlich Willkommen auf den Informationsseiten der modell(-bau-)basierten
Eisenbahnsteuerung! Auf diesen Seiten wird die Entwicklung einer
eigenständig entwickelten Eisenbahnsteuerung dargestellt. Alle Ergebnisse
können auf diesen Seiten als Open Source (Eclipse Public Licence) eingesehen
werden. Schaut Euch den Quellcode an und nehmt Einblick in die
technischen Hintergründe des CAN-Bus und Microcontroller-Programmierung mit
dem ATmega32. Die ausführliche API bietet weiteres Hintergrundmaterial.
Schauen Sie sich auch die vielen
[Videos auf YouTube](https://www.youtube.com/playlist?list=PL6E0B186E9CC98026) an!

Viel Spaß!!

## Build- und Modellarchitektur

```mermaid
flowchart TB
subgraph Eclipse
 Modelrailway.umlclass -- converts --> Ecore --> GenModel
 GenModel --> Modelleditor-Plugin -- edit --> MRW.modelrailway
 Ecore --> Modelleditor-Plugin
 Ecore --> MRW-Generator --> MRW-Classes
 Xtext --> MRW-Generator
 MRW.modelrailway -- generates --> MRW-Classes -- compiles --> Control-Plugin
 end
 Control-Plugin -- deploys --> Application
 subgraph Eclipse-RPC-Application
 Application -. uses .-> Signal.properties
 Application -. uses .-> Gruppe.properties
 Application -. uses .-> Gleisteile.properties
 end

```
