/*
**
**	$Filename:	RGB.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Simple RGB diode light testing.
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

#ifndef F_CPU
#define F_CPU 16000000UL
#endif

#include <stdlib.h>
#include <avr/io.h>
#include <avr/sleep.h>

#include "config.h"
#include "eeprom.h"
#include "light.h"
#include "mcp2515.h"
#include "pwm.h"
#include "serial.h"
#include "sleep.h"
#include "timer.h"

#define no_BUSY_TEST

#define NODE_READY     MCP_LED_GREEN

#define INDEX_B1 3
#define INDEX_R  2
#define INDEX_B2 1
#define INDEX_G  0

#define PORT_BUSY  PORTC
#define DDR_BUSY   DDRC
#define PIN_BUSY     7
#define PIN_INT1     6
#define PIN_INT2     5
#define PIN_INT3     4
#define PIN_READY    0

#define BUSY_MAIN   (PORT_BUSY = (PORT_BUSY & ~(_BV(PIN_BUSY) | _BV(PIN_INT1) | _BV(PIN_INT2) | _BV(PIN_INT3))) | _BV(PIN_BUSY))
#define BUSY_INT1   (PORT_BUSY |= (_BV(PIN_BUSY) | _BV(PIN_INT1)))
#define BUSY_INT2   (PORT_BUSY |= (_BV(PIN_BUSY) | _BV(PIN_INT2)))
#define BUSY_INT3   (PORT_BUSY |= (_BV(PIN_BUSY) | _BV(PIN_INT3)))
#define READY       (PORT_BUSY |=  _BV(PIN_READY))
#define IDLE        (PORT_BUSY &= ~(_BV(PIN_BUSY) | _BV(PIN_INT1) | _BV(PIN_INT2) | _BV(PIN_INT3)))

/**
 * Diese ISR holt eine CAN-Meldung aus dem MCP2515 ab. Die
 * Meldung wird ignoriert.
 */
ISR(INT2_vect)
{
	BUSY_INT1;
	CAN_message msg;

	while(can_get_msg(&msg) >= 0)
	{
	}
	BUSY_MAIN;
}

/**
 * Diese ISR wird aufgerufen, wenn eine DA-Wandlung durchgeführt
 * wurde. Der gewandelte Wert wird als Helligkeitswert interpretiert
 * und dementsprechend werden die konfigurierten Lampen angesteuert.
 */
ISR(TIMER1_COMPA_vect)
{
	BUSY_INT2;
	mrw_device *dvc = config.dvc;
	uint8_t     i;

	for (i = 0;i < config.count; i++)
	{
		if (IS_PWM_DIMM(&dvc->unit.u_light))
		{
			handle_pwm(&dvc->unit.u_light);
		}
		dvc++;
	}
	BUSY_MAIN;
}

static uint8_t counter;
static uint8_t state;

#ifdef BUSY_TEST
static uint8_t busy_test_values[4] =
{
	0, 85, 170, 255
};
#endif

/**
 * Über diesen Timer ISR werden fortlaufend die Warbwerte
 * der RGB-LEDs angesteuert. Es wird kontinuierlich zwischen
 * Rot, Grün und Blau ein Farbwechsel interpoliert. Das
 *
 * Ergebnis kann bei YouTube eingesehen werden:
 * https://www.youtube.com/watch?v=oTN2_jRfX8c
 */
ISR(TIMER2_OVF_vect)
{
	BUSY_INT3;
#ifdef BUSY_TEST
	for(uint8_t idx = 0;idx < 8; idx++)
	{
		set_dimm(&config.dvc[idx].unit.u_light, busy_test_values[state & 3]);
	}
#else
	uint8_t inv = ~counter;

	switch(state & 15)
	{
	case  0: // R B1 hoch
		set_dimm(&config.dvc[INDEX_R].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_B1 + 4].unit.u_light, counter);
		break;

	case  1: // G R hoch
		set_dimm(&config.dvc[INDEX_G    ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_R + 4].unit.u_light, counter);
		break;

	case  2: // R B1 runter
		set_dimm(&config.dvc[INDEX_R     ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_B1 + 4].unit.u_light, inv);
		break;

	case  3: // B G hoch
		set_dimm(&config.dvc[INDEX_B1   ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_G + 4].unit.u_light, counter);
		break;

	case  4: // G R runter
		set_dimm(&config.dvc[INDEX_G    ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_R + 4].unit.u_light, inv);
		break;

	case  5: // R B2 hoch
		set_dimm(&config.dvc[INDEX_R     ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_B2 + 4].unit.u_light, counter);
		break;

	case  6: // B G runter
		set_dimm(&config.dvc[INDEX_B1   ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_G + 4].unit.u_light, inv);
		break;

	case  7: // R B2 runter
		set_dimm(&config.dvc[INDEX_R     ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_B2 + 4].unit.u_light, inv);
		break;

	case  8:
		set_dimm(&config.dvc[INDEX_B1   ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_R + 4].unit.u_light, counter);
		break;

	case  9:
		set_dimm(&config.dvc[INDEX_R     ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_B2 + 4].unit.u_light, counter);
		break;

	case 10:
		set_dimm(&config.dvc[INDEX_G    ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_G + 4].unit.u_light, counter);
		break;

	case 11:
		set_dimm(&config.dvc[INDEX_B2    ].unit.u_light, counter);
		set_dimm(&config.dvc[INDEX_B1 + 4].unit.u_light, counter);
		break;

	case 12:
		break;

	case 13:
		set_dimm(&config.dvc[INDEX_G     ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_B1 + 4].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_B2 + 4].unit.u_light, inv);
		break;

	case 14:
		set_dimm(&config.dvc[INDEX_R     ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_R + 4].unit.u_light, inv);
		break;

	case 15:
		set_dimm(&config.dvc[INDEX_B2   ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_B1   ].unit.u_light, inv);
		set_dimm(&config.dvc[INDEX_G + 4].unit.u_light, inv);
		break;
	}
#endif

	counter+=8;
	if (counter == 0)
	{
		state++;
	}
	BUSY_MAIN;
}

/**
 * Diese Methode initialisiert die Device Konfiguration.
 */
static void config_init(void)
{
	mrw_device *dvc = config.dvc;
	uint8_t     i;

	for (i = 0;i < 12;i++)
	{
		dvc->unit_no                = i + 1;
		dvc->unit_type              = TYPE_LIGHT;
		dvc->unit.u_light.threshold = 0;
		dvc->unit.u_light.type      = 0;
		config_connection(&dvc->unit.u_light.pin, i);

		light_init(&dvc->unit.u_light);
		set_dimm  (&dvc->unit.u_light, 0);
		dvc++;
	}
	config.count = i;
}

/**
 * Die Hauptmethode initialisiert die Hardware und
 * macht im Betrieb nichts außer schlafen. D.h., die
 * Arbeit wird nur in den ISRs erledigt :-) 
 */
int main(void)
{
	DDR_BUSY  = 0xff;
	PORT_BUSY = 0;
	BUSY_MAIN;

	read_eeprom_config(&config);

	/* Takt initialisieren, so dass MCP2515 seine 16 MHz auch her gibt. */
	/* Ansonsten passiert hier heute per CAN-Bus nix ;-) */
	mcp2515_init(config.id, 1, MCP2515_SINGLE_TX_BUFFER);
	config_init();

#if 0
	set_dimm(&config.dvc[INDEX_R ].unit.u_light, 128);
	set_dimm(&config.dvc[INDEX_G ].unit.u_light, 255);
	set_dimm(&config.dvc[INDEX_B1].unit.u_light,  16);
	set_dimm(&config.dvc[INDEX_B2].unit.u_light,  16);
	
	set_dimm(&config.dvc[INDEX_R + 4].unit.u_light, 208);
	set_dimm(&config.dvc[INDEX_G + 4].unit.u_light, 240);
#else
	timer2_init();
#endif

	timer1_init(F_CPU / (50 * PWM_TABLE_SIZE));

	serial_init();
	mcp2515_write_rx_output_pins(NODE_READY);

	set_sleep_mode(SLEEP_MODE_IDLE);

	READY;
	do
	{
		/*
		 * Ein bißchen was für die Umwelt tun. Es fängt halt
		 * halt schon im Kleinen an ;-)
		 */
		IDLE;
		sleep();
		BUSY_MAIN;
	}
	while(1);
}
