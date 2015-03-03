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
#define F_CPU 14745600UL
#endif

#include <stdlib.h>
#include <stddef.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#include "bit.h"
#include "busy.h"
#include "can_ring.h"
#include "mcp2515.h"
#include "sleep.h"
#include "timer.h"
#include "tool.h"
#include "uart.h"

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
 * Diese Methode prüft auf sichere Weise, ob
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
 * überläuft, wird die Overflow LED angeschaltet.
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
 * Hier wird eine CAN-Message über RS232 übertragen. Abschließend
 * wird ein Prüfbyte versendet. Die Summe aller CAN-Message Bytes
 * und des Prüfbytes muss 0 ergeben.
 */
static int8_t uart_send_msg(CAN_message *msg)
{
	cli();

	register uint8_t *buffer = (uint8_t *)msg;
	register uint8_t  pos    = uart_tx_pos;
	register uint8_t  sum    = 0;
	register uint8_t  i;
	register uint8_t  len = offsetof(CAN_message, data[msg->length]);

	for(i = 0;i < len;i++)
	{
		uart_tx_buffer[pos++] = buffer[i];
		sum -= buffer[i];
	}
	uart_tx_buffer[pos++] = sum;
	
	/*
	 * Das hier wäre eine neue CAN-Länge. Sie ist ungültig und kann
	 * den Remote-Zustandsautomaten wieder In-Sync bringen, falls bei
	 * der Übertragung ein Byte verloren gegangen ist.
	 */
	len++;
	uart_tx_count += len;
	uart_tx_pos    = pos;
	sei();

	/*
	 * Wenn der Sendepuffer leer ist, muss die Übertragung von Hand
	 * angestoßen werden. Den Rest erledigt die ISR. UDRE sollte noch
	 * vom letzten Mal gesetzt sein. Daher wird unmittelbar nach der
	 * nächsten Methode der ISR beim Aktivieren von UDRIE der ISR
	 * ausgelöst.
	 */
	UART_ENABLE_TX_IRQ();

	return 1;
}

/**
 * Interrupt Service Routine externer Interrupt 2.
 * Hier werden alle einkommenden CAN-Messages verarbeitet.
 */
ISR(INT2_vect)
{
	BUSY;
	process_can_messages();
}

/**
 * Interrupt Service Routine UART buffer receive.
 * Hier wird ein einkommendes Byte verarbeitet. Dieses
 * kann nur Teil einer CAN-Message sein. Ob dieses Byte
 * in diese Message passt, wird während der Verarbeitung
 * geprüft. Als letztes Byte wird ein Prüfbyte übertragen.
 * Alle CAN-Message bytes und das Prüfbyte müssen zusamman
 * als Summe 0 ergeben. Ist eine CAN-Message samt Proüfsumme
 * korrekt übertragen, wird der Ring Buffer hochgezählt.
 */
ISR(USART_RXC_vect)
{
	BUSY;
	volatile uint8_t  udr = UDR;

	uart_received++;

	/*
	 * Wenn der Byte Buffer überläuft, zeigt eine LED diesen
	 * Fehlerzustand an.
	 */
	if (uart_rx_count >= sizeof(uart_rx_buffer))
	{
		// Set UART overflow error LED
		SET_PORT_BIT(PORT_OVL, P_OVL_UART);
		while(1);
	}

	uart_rx_buffer[uart_rx_pos++] = udr;
	uart_rx_count++;
}

ISR(USART_UDRE_vect)
{
	BUSY;
	register uint8_t  start = uart_tx_start;
	register uint8_t  pos   = uart_tx_pos;
	register uint16_t count = uart_tx_count;

	if (count > 0)
	{
		uint8_t udr = uart_tx_buffer[start++];
		count--;

		UDR = udr;
		uart_transmitted++;
	}
	else
	{
		/*
		 * Wenn alles übertragen ist, Interrupt wieder ausschalten. Das ist
		 * wichtig, weil sonst nur noch "leere" Interrupts ausgeführt werden.
		 */
		UART_DISABLE_TX_IRQ();

		/*
		 * Puffer wird mit Synchronisationssequenz aufgefüllt, wenn er leer
		 * gelaufen ist. Beim nächsten Senden wird diese vorausgeschickt.
		 */		
		start = 0;
		count =
		pos   = 8;
		for (uint8_t i = 0;i < pos;i++)
		{
			uart_tx_buffer[i] = 0;
		}
	}
	uart_tx_start = start;
	uart_tx_count = count;
	uart_tx_pos   = pos;
}

/**
 * Diese Methode verarbeitet ein einzelnes Byte, das über
 * RS232 empfangen wurde. Es müssen dabei folgende Bedingungen
 * für eine gültige Empfangsverarbeitung erfüllt sein:
 * 1. Der CAN-Frame Buffer darf nicht überfüllt sein.
 * 2. Das erste Byte muss im Bereich [1..8] liegen. Es ist das
 *    die Längendefinition.
 * 3. Das Byte nach den Datenbytes muss eine richtige Prüfsumme
 *    sein.
 * Sollte eine der Bedingungen nicht zutreffen, wird der Pufferindex
 * und die Prüfsumme zurückgesetzt. Ist nach Überprüfung der
 * Prüfsumme alles OK, wird das empfangene Frame über den CAN-Bus
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
	 * Daher gibt es hier nie leere CAN-Messages. Das wird hier überprüft.
	 */
	if ((uart_input.can.length == 0) || (uart_input.can.length > 8))
	{
		uart_idx = 0;
		uart_sum = 0;

		/*
		 * Das kann passieren, wenn die Kommunikation Out-Of-Sync ist.
		 * Daher senden wir einfach mal ein Byte zurück, um den Remote
		 * Empfänger auch wieder In-Sync zu bringen.
		 */
		if (uart_tx_count < UART_OVL_WARNING_LEVEL)
		{
			cli();
			uart_tx_buffer[uart_tx_pos++] = 0x19;
			uart_tx_count++;
			sei();
			UART_ENABLE_TX_IRQ();
		}
	}
#ifdef MRW_H
	else if ((uart_idx >= offsetof(CAN_message, data)) && (uart_input.can.data[0] == CMD_ILLEGAL))
	{
		uart_idx = 0;
		uart_sum = 0;
	}
#endif
	/*
	 * Wenn die CAN-Message vollständig ist, muss nur noch das Prüfbyte überprüft werden.
	 */
	else if ((uart_input.can.length + offsetof(CAN_message, data) + 1) == uart_idx)
	{
		/*
		 * Wenn das Prüfbyte jetzt 0 ist, ist die Prüfsumme OK. Dann
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
	/* Busy indicator */
	DDR_BUSY  |= _BV(P_BUSY);
	BUSY;

	/* LED Pins auf Output setzen und Anzeige löschen. */
	DDR_OVL  |=    OVL_CAN | OVL_UART;
	PORT_OVL &= (~(OVL_CAN | OVL_UART));
}


static void adc_start(void)
{
	ADCSRA |= _BV(ADSC);
}

static void adc_wait(void)
{
	while(bit_is_set(ADCSRA, ADSC));
}

#define ADC_BUFFER_SHIFT  6
#define ADC_BUFFER_SIZE  (1 << ADC_BUFFER_SHIFT)
#define ADC_BUFFER_MASK  (ADC_BUFFER_SIZE - 1)

static uint16_t adc_buffer[ADC_BUFFER_SIZE];
static uint16_t adc_value;
static uint8_t  adc_index;

static uint8_t  sensor_old;
static uint16_t sensor_counter = TIMER2_DELAY(5);

static void send_sensor_value(uint8_t value)
{
	CAN_message *msg = ring_get_pos(&tx_ring);

	msg->sid     = BROADCAST_SID;
	msg->eid     = 0;
	msg->status  = 0;
	msg->length  = 3;
	msg->data[0] = 0x4e;
	msg->data[1] = 0x01;
	msg->data[2] = value;

	ring_increase(&tx_ring);
}

static uint8_t adc_get(void)
{
	uint16_t value = ADCW;

	adc_value -= adc_buffer[adc_index];
	adc_buffer[adc_index] = value;
	adc_value += value;
	adc_index++;
	adc_index &= ADC_BUFFER_MASK;
	
	return (((adc_value >> ADC_BUFFER_SHIFT) + 0x04) >> 2) & 0xfe;
}

static uint8_t adc_read(void)
{
	adc_start();
	adc_wait();
	return adc_get();
}

static void adc_init(void)
{
	uint8_t i;

	/* ADC 7, Aref, ADC right adjust */
	ADMUX = _BV(REFS0) | _BV(MUX2) | _BV(MUX1) | _BV(MUX0);

	/* ADC and interrupt enable, Prescaler 128, Auto Trigger */
	ADCSRA = _BV(ADEN);

	/* Durchschnitt initialisieren */	
	for (i = 0; i < sizeof(adc_buffer); i++)
	{
		sensor_old = adc_read();
	}
	send_sensor_value(sensor_old);
}

ISR(TIMER2_OVF_vect)
{
	BUSY;
	uint8_t sensor_new = adc_read();

	if ((sensor_counter & 0xf) == 0)
	{
		if ((sensor_old != sensor_new) || (sensor_counter == 0))
		{
			send_sensor_value(sensor_new);
			sensor_old = sensor_new;
			sensor_counter = TIMER2_DELAY(60);
		}
	}
	sensor_counter--;
}

int main(void)
{
	MCP2515_error_status status;

	port_init();
	ring_init(&rx_ring);
	ring_init(&tx_ring);
	mcp2515_init(GATEWAY_SID, 1, MCP2515_SINGLE_TX_BUFFER);
	uart_init();
	adc_init();
	timer2_init();

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
		 * Prüfen auf Empfang einer CAN Message über RS232.
		 * Das Versenden über CAN-Bus darf nicht durch Interrupt
		 * gestört werden.
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
		 * Prüfen auf Empfang einer CAN Message über CAN-Bus.
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
		 * Test auf das Versenden von Frames über CAN-Bus
		 */
		if(ring_has_messages(&tx_ring))
		{
			CAN_message *msg = ring_get_start(&tx_ring);

			if (can_put_msg(msg) >= 0)
			{
				ring_decrease(&tx_ring);
			}
			
			/*
			 * Warnung bei drohendem UART-Überlauf.
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
		 * Warnung bei drohendem UART-Überlauf.
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
			 * Jetzt müssen noch die Overflow Flags gelöscht werden. Mehr
			 * können wir nicht tun.
			 */
			mcp2515_reset_overflow(status.eflg);
		}

		/*
		 * Wenn beide Ring Buffer leer sind, kann die MCU
		 * schlafen. Die Bedingung darf nicht durch Interrupts
		 * gestört werden, weil eine einkommende Übertragung diese
		 * Bedingung verändern könnte.
		 */
		cli();
		if ((!ring_has_messages(&rx_ring)) &&
		    (!ring_has_messages(&tx_ring)) &&
		    (uart_rx_count == 0))
		{
			// Sleep aktiviert den Interrupt wieder, sonst würde er
			// ewig schlafen :-(
			IDLE;
			sleep();
		}
		BUSY;
		sei();
	}
	while(1);
}
