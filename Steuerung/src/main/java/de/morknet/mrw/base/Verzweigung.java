/*
**
**	$Filename:	Verzweigung.java $
**	$Revision$
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

import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse repräsentiert eine Verzweigung. Eine Verzweigung kann eine {@link Weiche} oder {@link DKW} sein.
 * @author sm
 *
 */
abstract public class Verzweigung extends Gleisteil
{
	private static final long serialVersionUID = 1L;

	/**
	 * Flag, ob Verzweigung Endabschaltung hat.
	 */
	protected final boolean limitStop;

	/**
	 * Dieser Konstruktor initialisiert eine Verzweigung.
	 * @param segment Der dazugehörende Gleisabschnitt.
	 * @param number Die Verzweigungsnummer.
	 * @param aIsHigh Zählrichtung.
	 * @param limitStop Flag, ob Verzweigung Endabschaltung hat.
	 */
	public Verzweigung(
			final Abschnitt segment,
			final String    number,
			final boolean   aIsHigh,
			final boolean   limitStop)
	{
		super(segment, number, aIsHigh);
		this.limitStop = limitStop;
	}

	/**
	 * Diese Methode meldet, ob diese Verzweigung neuerer Bauart mit Endabschaltung ist. 
	 * @return Zustand, ob Verzweigung Endabschaltung hat.
	 */
	public boolean hasLimitStop()
	{
		return this.limitStop;
	}

	/**
	 * Diese Methode ermittelt eine Verzweigung abhängig von der  Mikrocontroller-ID/Gerätenummer.
	 * @param id Die Mikrocontroller-ID/Gerätenummer.
	 * @return Die ermittelte Verzweigung.
	 */
	public static Verzweigung findVerzweigung(int id)
	{
		return (Verzweigung)findDeviceUnit(id);
	}
	
	/**
	 * Diese Methode erzeugt eine MRW-Meldung zur Abfrage der Weichenlage. 
	 * @param batch Der {@link Batch}, in den das Abfragekommando eingereiht wird.
	 */
	public void addQuestion(de.morknet.mrw.batch.Batch batch)
	{
		MrwMessage msg = MrwMessage.createCommandMsg(Command.GETDIR, ctrl_id, unit_no);
		batch.addBatchElement(this, msg);
	}

	@Override
	public MrwMessage createConfigMessage() {
		MrwMessage msg = MrwMessage.createCommandMsg(hasLimitStop() ? Command.CFGSWN : Command.CFGSWO, ctrl_id, unit_no);
		msg.addDataByte(pin);
		msg.addDataByte(pin + 1);
		if (hasLimitStop())
		{
			msg.addDataByte(pin + 8);
			msg.addDataByte(pin + 9);
		}
		return msg;
	}

	/**
	 * Diese Methode definiert die Weichenlage dieser Verzweigung neu.
	 * @param dir Die neue Weichenlage.
	 */
	abstract public void setDir(DirectionCode dir);
	
	/**
	 * Diese Methode ermittelt die aktuelle Weichenlage.
	 * @return Die aktuelle Weichenlage.
	 */
	abstract public DirectionCode getDirectionCode();
}
