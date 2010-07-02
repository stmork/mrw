/*
**
**	$Id: CAN_Node.h 944 2010-04-23 07:01:13Z smork $
**	$Revision: 944 $
**	$Date: 2010-04-23 09:01:13 +0200 (Fr, 23. Apr 2010) $
**	$Author: smork $
**
**	Modellrailway state chart generation CAN-Node.
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

#ifndef CAN_NODE_H
#define CAN_NODE_H

#include "mcp2515.h"

#define CAN_NODE__INITIAL 0

/*******************************************************************
Dieser Zustand entspricht dem normelen Betriebsmodus. Hier werden
Steuerkommandos verarbeitet.
*******************************************************************/

#define CAN_NODE__OPERATING 1

/*******************************************************************
Im Konfigurationsmodus werden Konfigurationskommandos
verarbeitet. Dieser Zustand kann nur in einen Reset �berf�hrt
werden. Durch den Reset wird dann die hardware neu
initialisiert.
*******************************************************************/

#define CAN_NODE__CONFIGURING 2

/*******************************************************************
Dieser Zustand ist ein �bergangszustand. Es wurde ein Watchdog Reset
ausgel�st. Dieser wird erst nach einer Sekunde durchgef�hrt. W�hrend
dessen ist der Mikrocontroller in diesem Zustand und kann keine Aufgaben
durchf�hren.
*******************************************************************/

#define CAN_NODE__RESETTING 3

/*******************************************************************
Dieser Betriebsmodus wird nach dem Booten erreicht, wenn
keine g�ltige Konfiguration im EEPROM abgelegt ist. Von hier
aus kann nur konfiguriert oder gebootet werden.
*******************************************************************/

#define CAN_NODE__UNCONFIGURED 4

extern int8_t get_state_CAN_Node(void);
extern void   init_CAN_Node(void);
extern void   CAN_Node(CAN_message *msg);
#endif
