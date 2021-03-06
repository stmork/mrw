/*
**
**	$Filename:	can_processing.h  $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Schnittstelle zur CAN Message Verarbeitung.
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

#ifndef CAN_PROCESSING_H
#define CAN_PROCESSING_H

#include "mcp2515.h"

extern int8_t isConfiguring(void);
extern int8_t isResetting(void);

#endif
