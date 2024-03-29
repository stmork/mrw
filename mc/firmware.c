/*
**
**	$Filename:	firmware.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Modellrailway micro controller firmware
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

#include "firmware.h"

#include <stdlib.h>
#include <avr/interrupt.h>
#include <avr/sleep.h>
#include <avr/wdt.h>
#include <util/delay.h>

#include "mcp2515.h"
#include "mrw.h"
#include "rail.h"
#include "switch.h"
#include "signal.h"
#include "light.h"
#include "bit.h"
#include "eeprom.h"
#include "tool.h"
#include "serial.h"
#include "timer.h"
#include "random.h"
#include "sleep.h"
#include "config.h"
#include "can_ring.h"
#include "cmd_queue.h"
#include "result_queue.h"
#include "can_processing.h"
#include "pwm.h"

#include "CAN_Node.h"

#define NODE_OFF       0
#define NODE_READY     MCP_LED_GREEN
#define NODE_ERROR    (MCP_LED_GREEN|MCP_LED_YELLOW)

/*
 * Solange Antworten versenden, wie Meldungen zu
 * versenden sind und bis MCP keine TX-Buffer mehr frei hat.
 */
static void fill_tx_buffers(void)
{
	int8_t buffer_no = 0;

	while (ring_has_messages(&tx_ring) && (buffer_no >= 0))
	{
		CAN_message *msg = ring_get_start(&tx_ring);

		buffer_no = can_put_msg(msg);
		if (buffer_no >= 0)
		{
			ring_decrease(&tx_ring);
		}
	}
}

/**
 * Hier werden evtl. nicht verabeitete CAN-Messages aus
 * ihren Empfangspuffern geholt. Das muss bei gesperrtem
 * Interrupt erfolgen.
 */
static void can_process_messages(void)
{
	/* Erst mal alle eingehenden Meldungen verarbeiten */
	while(ring_has_messages(&rx_ring))
	{
		CAN_message *msg = ring_get_start(&rx_ring);

		/* Meldung verarbeiten */
		CAN_Node(msg);

		/* Meldung aus Ringpuffer nehmen */
		ring_decrease(&rx_ring);

		/* Nach Möglichkeit Antworten versenden. */
		fill_tx_buffers();
	}

	fill_tx_buffers();
	if (ring_has_overflow(&tx_ring))
	{
		/*
		 * Wenn der Ringpuffer zu voll ist, wird der Versand mindestens
		 * einer Meldung erzwungen.
		 */
		CAN_message *msg = ring_get_start(&tx_ring);

		while(can_put_msg(msg) < 0);
		ring_decrease(&tx_ring);
	}
}

/**
 * Interrupt Service Routine externer Interrupt 2. Hier werden alle
 * empfangenen CAN-Messages verarbeitet.
 */
ISR(INT2_vect)
{
	while(can_get_msg(ring_get_pos(&rx_ring)) >= 0)
	{
		if (ring_increase(&rx_ring))
		{
			mcp2515_write_rx_output_pins(NODE_ERROR);
		}
	}
}

/******************/
/* timer handling */
/******************/

volatile static uint8_t switch_state_change = 0;
volatile static  int8_t switch_counter      = 0;
volatile static  int8_t switch_elapsed;

static void check_switching_state(struct mrw_switch *device, uint8_t cmd)
{
	switch (cmd)
	{
	case SETLFT:
		if (test_state_change(&device->left_input) == PIN_FALLING_EDGE)
		{
			switch_state_change |= 1;
		}
		if (test_state_change(&device->right_input) == PIN_RISING_EDGE)
		{
			switch_state_change |= 2;
		}
		break;

	case SETRGT:
		if (test_state_change(&device->right_input) == PIN_FALLING_EDGE)
		{
			switch_state_change |= 1;
		}
		if (test_state_change(&device->left_input) == PIN_RISING_EDGE)
		{
			switch_state_change |= 2;
		}
		break;
	}
}

static int8_t get_timeout(uint8_t dvc_type)
{
	/*
	 * Wir haben den Timer auf den Prescaler 1024 gestellt und alle
	 * 256 Zählschritte tritt ein Overflow und somit dieser Interrupt
	 * auf. 1024 * 256 = (1 << 10) * (1 << 8) = (1 << (10 + 8))
	 * Wir müssen also den MCU Takt durch 2^18 teilen um die Überläufe
	 * pro Sekunde zu bekommen. Das wird unser Schaltzähler.
	 */
	int8_t to = F_CPU >> TIMER2_SHIFT;

	switch (dvc_type)
	{
	case TYPE_SWITCH_OLD:
		to /= 4;
		break;

	case TYPE_SWITCH_NEW:
		to = (F_CPU >> TIMER2_SHIFT) * 2 / 4;
		break;

	case TYPE_SIGNAL_PF2:
	case TYPE_SIGNAL_MF2:
	case TYPE_SIGNAL_SF2:
	case TYPE_SIGNAL_PF3:
	case TYPE_SIGNAL_MF3:
		to = (F_CPU >> TIMER2_SHIFT) * 4 / 5;
		break;

	case TYPE_SIGNAL_SL2:
	case TYPE_SIGNAL_PL2:
	case TYPE_SIGNAL_PL3:
	case TYPE_SIGNAL_ML2:
	case TYPE_SIGNAL_ML3:
	case TYPE_SIGNAL_ML4:
	case TYPE_SIMPLE_LIGHT:
		to = 1;
		break;

	default:
		to /= 2;
		break;
	}
	return to;
}

/**
 * Interrupt Service Routine Overflow Timer 2. Hier werden alle Langzeitkommandos
 * verarbeitet.
 */
ISR(TIMER2_OVF_vect)
{
	mrw_device *dvc;
	uint8_t     cmd;
	uint8_t     code;
	uint8_t     dir;
	uint8_t     i;

	/*
	 * Hier wird der Watch dog reset durchgeführt. Da das die einzige
	 * sichere regelmäßige Stelle ist, ist es hier sinnvoll. Bleibt eine
	 * Interrupt Aktivierung aus - was ein Software Fehler ist - wird der
	 * Watch dog die MCU resetten. Was auch gut ist: Wir können in den
	 * Sleep Modus wechseln und trotzdem den Watch Dog benutzen.
	 */
	if (!isResetting())
	{
		wdt_reset();
	}
	else
	{
		mcp2515_write_rx_output_pins(NODE_OFF);
	}

	/**
	 * Wenn nix konfiguriert wurde, gibt's auch nix zu tun.
	 */
	if (!IS_CONFIGURED)
	{
		return;
	}

	/*
	 * Hier wird überprüft, ob es an neuen Weichen mit Endabschaltung einen
	 * manuellen Umschaltvorgang gegeben hat oder ob die Gleisbesetztmeldung
	 * einen Zustandswechsel entdeckt hat. Dann muss eine CAN-Message
	 * verschickt werden.
	 */
	switch_elapsed++;
	dvc = config.dvc;
	for (i = 0;i < config.count; i++)
	{
		switch (dvc->unit_type)
		{
		case TYPE_SWITCH_NEW:
			if (cmd_is_first(dvc))
			{
				command *entry = cmd_get_first();

				/*
				 * Hier wird ein laufender Schaltauftrag überprüft. Das kann nur
				 * eine Weiche mit Endabschaltung sein.
				 */
				check_switching_state(&dvc->unit.u_switch, entry->cmd);
				if (switch_state_change == 3)
				{
					/*
					 * OK, Endlage ist erreicht. Jetzt Richtung feststellen, Antriebe ausschalten
					 * und Kommando aus Schleife entfernen.
					 */
					dir = switch_dir(&dvc->unit.u_switch);
	
					switch_off(&dvc->unit.u_switch);
					switch_counter      = 0;
					switch_state_change = 0;
					queue_info(entry->cmd, dvc->unit_no, MSG_OK, switch_elapsed);
					cmd_remove();
				}
			}
			else
			{
				/*
				 * Hier wird der Zustand einer Weiche mit Endabschaltung überprüft,
				 * die keinen aktuellen Schaltauftrag hat. Das entspricht einem manuellen
				 * Eingriff in die Weichenschaltung.
				 */
				dir = switch_test(&dvc->unit.u_switch);
				if (dir != 0)
				{
					queue_info(GETDIR, dvc->unit_no, MSG_OK, dir);
				}
			}
			break;

		case TYPE_RAIL:
			if (rail_state_changed(&dvc->unit.u_rail))
			{
				/* Gleiszustand hat sich geändert. */
				queue_info(GETRBS, dvc->unit_no, MSG_OK, rail_occupied(&dvc->unit.u_rail));
			}
			break;

		case TYPE_LIGHT:
			light_dimm(&dvc->unit.u_light);
			break;
		}
		dvc++;
	}

	/* Test, ob es überhaupt was zu tun gibt. */
	if (cmd_ring_size() == 0)
	{
		return;
	}

	/**
	 * Wenn der Ring Buffer Kommandos enthält und der Zähler bei 0
	 * angelangt ist, kann der nächste Antrieb aktiviert werden.
	 */
	if (switch_counter <= 0)
	{
		command *entry = cmd_get_first();
		dvc  = entry->device;
		cmd  = entry->cmd;
		code = entry->code;

		switch_counter = get_timeout(dvc->unit_type);
		switch(dvc->unit_type)
		{
		case TYPE_SWITCH_OLD:
		case TYPE_SWITCH_NEW:
			switch_state_change = 0;
			switch_elapsed      = 0;

			if (cmd == SETLFT)
			{
				switch_left(&dvc->unit.u_switch);
			}
			else if (cmd == SETRGT)
			{
				switch_right(&dvc->unit.u_switch);
			}
			break;

		case TYPE_SIGNAL_SF2:
		case TYPE_SIGNAL_PF2:
		case TYPE_SIGNAL_MF2:
			form2_turn(&dvc->unit.u_form2, code);
			break;

		case TYPE_SIGNAL_PF3:
		case TYPE_SIGNAL_MF3:
			form3_turn(&dvc->unit.u_form3, code);
			break;
		}
	}
	else
	{
		/*
		 * Wenn der Zähler größer 0 ist, ist ein Spulenantrieb geschaltet. Wenn
		 * dieser beim Herabzählen 0 erreicht hat, muss der Antrieb abgeschaltet
		 * werden. Der Schaltvorgang ist somit beendet und das Kommando kann aus
		 * dem Ring Buffer entfernt werden.
		 */
		switch_counter--;
		if (switch_counter <= 0)
		{
			command *entry = cmd_get_first();
			dvc = entry->device;
			cmd = entry->cmd;

			switch (dvc->unit_type)
			{
			case TYPE_SWITCH_OLD:
				switch_off(&dvc->unit.u_switch);
				queue_info(cmd, dvc->unit_no, MSG_OK, switch_elapsed);
				break;

			case TYPE_SWITCH_NEW:
				switch_off(&dvc->unit.u_switch);
				queue_result(cmd, dvc->unit_no, MSG_SWITCH_FAILED);
				break;

			case TYPE_SIGNAL_PF2:
			case TYPE_SIGNAL_MF2:
				form2_off(&dvc->unit.u_form2);
				queue_result(SETSGN, dvc->unit_no, MSG_OK);
				break;

			case TYPE_SIGNAL_PF3:
			case TYPE_SIGNAL_MF3:
				form3_off(&dvc->unit.u_form3);
				queue_result(SETSGN, dvc->unit_no, MSG_OK);
				break;
			}
			cmd_remove();		
		}
	}
}

/*
 * Hier wird das Soft-PWM durchgeführt.
 */
ISR(TIMER1_COMPA_vect)
{
	mrw_device *dvc = config.dvc;
	uint8_t     i;

	for (i = 0;i < config.count; i++)
	{
		if (dvc->unit_type == TYPE_LIGHT)
		{
			if (IS_PWM_DIMM(&dvc->unit.u_light))
			{
				handle_pwm(&dvc->unit.u_light);
			}
		}
		dvc++;
	}
}

static void init_ports(void)
{
	uint8_t i;

	/*
	 * Wenn nichts konfiguriert wurde, werden alle Ports auf Eingang geschaltet.
	 * und die Pullups eingeschaltet.
	 */
	if (!IS_CONFIGURED)
	{
 		/* Alle Ports auf Eingang. */
		DDRA = 0;
		DDRC = 0;
 		DDRD = 0;
		
		/* Pullups an! */
		PORTA = 0;
		PORTC = 0;
		PORTD = 0;

		return;
	}

	/*
	 * Hier wird pro konfiguriertem Gerät die Portansteuerung programmiert.
	 */
	mrw_device *dvc = config.dvc;
	for (i = 0;i < config.count; i++)
	{
		switch (dvc->unit_type)
		{
		case TYPE_SWITCH_OLD:
			switch_init_old(&dvc->unit.u_switch);
			switch_off     (&dvc->unit.u_switch);
			break;
		case TYPE_SWITCH_NEW:
			switch_init_new(&dvc->unit.u_switch);
			switch_off     (&dvc->unit.u_switch);
			break;

		case TYPE_SIGNAL_PF2:
		case TYPE_SIGNAL_MF2:
			form2_init(&dvc->unit.u_form2);
			form2_off (&dvc->unit.u_form2);
			break;
		case TYPE_SIGNAL_PF3:
		case TYPE_SIGNAL_MF3:
			form3_init(&dvc->unit.u_form3);
			form3_off (&dvc->unit.u_form3);
			break;

		case TYPE_RAIL:
			rail_init(&dvc->unit.u_rail);
			rail_off (&dvc->unit.u_rail);
			break;

		case TYPE_SIGNAL_SL2:
			dvc->unit.u_signal.img = SIGNAL_SH0;
			break;
		case TYPE_SIGNAL_PL2:
		case TYPE_SIGNAL_PL3:
			dvc->unit.u_signal.img = SIGNAL_VR0;
			break;
		case TYPE_SIGNAL_ML2:
		case TYPE_SIGNAL_ML3:
		case TYPE_SIGNAL_ML4:
			dvc->unit.u_signal.img = SIGNAL_HP0;
			break;

		case TYPE_LIGHT:
			light_init(&dvc->unit.u_light);
			break;

		case TYPE_SIMPLE_LIGHT:
			simple_light_init(&dvc->unit.u_simple_light);
			break;
		}
		dvc++;
	}
}

static void can_wait_for_tx(void)
{
	/*
	 * Hier wird max. 150 ms darauf gewartet, dass die Sendepuffer komplett
	 * frei werden. Wenn die 150ms abgelaufen sind, wird die Hauptapplikation
	 * trotzdem gestartet.
	 */
	for (uint8_t cnt = 0;
		((mcp2515_read_status() & (TX_STAT_TXB0|TX_STAT_TXB1|TX_STAT_TXB2)) &&
		(cnt < 150));
		cnt++)
	{
		_delay_ms(1);
	}
}

static void signal_init(void)
{
	serial_init();
	if (IS_CONFIGURED)
	{
		mrw_device *dvc = config.dvc;
		uint8_t     i;

		for (i = 0;i < config.count; i++)
		{
			switch (dvc->unit_type)
			{
			case TYPE_SIGNAL_SL2:
			case TYPE_SIGNAL_PL2:
			case TYPE_SIGNAL_PL3:
			case TYPE_SIGNAL_ML2:
			case TYPE_SIGNAL_ML3:
			case TYPE_SIGNAL_ML4:
				// Größe des benötigten Sendepuffers abgleichen
				for(uint8_t idx = 0; idx < dvc->unit.u_signal.count; idx++)
				{
					serial_limit(dvc->unit.u_signal.byte[idx]);
				}
				compute_signal(dvc);
				break;

			case TYPE_SIMPLE_LIGHT:
				serial_limit(dvc->unit.u_simple_light.byte);
				break;
			}
			dvc++;
		}
		send_serial_buffer();
	}
}

#ifdef HUNTING_SIMPLE_LIGHT_BUG
static void init_debug_config(void)
{
	mrw_device *dvc = config.dvc;

	config.id = 11;
	config.magic = CONFIG_MAGIC;
	config.count = MAX_DEVICES;

	for (uint8_t i = 0;i < config.count;i++)
	{
		dvc->unit_no = i+1;
		dvc->unit_type = TYPE_SIMPLE_LIGHT;
		dvc->unit.u_simple_light.threshold = 32+i;
		dvc->unit.u_simple_light.byte = (63 - i) >> 3;
		dvc->unit.u_simple_light.bit  = (63 - i)  & 7;
		dvc++;
	}
}
#endif

int main(int argc,char *argv[])
{
	MCP2515_error_status status;
	uint8_t last_state = 0;

	// Timer für Zufallszahlen aktivieren.
	random_preinit();

	// Konfiguration ins SRAM lesen.
#ifndef HUNTING_SIMPLE_LIGHT_BUG
	read_eeprom_config(&config);
#else
	init_debug_config();
#endif

	// Gerätedaten sortieren
	config_sort();

	// Workaround für Bootloader: FLASH_CHECK loswerden!
	// Nach einem Flash hat das MCUCSR keinen Grund für einen Reset.
	if (bit_is_set(MCUCSR, WDRF))
	{
		can_wait_for_tx();
	}

	// Zustandsautomaten initialisieren
	init_CAN_Node();
	
	// CAN-Bus initialisieren
	mcp2515_init(config.id, IS_CONFIGURED, MCP2515_MULTI_TX_BUFFER);

	// Hardware initialisieren
	timer2_init();
	random_postinit();

	// Hardware an Hand der Konfiguration initialisieren
	init_ports();

	// Initiale Signalbilder bereitstellen
	signal_init();

	// Lampen einschalten
	if (light_available() > 0)
	{
		set_sleep_mode(SLEEP_MODE_IDLE);
		timer1_init(F_CPU / (50 * PWM_TABLE_SIZE));
	}
	else
	{
		set_sleep_mode(SLEEP_MODE_ADC);
	}

	// Watch dog einschalten.
	wdt_enable(WDTO_1S);

	// Queue startup message incl. reset reason.
	queue_info(RESET, 0, MSG_BOOTED, MCUCSR);
	MCUCSR = 0;

#if 0
	for(uint8_t t = 0; t < 10; t++)
	{
		queue_infos4(RESET, 0, MSG_BOOTED,
			random_timer(), random_timer(),
			random_timer(), random_timer());
	}
#endif

	mcp2515_write_rx_output_pins(NODE_READY);
	sei();
	for (;;)
	{
		/*
		 * CAN-Meldungen und nach Möglichkeit deren
		 * Antworten bearbeiten.
		 */
		can_process_messages();

		/*
		 * Wenn Fehler/Warn Status vorhanden ist, dann muss
		 * darauf reagiert werden. Am Wahrscheinlichsten sind
		 * Overflows bei zu hoher Message-Rate oder Error im
		 * Fall von Leitungsproblemen. Das Lesen des Fehlerstatus
		 * erfolgt mit gesperrtem Interrupt.
		 */
		if (mcp2515_read_error_status(&status))
		{
			/*
			 * Jetzt müssen noch die Overflow flags gelöscht werden. Mehr
			 * können wir nicht tun.
			 */
			mcp2515_reset_overflow(status.eflg);
		}

		/*
		 * Fehlerzustand bei Änderung automatisch versenden.
		 */
		if (last_state != status.eflg)
		{
			queue_infos4(QRYERR, 0, MSG_OK, 3, status.eflg, status.rec, status.tec); 
			last_state = status.eflg;
		}

		/*
		 * Hier wird der Status über die gelbe LED ausgegeben, ob ein
		 * Fehlerzähler erhöht wurde.
		 */
		mcp2515_write_rx_output_pins(
			(status.rec > 0) || (status.tec > 0) || (status.eflg != 0) ?
			NODE_ERROR : NODE_READY);


		/*
		 * Wenn der Ring Buffer leer ist, kann die MCU
		 * schlafen. Die Bedingung darf nicht durch Interrupts
		 * gestört werden, weil eine einkommende Übertragung diese
		 * Bedingung verändern könnte.
		 */
		cli();
		if ((!ring_has_messages(&rx_ring)) &&
		    (!ring_has_messages(&tx_ring)) &&
		    (!isResetting()))
		{
			// Sleep aktiviert den Interrupt wieder, sonst würde er
			// ewig schlafen :-(
			sleep();
		}
		sei();
	}
}
