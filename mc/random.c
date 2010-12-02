/*
**
**	$Filename:	random.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Compute random number
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

#include "random.h"

#include <avr/io.h>

void random_preinit(void)
{
	/* Kein Prescaling */
	TCCR0 = _BV(CS00);
}

void random_postinit(void)
{	
	uint8_t *ptr = (uint8_t *)(RAMEND & 0xff);
	uint8_t  l = 0;

	/*
	 * Initialisierung mit Zufallszahlen. Das RAM scheint hierf�r
	 * am Besten geeignet zu sein.
	 */
	for (uint8_t h = RAMEND >> 8; h > 0; h--)
	{
		do
		{
			TCNT0 += *ptr++;
			
			l++;
		}
		while(l != 0);
	}
}

uint8_t random_timer(void)
{
	return TCNT0 >> 1;
}
