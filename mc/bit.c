/*
**
**	$Filename:	bit.c $
**	$Release:	Dortmund 2010 $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Modellrailway micro controller
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

#include <stdint.h>

#include "bit.h"
#include "serial.h"

uint8_t set_bits[8] =
{
	0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80
};

uint8_t clr_bits[8] =
{
	0xfe,0xfd,0xfb,0xf7,0xef,0xdf,0xbf,0x7f
};

void set_pin(mrw_connection *con)
{
	register uint8_t bit   = CONNECTION_BITS(con->bit);
	register uint8_t port  = CONNECTION_PORT(con->bit);
	register uint8_t value = set_bits[bit];

	switch(port)
	{
	case  0:
		PORTA |= value;
		break;
	case  8:
		PORTC |= value;
		break;
	case 16:
		PORTD |= value;
		break;
	}
}

void clr_pin(mrw_connection *con)
{
	register uint8_t bit  = CONNECTION_BITS(con->bit);
	register uint8_t port = CONNECTION_PORT(con->bit);
	register uint8_t mask = clr_bits[bit];

	switch(port)
	{
	case  0:
		PORTA &= mask;
		break;
	case  8:
		PORTC &= mask;
		break;
	case 16:
		PORTD &= mask;
		break;
	}
}

void out_pin(mrw_connection *con)
{
	register uint8_t port  = CONNECTION_PORT(con->bit);
	register uint8_t bit   = CONNECTION_BITS(con->bit);
	register uint8_t value = set_bits[bit];

	switch(port)
	{
	case  0:
		DDRA |= value;
		break;
	case  8:
		DDRC |= value;
		break;
	case 16:
		DDRD |= value;
		break;
	}
}

void in_pin(mrw_connection *con, uint8_t pullup)
{
	register uint8_t port  = CONNECTION_PORT(con->bit);
	register uint8_t bit   = CONNECTION_BITS(con->bit);
	register uint8_t mask  = clr_bits[bit];

	switch(port)
	{
	case  0:
		DDRA  &= mask;
		break;
	case  8:
		DDRC  &= mask;
		break;
	case 16:
		DDRD  &= mask;
		break;
	}
	
	if (pullup)
	{
		uint8_t value = set_bits[bit];

		switch(port)
		{
		case  0:
			PORTA |= value;
			break;
		case  8:
			PORTC |= value;
			break;
		case 16:
			PORTD |= value;
			break;
		}
	}
	else
	{
		switch(port)
		{
		case  0:
			PORTA &= mask;
			break;
		case  8:
			PORTC &= mask;
			break;
		case 16:
			PORTD &= mask;
			break;
		}
	}
}

uint8_t tst_pin(mrw_connection *con)
{
	register uint8_t port  = CONNECTION_PORT(con->bit);
	register uint8_t bit   = CONNECTION_BITS(con->bit);
	register uint8_t mask  = set_bits[bit];

	switch(port)
	{
	case  0:
		return PINA & mask;
	case  8:
		return PINC & mask;
	case 16:
		return PIND & mask;
	}
	return 0;
}

void config_connection(mrw_connection *con, uint8_t con_no)
{
	con->bit = con_no < 8 ? 7 - con_no : con_no;
}

void init_input(mrw_input *input, uint8_t pullup)
{
	in_pin(&input->connection, pullup);
	input->counter    = 0;
	input->last_state =
	input->state      = tst_pin(&input->connection) != 0;
}

int8_t test_state_change(mrw_input *input)
{
	uint8_t state  = tst_pin(&input->connection) != 0;
	int8_t  result = PIN_NO_CHANGE;

	if (input->last_state != state)
	{
		// OK, Zustands�nderung seit dem letzten Mal. Entprellen!
		input->last_state = state;
		input->counter = DEPRELL_COUNT;
	}
	else
	{
		// Ist Entprellen im Gange?
		if (input->counter > 0)
		{
			// Ja!
			input->counter--;

			// Taste w�hrend des Entprellens
			if (input->counter == 0)
			{
				// OK, abgez�hlt = stabilisiert. Jetzt muss nur noch
				// getestet werden, ob der stabilisierte Zustand sich
				// vom Zustand vor dem Entprellen unterscheidet.
				if (input->state != state)
				{
					// Zustand nur melden, wenn sich endg�ltig
					// Zustand ge�ndert hat!
					input->state = state;
					result = state ? PIN_RISING_EDGE : PIN_FALLING_EDGE;
				}
			}
		}
	}
	return result;
}
