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
 * Diese Basisklasse verwaltet ein schaltbares Gerät. Ein solches Gerät können Weichen, Signale und
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
	 * Die Gerätenummer. In einer CAN-Message ist das die Extended-ID.
	 */
	protected int   unit_no;
	
	/**
	 * Der Startpin, ab der sich dieses Gerät am Mikrocontroller befindet. 
	 */
	protected int   pin;
	
	/**
	 * Die Gesamtzahl der benötigten IO-Pins am Mikrocontroller.
	 */
	protected int   count;
	
	/**
	 * Die verstrichene Gesamtzeit an Schaltticks im Mikrocontroller.
	 */
	protected int   elapsed;

	/**
	 * Die Zahl der Schaltaufträge. Zusammen mit {@link #elapsed} kann daraus die durchschnittliche Schaltzeit berechnet werden.
	 */
	protected int   elapsedCount;

	/**
	 * Eine Zuordnung zu einem {@link BatchElement}, das wiederum in einem {@link Batch} gefasst sein kann.
	 */
	private BatchElement element;
	private final static Map<Integer, DeviceUnit> devices = new HashMap<Integer, DeviceUnit>();
	
	/**
	 * Diese abstrakte Methode gibt das Schaltkommando zurück.
	 * @return Das Schaltkommando passend zu diesem Gerät. 
	 */
	abstract protected Command getCommand();
	
	/**
	 * Diese abstrakte Methode definiert das erzeugen einer Konfigurationsmeldung.
	 * @return Die Konfigurationsmeldung.
	 * @see MrwMessage
	 */
	abstract public MrwMessage createConfigMessage();

	/**
	 * Dieser Konstruktur initialisiert dieses Gerät in ein Modell und benennt es.
	 * @param modell Das Modell, zu dem dieses Gerät gehört.
	 * @param name Der Name dieses Geräts.
	 */
	public DeviceUnit(final Modell modell, final String name)
	{
		super(name);
		this.log = LogFactory.getLog("DeviceUnit " + name);
		this.model = modell; 
	}

	/**
	 * Diese Methode definiert für dieses Gerät die Controller-ID und die Gerätenummer.
	 * @param ctrl_id Die zu setzende Controller-ID.
	 * @param unit_no Die zu setzende Gerätenummer.
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
	 * Diese Methode gibt die Microcontroller-ID zurück.
	 * @return Die Microcontroller-ID-
	 */
	final public int getMicroControllerId()
	{
		return this.ctrl_id;
	}

	/**
	 * Diese Methode gibt die Gerätenummer zurück.
	 * @return Die Gerätenummer.
	 */
	final public int getDeviceUnitNumber()
	{
		return this.unit_no;
	}
	
	/**
	 * Gibt das Modell dieses Abschnitts zurück.
	 * @return Das Modell dieser Anlage.
	 */
	public Modell getModell()
	{
		return this.model;
	}


	/**
	 * Diese Methode sucht ein Gerät an Hand ihrer ID. Die ID setzt sich aus Controller-ID und Gerätenummer zusammen.
	 * @param id Die Geräte-ID.
	 * @return Das gefundene Gerät.
	 */
	final public static DeviceUnit findDeviceUnit(final int id)
	{
		DeviceUnit device = devices.get(id);
		if (device == null)
		{
			throw new UnknownDeviceException("Eine Geräte-ID lässt sich nicht auf ein Gerät abbilden!", id);
		}
		return device;
	}

	/**
	 * Diese Methode definiert den Startpin und die Anzahl der benötigten IO-Pins.
	 * @param pin Der Startpin, ab dem der Mikrocontroller dieses Gerät verwaltet.
	 * @param count Die Zahl der benötigten IO-Pins.
	 */
	final public void setPinConfiguration (final int pin, final int count)
	{
		this.pin = pin;
		this.count = count;
	}

	/**
	 * Diese Methode erzeugt eine CAN-Meldung und initialisiert diese mit den IDs
	 * dieses Geräts.
	 * @return Die geräteabhängig erzeugte CAN-Meldung.
	 * @see MrwMessage
	 */
	protected MrwMessage createMsg()
	{
		return MrwMessage.createCommandMsg(
			getCommand(),
			ctrl_id, unit_no);
	}

	/**
	 * Diese Methode fügt einer Konfigurations-Meldung die IO-Pin-Belegung bei.
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
	 * Diese Methode fügt einem Schaltauftrag Daten hinzu. Je nach Gerät wird diese
	 * Methode überladen und daher in dieser Basisimplementierung leer.
	 * @param msg Die CAN-Meldung, der Daten hinzugefügt werden sollen.
	 */
	protected void addData(MrwMessage msg)
	{
	}
	
	/**
	 * Diese Methode fügt einen Schaltauftrag einem Verarbeitungsstapel hinzu
	 * @param batch Der Kommandostapel.
	 */
	final public void addCommand(final Batch batch)
	{
		MrwMessage msg = createMsg();

		addData(msg);
		batch.addBatchElement(this, msg);
	}

	/**
	 * Diese Methode fügt dem Gerät eine Schaltzeit hinzu. So können durchschnittliche
	 * Schaltzeiten ermittelt werden. 
	 * @param elapsed Die hinzuzufügende Schaltzeit als Zeitquantum. Dieses Zeitquantum
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
	 * Diese Methode gibt die durchschnittliche Schaltzeit zurück.
	 * @param quantum Das Timerquantum, dass der Berechnung zugrunde liegt.
	 * @return Die absolute durchschnittliche Schaltzeit in Sekunden.
	 */
	final public double getSwitchTime(final double quantum)
	{
		return elapsedCount > 0 ? quantum * elapsed / elapsedCount : 0.0;
	}

	/**
	 * Diese Vergleichsmethode sorgt dafür, dass Geräte gemäß ihrer ansteigenden
	 * Pin-Konfiguration sortiert werden. Das macht das Debuggen der Gerätekonfiguration
	 * leichter.
	 * @param unit Das zu vergleichende Gerät.
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
	 * Diese Methode weist ein {@link BatchElement} diesem Gerät zu. Damit wird signalisiert, dass
	 * ein Kommando verschickt wurde. Das BatchElement darf nicht null sein, ansonsten wird eine
	 * {@link UndefinedBatchElementException} geworfen.
	 * @param element Das mit dem Gerät verbundene BatchElement mit dem verschickten Kommando.
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
	 * Diese Methode markiert diesem Gerät, dass die Kommandoverarbeitung abgeschlossen wurde. Das
	 * {@link BatchElement} wird ausgetragen.
	 */
	public void clearBatchElement()
	{
		this.element = null;
	}
	
	/**
	 * Diese Methode meldet zurück, ob ein Kommando zur Verarbeitung verschickt wurde.
	 * @return Zustand, ob ein Kommando zur Verarbeitung verschickt wurde und die Ausführung noch aussteht.
	 */
	public boolean isProcessing()
	{
		return this.element != null;
	}

	/**
	 * Diese Methode gibt im Falle einer ausstehenden Kommandoverarbeitung das damit verbundene
	 * {@link BatchElement} zurück. Ist keine Kommandoverarbeitung ausstehend, wird eine
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
