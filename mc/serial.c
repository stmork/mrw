/*
**
**	$Filename:	serial.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Serial shift handler
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

#include "serial.h"
#include "tool.h"

#define DATA0 { PORT_SERIAL &= (~SER_DATA); }
#define DATA1 { PORT_SERIAL |=   SER_DATA; }

#define CLK0  { PORT_SERIAL &= (~SER_CLK); }
#define CLK1  { PORT_SERIAL |=   SER_CLK; }

#define SET0  { PORT_SERIAL &= (~SER_SET); }
#define SET1  { PORT_SERIAL |=   SER_SET; }

       uint8_t serial_buffer[MAX_SERIAL_BUFFER];
static uint8_t start_idx = sizeof(serial_buffer);

/**
 * Diese Methode initialisiert die für die Schieberegister nötigen Portbits. Der
 * Schiebepuffer wird dabei ebenfalls gelöscht und versendet.
 */
void serial_init(void)
{
	DDR_SERIAL |= SER_CLK | SER_DATA | SER_SET;
	clear_serial_buffer();
//	send_serial_buffer();
}

/**
 * Diese Methode schreibt ein einzelnes Byte in die Schieberegister.
 */
static void serial_putc(uint8_t val)
{
	for (uint8_t bit = 1; bit != 0; bit = bit << 1)
	{
		
		if (val & bit)
		{
			DATA1;
		}
		
		CLK1;
		CLK0;
		DATA0;
	}
}

/**
 * Diese Methode schreibt einen kompletten Puffer in die Schieberegister.
 */
void serial_put_buffer(uint8_t *buffer, uint8_t len)
{
	DATA0;
	while (len > 0)
	{
		serial_putc(*buffer++);
		len--;
	}

	// Serielles Schieberegister in parallele Ausgabepuffer übernehmen.
	SET0;
	NOP;
	SET1;
}

/**
 * Diese Methode löscht den Sendepuffer und sendet ihn gleichzeitig
 * über die Schieberegister.
 */
void clear_serial_buffer(void)
{
	uint8_t *buffer = serial_buffer;

	DATA0;
	for (uint8_t i = sizeof(serial_buffer); i > 0; i--)
	{
		*buffer++ = 0;
		for (uint8_t bit = 8; bit > 0; bit--)
		{
			CLK1;
			CLK0;
		}
	}

	// Serielles Schieberegister in parallele Ausgabepuffer übernehmen.
	SET0;
	NOP;
	SET1;
}

/**
 * Diese Methode gleicht die Größe des Sendepuffers ab. Ist
 * die Größe 0, gibt es auch nichts zu senden.
 */
void serial_limit(uint8_t idx)
{
	if (idx < start_idx)
	{
		start_idx = idx;
	}
}

/**
 * Diese Methode sendet den Buffer. Es werden nur die Bytes
 * gesendet, die für das Senden auch gebraucht werden.
 */
void send_serial_buffer(void)
{
	serial_put_buffer(&serial_buffer[start_idx], sizeof(serial_buffer) - start_idx);
}
