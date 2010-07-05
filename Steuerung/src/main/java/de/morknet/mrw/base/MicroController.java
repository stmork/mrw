/*
**
**	$Filename:	MicroController.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
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

package de.morknet.mrw.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse repr�sentiert einen Mikrocontroller (aka CAN-Knoten).
 * @author sm
 *
 */
public class MicroController
{
	/**
	 * Die Controller-ID dieser Mikrocontroller-Instanz.
	 */
	private final int id;
	
	/**
	 * Die Taktfrequenz des Mikrocontrollers.
	 */
	public final static long MC_FREQUENCY      = 16000000L;
	
	/**
	 * Die Zahl der Takte zwischen einem Timer-Overflow. Diese bildet sich aus dem 8-Bit-Z�hler und
	 * dem Prescaler von 1024.
	 */
	public final static long MC_TIMER_OVERFLOW = (1L << 18);

	/**
	 * Die absolute L�nge eines Timer-Durchlaufs in Sekunden.
	 */
	public final static double MC_TIMER_QUANTUM = (double)MC_TIMER_OVERFLOW / (double)MC_FREQUENCY;

	/**
	 * Liste aller angeschlossenen Ger�te.
	 */
	private final ArrayList<DeviceUnit> units = new ArrayList<DeviceUnit>();

	/**
	 * Anzahl versendeter Pings.
	 */
	volatile private int ping_count = 0;

	/**
	 * Anzahl empfangener Ping-Antworten
	 */
	volatile private int pong_count = 0;

	/**
	 * F�llstand des Empfangspuffers.
	 */
	volatile private int rx_count = 0;

	/**
	 * F�llstand des Sendepuffers.
	 */
	volatile private int tx_count = 0;

	/**
	 * CAN-Bus Fehlerzustand
	 */
	volatile private int error_flags    = 0;

	/**
	 * CAN-Bus Empfangsfehler
	 */
	volatile private int rx_err_counter = 0;

	/**
	 * CAN-Bus Sendefehler
	 */
	volatile private int tx_err_counter = 0;

	/**
	 * Firmware Version.
	 */
	volatile private int version = 0;

	/**
	 * Firmware revision.
	 */
	volatile private int revision = 0;

	/**
	 * Flag, ob Mikrocontroller gebootet hat.
	 */
	volatile private boolean booted;

	/**
	 * Dieser Konstruktor definiert die Controller-ID mit.
	 * @param id Die zu setzende Controller-ID.
	 */
	public MicroController(int id)
	{
		this.id = id;
	}

	/**
	 * Diese Methode z�hlt die Ping-Anfragezahl hoch.
	 */
	public void ping()
	{
		synchronized (this)
		{
			ping_count++;
		}
	}

	/**
	 * Diese Methode z�hlt die Ping-Antwortzahl (Pong) hoch.
	 */
	public void pong()
	{
		synchronized (this)
		{
			pong_count++;
			booted();
		}
	}

	/**
	 * Diese Methode l�scht alle Zustandsz�hler. 
	 */
	public void reset()
	{
		synchronized (this)
		{
			ping_count     = 0;
			pong_count     = 0;
			error_flags    = 0;
			rx_count       = 0;
			tx_count       = 0;
			rx_err_counter = 0;
			tx_err_counter = 0;
			booted         = false;
		}
	}

	/**
	 * Diese Methode markiert den Mikrocontroller als gebootet.
	 */
	public void booted()
	{
		synchronized(this)
		{
			this.booted = true;
		}
	}

	/**
	 * Diese Methode meldet zur�ck, ob der Controller erreichbar ist. Das bedeutet, dass
	 * nach gleich vielen Ping-Anfragen, gleich viele Antworten (Pong) erfolgt sein m�ssen. 
	 * @return Die Erreichbarkeit.
	 */
	public boolean isReachable()
	{
		final boolean reachable; 

		synchronized(this)
		{
			reachable = (ping_count == pong_count) && booted; 
		}
		return reachable;
	}
	
	/**
	 * Diese Methode setzt die F�llst�nde des Sende- und Empfangspuffers.
	 * @param rx_count F�llstand des Empfangspuffers.
	 * @param tx_count F�llstand des Sendepuffers.
	 */
	public void setBufferCount(final int rx_count, final int tx_count)
	{
		synchronized(this)
		{
			booted();
			this.rx_count = rx_count;
			this.tx_count = tx_count;
		}
	}

	/**
	 * Diese Methode setzt diverse Fehlerz�hler auf einen bestimmten Wert. Konkret werden
	 * damit die Antworten auf eine QRYERR-Anfrage verarbeitet.
	 * @param index Der Z�hlertyp.
	 * @param value Der Z�hler.
	 */
	public void setErrorValue(final int index, final int value)
	{
		synchronized(this)
		{
			booted();
			switch(index)
			{
			case 0:
				error_flags = value;
				break;
			case 1:
				rx_err_counter = value;
				break;
			case 2:
				tx_err_counter = value;
				break;
			default:
				// Do nothing!
				break;
			}
		}
	}
	
	/**
	 * Diese Methode setzt die Firmware Version und Revision. Konkret werden
	 * damit die Antworten auf eine GETVER-Anfrage verarbeitet.
	 * @param index Der Versionstyp.
	 * @param value Der Wert.
	 */
	public void setRevisionValue(final int index, final int value)
	{
		booted();
		switch(index)
		{
		case 0:
			version = value;
			break;
		case 1:
			revision = (revision & 0xff00) | value;
			break;
		case 2:
			revision = (revision & 0x00ff) | (value << 8);
			break;
		default:
			// Do nothing!
			break;
		}
	}
	
	/**
	 * Diese Methode gibt die Controller-ID zur�ck.
	 * @return Die Controller-ID.
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * Diese Methode f�gt ein Ger�t diesem Controller hinzu.
	 * @param unit Das zu steuernde Ger�t.
	 */
	public void addDeviceUnit(final DeviceUnit unit)
	{
		units.add(unit);
	}
	
	/**
	 * Diese Methode f�gt einer Liste alle CAN-Meldungen hinzu, die f�r das Konfigurieren
	 * dieses Controllers n�tig sind.
	 * @param list Die Liste, die mit Konfigurationsmeldungen bef�llt wird.
	 */
	public void addConfigMessages(final List<MrwMessage> list)
	{
		list.add(MrwMessage.createCommandMsg(Command.CFGBGN, id, 0));

		Collections.sort(units);
		for (DeviceUnit device : units)
		{
			list.add(device.createConfigMessage());
		}
		list.add(MrwMessage.createCommandMsg(Command.CFGEND, id, 0));
	}
	
	/**
	 * Diese Methode erzeugt einen Klartext �ber den Zustand dieses Controllers.
	 * @return Der Klartext �ber den Zustand dieses Controllers.
	 */
	public String toString()
	{
		StringWriter sw = new StringWriter();
		PrintWriter  pw = new PrintWriter(sw);
		
		pw.printf("Controller ID: %04x  Ver: %d.%d Ping: %5d Pong: %5d RXB: %5d TXB: %5d  EFlags: %02x RX error: %3d TX error: %3d",
			id, version, revision,
			ping_count, pong_count,
			rx_count, tx_count,
			error_flags, rx_err_counter, tx_err_counter);
		String result = sw.toString();
	    pw.close();
	    return result;
	}
}
