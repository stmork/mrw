/*
**
**	$Filename:	gateway.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	CAN/RS232 gateway
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
#define F_CPU 14745600L
#endif

#include <stdlib.h>
#include <stddef.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#include "mcp2515.h"
#include "uart.h"
#include "sleep.h"
#include "bit.h"
#include "tool.h"
#include "can_ring.h"

#define MAX_MSG        32
#define MAX_MSG_MASK ((MAX_MSG)-1)

#define MAX_CAN_LENGTH (offsetof(CAN_message, data[8])+2)

#define PORT_OVL   PORTA
#define DDR_OVL    DDRA
#define PIN_OVL    PINA
#define P_OVL_CAN  0
#define P_OVL_UART 1
#define OVL_CAN    _BV(P_OVL_CAN)   // red
#define OVL_UART   _BV(P_OVL_UART)  // green

#define UART_OVL_WARNING_LEVEL 192

/*
 * CAN receive Ring Buffer
 */
static struct can_ring rx_ring;

/*
 * CAN transmit Ring Buffer
 */
static struct can_ring tx_ring;

/*
 * UART receive Ring Buffer
 */
static volatile uint8_t     uart_rx_buffer[256];
static volatile uint8_t     uart_rx_start = 0;
static volatile uint8_t     uart_rx_pos   = 0;
static volatile int16_t     uart_rx_count = 0;
static volatile uint16_t    uart_received = 0;

/*
 * UART transmit Ring Buffer
 */
static volatile uint8_t     uart_tx_buffer[256];
static volatile uint8_t     uart_tx_start = 0;
static volatile uint8_t     uart_tx_pos   = 0;
static volatile int16_t     uart_tx_count = 0;
static volatile uint16_t    uart_transmitted = 0;

/*
 * CAN transmit buffer
 */
static union
{
	CAN_message can;
	uint8_t     buffer[MAX_CAN_LENGTH];
}                           uart_input;
static volatile uint8_t     uart_idx   = 0;
static volatile uint8_t     uart_sum   = 0;

/**
 * Diese Methode pr�ft auf sichere Weise, ob
 * Bytes im RS232 Empfangspuffer vorliegen.
 */
static uint8_t test_rx_buffer(void)
{
	cli();
	uint8_t test = uart_rx_count > 0;
	sei();

	return test;
}

/**
 * Hier werden alle einkommenden CAN-Messages in den
 * CAN receive Ring Buffer geholt. Falls dieser
 * �berl�uft, wird die Overflow LED angeschaltet.
 */
static void process_can_messages(void)
{
	// Check for valid message
	while (can_get_msg(ring_get_pos(&rx_ring)) >= 0)
	{
		if (ring_has_overflow(&rx_ring))
		{
			// Set CAN overflow error LED
			SET_PORT_BIT(PORT_OVL, P_OVL_CAN);
			cli();
			while(1);
		}
		ring_increase(&rx_ring);
	}
}

/**
 * Hier wird eine CAN-Message �ber RS232 �bertragen. Abschlie�end
 * wird ein Pr�fbyte versendet. Die Summe aller CAN-Message Bytes
 * und des Pr�fbytes muss 0 ergeben.
 */
static int8_t uart_send_msg(CAN_message *msg)
{
	uint8_t *buffer = (uint8_t *)msg;
	uint8_t  sum    = 0;
	int      i;
	int      len    = offsetof(CAN_message, data[msg->length]);

	cli();
	for(i = 0;i < len;i++)
	{
		uart_tx_buffer[uart_tx_pos++] = buffer[i];
		sum -= buffer[i];
	}
	uart_tx_count += len;

	uart_tx_buffer[uart_tx_pos++] = sum;
	uart_tx_count++;
	sei();

	/*
	 * Wenn der Sendepuffer leer ist, muss die �bertragung von Hand
	 * angesto�en werden. Den Rest erledigt die ISR. UDRE sollte noch
	 * vom letzten Mal gesetzt sein. Daher wird unmittelbar nach der
	 * n�chsten Methode der ISR beim Aktivieren von UDRIE der ISR
	 * ausgel�st.
	 */
	uart_enable_tx_interrupt(1);

	return 1;
}

/**
 * Interrupt Service Routine externer Interrupt 2.
 * Hier werden alle einkommenden CAN-Messages verarbeitet.
 */
ISR(INT2_vect)
{
	process_can_messages();
}

/**
 * Interrupt Service Routine UART buffer receive.
 * Hier wird ein einkommendes Byte verarbeitet. Dieses
 * kann nur Teil einer CAN-Message sein. Ob dieses Byte
 * in diese Message passt, wird w�hrend der Verarbeitung
 * gepr�ft. Als letztes Byte wird ein Pr�fbyte �bertragen.
 * Alle CAN-Message bytes und das Pr�fbyte m�ssen zusamman
 * als Summe 0 ergeben. Ist eine CAN-Message samt Pro�fsumme
 * korrekt �bertragen, wird der Ring Buffer hochgez�hlt.
 */
ISR(USART_RXC_vect)
{
	volatile uint8_t udr = UDR;

	uart_received++;

	/*
	 * Wenn der Byte Buffer �berl�uft, zeigt eine LED diesen
	 * Fehlerzustand an.
	 */
	if (uart_rx_count >= sizeof(uart_rx_buffer))
	{
		// Set UART overflow error LED
		SET_PORT_BIT(PORT_OVL, P_OVL_UART);
		while(1);
	}

	uart_rx_buffer[uart_rx_pos] = udr;
	uart_rx_pos++;
	uart_rx_count++;
}

ISR(USART_UDRE_vect)
{
	if (uart_tx_count > 0)
	{
		uint8_t udr = uart_tx_buffer[uart_tx_start];
		uart_tx_start++;
		uart_tx_count--;

		UDR = udr;
		uart_transmitted++;
	}
	else
	{
		/*
		 * Wenn alles �bertragen ist, Interrupt wieder ausschalten. Das ist
		 * wichtig, weil sonst nur noch "leere" Interrupts ausgef�hrt werden.
		 */
		uart_enable_tx_interrupt(0);
	}
}

/**
 * Diese Methode verarbeitet ein einzelnes Byte, das �ber
 * RS232 empfangen wurde. Es m�ssen dabei folgende Bedingungen
 * f�r eine g�ltige Empfangsverarbeitung erf�llt sein:
 * 1. Der CAN-Frame Buffer darf nicht �berf�llt sein.
 * 2. Das erste Byte muss im Bereich [1..8] liegen. Es ist das
 *    die L�ngendefinition.
 * 3. Das Byte nach den Datenbytes muss eine richtige Pr�fsumme
 *    sein.
 * Sollte eine der Bedingungen nicht zutreffen, wird der Pufferindex
 * und die Pr�fsumme zur�ckgesetzt. Ist nach �berpr�fung der
 * Pr�fsumme alles OK, wird das empfangene Frame �ber den CAN-Bus
 * verschickt.
 */
static void uart_process_byte(uint8_t udr)
{
	if (uart_idx >= MAX_CAN_LENGTH)
	{
		// Buffer Overflow verhindern.
		uart_idx = 0;
		uart_sum = 0;
	}
	uart_input.buffer[uart_idx++] = udr;
	uart_sum += udr;

	/*
	 * Das erste Byte einer CAN-Message ist immer ein Kommando (Application Layer).
	 * Daher gibt es hier nie leere CAN-Messages. Das wird hier �berpr�ft.
	 */
	if ((uart_input.can.length == 0) || (uart_input.can.length > 8))
	{
		uart_idx = 0;
		uart_sum = 0;
	}
#ifdef MRW_H
	else if ((uart_idx >= offsetof(CAN_message, data)) && (uart_input.can.data[0] == CMD_ILLEGAL))
	{
		uart_idx = 0;
		uart_sum = 0;
	}
#endif
	/*
	 * Wenn die CAN-Message vollst�ndig ist, muss nur noch das Pr�fbyte �berpr�ft werden.
	 */
	else if ((uart_input.can.length + offsetof(CAN_message, data) + 1) == uart_idx)
	{
		/*
		 * Wenn das Pr�fbyte jetzt 0 ist, ist die Pr�fsumme OK. Dann
		 * kann die CAN-Message abgesetzt werden.
		 */
		if (uart_sum == 0)
		{
			CAN_message *msg = ring_get_pos(&tx_ring);

			*msg = uart_input.can;
			ring_increase(&tx_ring);
		}
		uart_idx = 0;
		uart_sum = 0;
	}
}

/*
 * Hier werden die beiden LED Port Pins auf Ausgang geschaltet.
 */
static void port_init(void)
{
	/* LED Pins auf Output setzen und Anzeige l�schen. */
	DDR_OVL  |=    OVL_CAN | OVL_UART;
	PORT_OVL &= (~(OVL_CAN | OVL_UART));
}

int main(void)
{
	MCP2515_error_status status;

	ring_init(&rx_ring);
	ring_init(&tx_ring);
	port_init();
	mcp2515_init(GATEWAY_SID, 1, MCP2515_SINGLE_TX_BUFFER);
	uart_init();
	MCUCSR = 0;

	SET_PORT_BIT(PORT_OVL, P_OVL_CAN);
	SET_PORT_BIT(PORT_OVL, P_OVL_UART);
	for(int i = 0;i < 512;i++)
	{
		_delay_ms(1);
		mcp2515_write_rx_output_pins(i & 0x40 ? 0 : MCP_LED_GREEN|MCP_LED_YELLOW);
	}
	CLR_PORT_BIT(PORT_OVL, P_OVL_CAN);
	CLR_PORT_BIT(PORT_OVL, P_OVL_UART);

	mcp2515_write_rx_output_pins(MCP_LED_GREEN);

	sei();
	do
	{
		/*
		 * Pr�fen auf Empfang einer CAN Message �ber RS232.
		 * Das Versenden �ber CAN-Bus darf nicht durch Interrupt
		 * gest�rt werden.
		 */
		while (test_rx_buffer())
		{
			uart_process_byte(uart_rx_buffer[uart_rx_start]);

			cli();
			uart_rx_start++;
			uart_rx_count--;
			sei();
		}

		/*
		 * Pr�fen auf Empfang einer CAN Message �ber CAN-Bus.
		 * Das Versenden kann asynchron zum Empfang passieren.
		 * Daher muss der Interrupt erst beim Korrigieren des
		 * Ring Buffers gesperrt werden.
		 */
		while(ring_has_messages(&rx_ring))
		{
			if (uart_send_msg(ring_get_start(&rx_ring)) > 0)
			{
				ring_decrease(&rx_ring);
			}
		}

		/*
		 * Test auf das Versenden von Frames �ber CAN-Bus
		 */
		if(ring_has_messages(&tx_ring))
		{
			CAN_message *msg = ring_get_start(&tx_ring);

			if (can_put_msg(msg) >= 0)
			{
				ring_decrease(&tx_ring);
			}
			
			/*
			 * Warnung bei drohendem UART-�berlauf.
			 */
			if (ring_has_overflow(&tx_ring))
			{
				SET_PORT_BIT(PORT_OVL, P_OVL_CAN);
			}
			else
			{
				CLR_PORT_BIT(PORT_OVL, P_OVL_CAN);
			}
		}

		/*
		 * Warnung bei drohendem UART-�berlauf.
		 */
		if ((uart_tx_count >= UART_OVL_WARNING_LEVEL) || (uart_rx_count >= UART_OVL_WARNING_LEVEL))
		{
			SET_PORT_BIT(PORT_OVL, P_OVL_UART);
		}
		else
		{
			CLR_PORT_BIT(PORT_OVL, P_OVL_UART);
		}

		/*
		 * Wenn Fehler/Warn Status vorhanden ist, dann muss
		 * darauf reagiert werden. Am Wahrscheinlichsten sind
		 * Overflows bei zu hoher Message-Rate oder Error im
		 * Fall von Leitungsproblemen.
		 */
		if (mcp2515_read_error_status(&status) != 0)
		{
			/*
			 * Hier werden evtl. nicht verabeitete CAN-Messages aus
			 * ihren Empfangspuffern geholt. Das muss bei gesperrtem
			 * Interrupt erfolgen.
			 */
			process_can_messages();

			/*
			 * Jetzt m�ssen noch die Overflow Flags gel�scht werden. Mehr
			 * k�nnen wir nicht tun.
			 */
			mcp2515_reset_overflow(status.eflg);
		}

		/*
		 * Wenn beide Ring Buffer leer sind, kann die MCU
		 * schlafen. Die Bedingung darf nicht durch Interrupts
		 * gest�rt werden, weil eine einkommende �bertragung diese
		 * Bedingung ver�ndern k�nnte.
		 */
		cli();
		if ((!ring_has_messages(&rx_ring)) &&
		    (!ring_has_messages(&tx_ring)) &&
		    (uart_rx_count == 0))
		{
			// Sleep aktiviert den Interrupt wieder, sonst w�rde er
			// ewig schlafen :-(
			sleep();
		}
		sei();
	}
	while(1);
}
