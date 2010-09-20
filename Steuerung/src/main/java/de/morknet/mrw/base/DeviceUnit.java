/*
**
**	$Filename:	DeviceUnit.java $
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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchElement;
import de.morknet.mrw.batch.BatchElementDefinedException;
import de.morknet.mrw.batch.UndefinedBatchElementException;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Basisklasse verwaltet ein schaltbares Ger�t. Ein solches Ger�t k�nnen Weichen, Signale und
 * Gleisabschnitte sein.
 * @author smork
 *
 */
abstract public class DeviceUnit extends NamedElement implements Comparable<DeviceUnit>
{
	private static final long serialVersionUID = 1L;

	private final Log    log; 
	private final Modell model;

	/**
	 * Die Mikrocontroller-ID. In einer CAN-Message ist das die Standard-ID.
	 */
	protected int   ctrl_id;

	/**
	 * Die Ger�tenummer. In einer CAN-Message ist das die Extended-ID.
	 */
	protected int   unit_no;
	
	/**
	 * Der Startpin, ab der sich dieses Ger�t am Mikrocontroller befindet. 
	 */
	protected int   pin;
	
	/**
	 * Die Gesamtzahl der ben�tigten IO-Pins am Mikrocontroller.
	 */
	protected int   count;
	
	/**
	 * Die verstrichene Gesamtzeit an Schaltticks im Mikrocontroller.
	 */
	protected int   elapsed;

	/**
	 * Die Zahl der Schaltauftr�ge. Zusammen mit {@link #elapsed} kann daraus die durchschnittliche Schaltzeit berechnet werden.
	 */
	protected int   elapsedCount;

	/**
	 * Eine Zuordnung zu einem {@link BatchElement}, das wiederum in einem {@link Batch} gefasst sein kann.
	 */
	private BatchElement element;
	private final static Map<Integer, DeviceUnit> devices = new HashMap<Integer, DeviceUnit>();
	
	/**
	 * Diese abstrakte Methode gibt das Schaltkommando zur�ck.
	 * @return Das Schaltkommando passend zu diesem Ger�t. 
	 */
	abstract protected Command getCommand();
	
	/**
	 * Diese abstrakte Methode definiert das erzeugen einer Konfigurationsmeldung.
	 * @return Die Konfigurationsmeldung.
	 * @see MrwMessage
	 */
	abstract public MrwMessage createConfigMessage();

	/**
	 * Dieser Konstruktur initialisiert dieses Ger�t in ein Modell und benennt es.
	 * @param modell Das Modell, zu dem dieses Ger�t geh�rt.
	 * @param name Der Name dieses Ger�ts.
	 */
	public DeviceUnit(final Modell modell, final String name)
	{
		super(name);
		this.log = LogFactory.getLog("DeviceUnit " + name);
		this.model = modell; 
	}

	/**
	 * Diese Methode definiert f�r dieses Ger�t die Controller-ID und die Ger�tenummer.
	 * @param ctrl_id Die zu setzende Controller-ID.
	 * @param unit_no Die zu setzende Ger�tenummer.
	 */
	final public void setMicroControllerId(final int ctrl_id, final int unit_no)
	{
		int id = (ctrl_id << 16) | unit_no;
		this.ctrl_id = ctrl_id;
		this.unit_no = unit_no;
		
		MicroController c = model.findMicroController(ctrl_id);
		if (c != null)
		{
			c.addDeviceUnit(this);
		}
		else
		{
			throw new UnknownDeviceException("Unbekannter Mikrocontroller!", ctrl_id);
		}
		devices.put(id, this);
	}

	/**
	 * Diese Methode gibt die Microcontroller-ID zur�ck.
	 * @return Die Microcontroller-ID-
	 */
	final public int getMicroControllerId()
	{
		return this.ctrl_id;
	}

	/**
	 * Diese Methode gibt die Ger�tenummer zur�ck.
	 * @return Die Ger�tenummer.
	 */
	final public int getDeviceUnitNumber()
	{
		return this.unit_no;
	}
	
	/**
	 * Gibt das Modell dieses Abschnitts zur�ck.
	 * @return Das Modell dieser Anlage.
	 */
	public Modell getModell()
	{
		return this.model;
	}


	/**
	 * Diese Methode sucht ein Ger�t an Hand ihrer ID. Die ID setzt sich aus Controller-ID und Ger�tenummer zusammen.
	 * @param id Die Ger�te-ID.
	 * @return Das gefundene Ger�t.
	 */
	final public static DeviceUnit findDeviceUnit(final int id)
	{
		DeviceUnit device = devices.get(id);
		if (device == null)
		{
			throw new UnknownDeviceException("Eine Ger�te-ID l�sst sich nicht auf ein Ger�t abbilden!", id);
		}
		return device;
	}

	/**
	 * Diese Methode definiert den Startpin und die Anzahl der ben�tigten IO-Pins.
	 * @param pin Der Startpin, ab dem der Mikrocontroller dieses Ger�t verwaltet.
	 * @param count Die Zahl der ben�tigten IO-Pins.
	 */
	final public void setPinConfiguration (final int pin, final int count)
	{
		this.pin = pin;
		this.count = count;
	}

	/**
	 * Diese Methode erzeugt eine CAN-Meldung und initialisiert diese mit den IDs
	 * dieses Ger�ts.
	 * @return Die ger�teabh�ngig erzeugte CAN-Meldung.
	 * @see MrwMessage
	 */
	protected MrwMessage createMsg()
	{
		return MrwMessage.createCommandMsg(
			getCommand(),
			ctrl_id, unit_no);
	}

	/**
	 * Diese Methode f�gt einer Konfigurations-Meldung die IO-Pin-Belegung bei.
	 * @param msg
	 */
	protected void addPinConfig(final MrwMessage msg)
	{
		for (int i = 0;i < count;i++)
		{
			msg.addDataByte(pin + i);
		}
	}

	/**
	 * Diese Methode f�gt einem Schaltauftrag Daten hinzu. Je nach Ger�t wird diese
	 * Methode �berladen und daher in dieser Basisimplementierung leer.
	 * @param msg Die CAN-Meldung, der Daten hinzugef�gt werden sollen.
	 */
	protected void addData(MrwMessage msg)
	{
	}
	
	/**
	 * Diese Methode f�gt einen Schaltauftrag einem Verarbeitungsstapel hinzu
	 * @param batch Der Kommandostapel.
	 */
	final public void addCommand(final Batch batch)
	{
		MrwMessage msg = createMsg();

		addData(msg);
		batch.addBatchElement(this, msg);
	}

	/**
	 * Diese Methode f�gt dem Ger�t eine Schaltzeit hinzu. So k�nnen durchschnittliche
	 * Schaltzeiten ermittelt werden. 
	 * @param elapsed Die hinzuzuf�gende Schaltzeit als Zeitquantum. Dieses Zeitquantum
	 *  entspricht der Periodendauer eines Timerinterrupts auf dem Mikrocontroller. Ist
	 *  dieses Quantum 0, wird es nicht registriert.
	 */
	final public void addElapsed(final int elapsed)
	{
		if (elapsed > 0)
		{
			this.elapsed += elapsed;
			elapsedCount++;
		}
	}
	
	/**
	 * Diese Methode gibt die durchschnittliche Schaltzeit zur�ck.
	 * @param quantum Das Timerquantum, dass der Berechnung zugrunde liegt.
	 * @return Die absolute durchschnittliche Schaltzeit in Sekunden.
	 */
	final public double getSwitchTime(final double quantum)
	{
		return elapsedCount > 0 ? quantum * elapsed / elapsedCount : 0.0;
	}

	/**
	 * Diese Vergleichsmethode sorgt daf�r, dass Ger�te gem�� ihrer ansteigenden
	 * Pin-Konfiguration sortiert werden. Das macht das Debuggen der Ger�tekonfiguration
	 * leichter.
	 * @param unit Das zu vergleichende Ger�t.
	 */
	final public int compareTo(final DeviceUnit unit)
	{
		if (unit != null)
		{
			return this.pin - unit.pin;
		}
		else
		{
			return -1;
		}
	}

	@Override
	public boolean equals(Object arg0)
	{
		return super.equals(arg0);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	/**
	 * Diese Methode weist ein {@link BatchElement} diesem Ger�t zu. Damit wird signalisiert, dass
	 * ein Kommando verschickt wurde. Das BatchElement darf nicht null sein, ansonsten wird eine
	 * {@link UndefinedBatchElementException} geworfen.
	 * @param element Das mit dem Ger�t verbundene BatchElement mit dem verschickten Kommando.
	 * @throws UndefinedBatchElementException Falls das BatchElement null war.
	 * @throws BatchElementDefinedException Falls schon ein BatchElement vorher definiert war.
	 */
	final public void setBatchElement(final BatchElement element)
	{
		if (element == null)
		{
			throw new UndefinedBatchElementException(this);
		}
		if (this.element != null)
		{
			log.error("BatchElement bereits definiert!");
			log.error("Definiertes BatchElement: " + this.element);
			log.error("Versuchtes BatchElement:  " + element);
			throw new BatchElementDefinedException(this);
		}
		this.element = element;
	}
	
	/**
	 * Diese Methode markiert diesem Ger�t, dass die Kommandoverarbeitung abgeschlossen wurde. Das
	 * {@link BatchElement} wird ausgetragen.
	 */
	public void clearBatchElement()
	{
		this.element = null;
	}
	
	/**
	 * Diese Methode meldet zur�ck, ob ein Kommando zur Verarbeitung verschickt wurde.
	 * @return Zustand, ob ein Kommando zur Verarbeitung verschickt wurde und die Ausf�hrung noch aussteht.
	 */
	public boolean isProcessing()
	{
		return this.element != null;
	}

	/**
	 * Diese Methode gibt im Falle einer ausstehenden Kommandoverarbeitung das damit verbundene
	 * {@link BatchElement} zur�ck. Ist keine Kommandoverarbeitung ausstehend, wird eine
	 * {@link UndefinedBatchElementException} geworfen.
	 * @return Das mit einer ausstehenden Kommandoverarbeitung verbundene {@link BatchElement}.
	 */
	public BatchElement getBatchElement()
	{
		if (this.element == null)
		{
			throw new UndefinedBatchElementException(this);
		}
		return this.element;
	}
}
