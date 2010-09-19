/*
**
**	$Filename:	pwm.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	PWM soft handler
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

#include "pwm.h"

#include <inttypes.h>
#include <avr/pgmspace.h>

static uint8_t quotient[8];
static uint8_t dimm[8];
static uint8_t nom[8];
static uint8_t denom[8];

/* Z�hlertabelle */
static const uint8_t table_nom[PWM_TABLE_SIZE] PROGMEM =
{
	0x00, 0x01, 0x01, 0x03, 0x01, 0x05, 0x03, 0x07, 0x01, 0x09, 0x05, 0x0b, 0x03, 0x0d, 0x07, 0x0f,
	0x01, 0x11, 0x09, 0x13, 0x05, 0x15, 0x0b, 0x17, 0x03, 0x19, 0x0d, 0x1b, 0x07, 0x1d, 0x0f, 0x1
};

/* Nennertabelle*/
static const uint8_t table_denom[PWM_TABLE_SIZE] PROGMEM =
{
	0x20, 0x20, 0x10, 0x20, 0x08, 0x20, 0x10, 0x20, 0x04, 0x20, 0x10, 0x20, 0x08, 0x20, 0x10, 0x20,
	0x02, 0x20, 0x10, 0x20, 0x08, 0x20, 0x10, 0x20, 0x04, 0x20, 0x10, 0x20, 0x08, 0x20, 0x10, 0x1
};

/*
 * Hier wird aus einer Tabelle Z�hler und Nenner f�r die Helligkeit ausgelesen. Wichtig hier
 * ist, dass die Werte aus dem Flash geholt werden (const/PROGMEM), um RAM zu sparen.
 */
void set_dimm(uint8_t idx, uint8_t value)
{
	value >>= 3;
	nom[idx]   = pgm_read_byte(&table_nom[value]);
	denom[idx] = pgm_read_byte(&table_denom[value]);
	dimm[idx] = value;
	quotient[idx] = 0;
}

/*
 * Das hier ist die Soft-PWM-Routine. Sie kann acht Kan�le gleichzeitig unabh�ngig
 * voneinander bearbeiten. Der Trick, hier ohne langsame Division auszukommen ist,
 * dass so �hnlich wie beim Linienzeichnen (Gfx!!), die Steigung durch Ann�herung
 * nachgehalten wird. Da die Steigung immer zwischen [0.0, 1.0] ist, ist die Steigung
 * immer flach, was den Algorithmus vereinfacht. Diese Routine d�rfte nicht mehr
 * als 300 Takte brauchen.
 */
void handle_pwm(void)
{
	uint8_t pattern = 0;
	 int8_t p;

	for (p = 7; p >= 0; p--)
	{
		pattern += pattern;
		if (quotient[p] < nom[p])
		{
			pattern |= 1;
			quotient[p] += denom[p];
		}
		quotient[p] -= nom[p];
	}
	PORT_LIGHT = pattern;
}
