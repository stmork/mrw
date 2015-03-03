/*
**
**	$Filename:	MicroController.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
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
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse repräsentiert einen Mikrocontroller (aka CAN-Knoten).
 * @author sm
 *
 */
public class MicroController
{
	private final Log    log; 

	/**
	 * Die Controller-ID dieser Mikrocontroller-Instanz.
	 */
	private final int id;
	
	/**
	 * Die Taktfrequenz des Mikrocontrollers.
	 */
	public final static long MC_FREQUENCY      = 16000000L;
	
	/**
	 * Die Zahl der Takte zwischen einem Timer-Overflow. Diese bildet sich aus dem 8-Bit-Zähler und
	 * dem Prescaler von 1024.
	 */
	public final static long MC_TIMER_OVERFLOW = (1L << 18);

	/**
	 * Die absolute Länge eines Timer-Durchlaufs in Sekunden.
	 */
	public final static double MC_TIMER_QUANTUM = (double)MC_TIMER_OVERFLOW / (double)MC_FREQUENCY;

	/**
	 * Liste aller angeschlossenen Geräte.
	 */
	private final ArrayList<DeviceUnit> units = new ArrayList<DeviceUnit>();

	/**
	 * Anzahl versendeter Pings.
	 */
	private final AtomicInteger ping_count = new AtomicInteger(0);

	/**
	 * Anzahl empfangener Ping-Antworten
	 */
	private final AtomicInteger pong_count = new AtomicInteger(0);
	
	/**
	 * Füllstand des Empfangspuffers.
	 */
	volatile private int rx_count = 0;

	/**
	 * Füllstand des Sendepuffers.
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
		this.id  = id;
		this.log = LogFactory.getLog(LogUtil.printf("MicroController %3d", id));
	}

	/**
	 * Diese Methode zählt die Ping-Anfragezahl hoch.
	 */
	public void ping()
	{
		synchronized (this)
		{
			ping_count.addAndGet(1);
		}
	}

	/**
	 * Diese Methode zählt die Ping-Antwortzahl (Pong) hoch.
	 */
	public void pong()
	{
		synchronized (this)
		{
			pong_count.addAndGet(1);
			booted();
		}
	}

	/**
	 * Diese Methode löscht alle Zustandszähler. 
	 */
	public void reset()
	{
		synchronized (this)
		{
			
			ping_count.set(0);
			pong_count.set(0);
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
			ping_count.set(0);
			pong_count.set(0);
		}
	}

	/**
	 * Diese Methode meldet zurück, ob der Controller erreichbar ist. Das bedeutet, dass
	 * nach gleich vielen Ping-Anfragen, gleich viele Antworten (Pong) erfolgt sein müssen. 
	 * @return Die Erreichbarkeit.
	 */
	public boolean isReachable()
	{
		final boolean reachable; 

		synchronized(this)
		{
			reachable = (ping_count.get() <= pong_count.get()) && booted; 
		}
		return reachable;
	}
	
	/**
	 * Diese Methode setzt die Füllstände des Sende- und Empfangspuffers.
	 * @param rx_count Füllstand des Empfangspuffers.
	 * @param tx_count Füllstand des Sendepuffers.
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
	 * Diese Methode setzt diverse Fehlerzähler auf einen bestimmten Wert. Konkret werden
	 * damit die Antworten auf eine QRYERR-Anfrage verarbeitet.
	 * @param msg Die CAN-Message mit den Fehlerdaten.
	 */
	public void setErrorValue(final MrwMessage msg)
	{
		final int index = msg.getData(MrwMessage.IDX_INFO_START);

		synchronized(this)
		{
			booted();
			switch(index)
			{
			case 0:
				error_flags = msg.getData(MrwMessage.IDX_INFO_START + 1);
				break;
			case 1:
				rx_err_counter = msg.getData(MrwMessage.IDX_INFO_START + 1);
				break;
			case 2:
				tx_err_counter = msg.getData(MrwMessage.IDX_INFO_START + 1);
				break;
			case 3:
				error_flags    = msg.getData(MrwMessage.IDX_INFO_START + 1);
				rx_err_counter = msg.getData(MrwMessage.IDX_INFO_START + 2);
				tx_err_counter = msg.getData(MrwMessage.IDX_INFO_START + 3);
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
	 * @param msg Die CAN-Meldung mit den versionsinformationen.
	 */
	public void setRevisionValue(final MrwMessage msg)
	{
		final int index = msg.getData(MrwMessage.IDX_INFO_START);

		booted();
		
		switch(index)
		{
		case 0:
			version = msg.getData(MrwMessage.IDX_INFO_START + 1);
			break;
		case 1:
			revision = (revision & 0xff00) | msg.getData(MrwMessage.IDX_INFO_START + 1);
			break;
		case 2:
			revision = (revision & 0x00ff) | (msg.getData(MrwMessage.IDX_INFO_START + 1) << 8);
			break;
		case 3:
			version  =
				 msg.getData(MrwMessage.IDX_INFO_START + 1);
			revision =
				 msg.getData(MrwMessage.IDX_INFO_START + 2) |
				(msg.getData(MrwMessage.IDX_INFO_START + 3) << 8);
			break;
			
		default:
			// Do nothing!
			break;
		}
	}
	
	/**
	 * Diese Methode gibt die Controller-ID zurück.
	 * @return Die Controller-ID.
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * Diese Methode fügt ein Gerät diesem Controller hinzu.
	 * @param unit Das zu steuernde Gerät.
	 */
	public void addDeviceUnit(final DeviceUnit unit)
	{
		units.add(unit);
	}
	
	/**
	 * Diese Methode fügt einer Liste alle CAN-Meldungen hinzu, die für das Konfigurieren
	 * dieses Controllers nötig sind.
	 * @param list Die Liste, die mit Konfigurationsmeldungen befüllt wird.
	 */
	public void addConfigMessages(final List<MrwMessage> list)
	{
		list.add(MrwMessage.createCommandMsg(Command.CFGBGN, id, 0));

		Collections.sort(units);
		for (DeviceUnit device : units)
		{
			MrwMessage msg = device.createConfigMessage();
			
			if (msg != null)
			{
				list.add(msg);
			}
			else
			{
				log.warn ("Gerät " + device + "kann nicht konfiguriert werden!");
			}
		}
		list.add(MrwMessage.createCommandMsg(Command.CFGEND, id, 0));
	}
	
	/**
	 * Diese Methode erzeugt einen Klartext über den Zustand dieses Controllers.
	 * @return Der Klartext über den Zustand dieses Controllers.
	 */
	public String toString()
	{
		StringWriter sw = new StringWriter();
		PrintWriter  pw = new PrintWriter(sw);
		
		pw.printf("Controller ID: %04x  Ver: %d.%d Ping: %5d Pong: %5d RXB: %5d TXB: %5d  EFlags: %02x RX error: %3d TX error: %3d",
			id, version, revision,
			ping_count.get(), pong_count.get(),
			rx_count, tx_count,
			error_flags, rx_err_counter, tx_err_counter);
		String result = sw.toString();
	    pw.close();
	    return result;
	}
}
