/*
**
**	$Filename:	Config.java $
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

package de.morknet.mrw.comm.test;

import java.util.List;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse versorgt den CAN-Knoten mit der ID 7 mit einer Testkonfiguration.
 * @author sm
 *
 */
public class Config extends CANMain
{
	/**
	 * Die zentrale Methode zum Versenden und Empfangen von MRW-Meldungen.
	 * @param args Die Argumentliste des Programmaufrufs.
	 */
	public Config(String[] args)
	{
		super(args);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Config check = new Config(args);
		
		check.execute();
		check.wait(7);
	}

	@Override
	protected void fillBatch(List<MrwMessage> batch) throws Exception
	{
		MrwMessage msg;
		int id = 7;
		
		msg = MrwMessage.createCommandMsg(Command.CFGBGN, id, 0);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGSWO, id, 2020);
		msg.addDataByte(0);
		msg.addDataByte(1);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGSWO, id, 2021);
		msg.addDataByte(2);
		msg.addDataByte(3);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGSWN, id, 2019);
		msg.addDataByte(4);
		msg.addDataByte(5);
		msg.addDataByte(12);
		msg.addDataByte(13);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGSWN, id, 2001);
		msg.addDataByte(6);
		msg.addDataByte(7);
		msg.addDataByte(14);
		msg.addDataByte(15);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGRAI, id,  105);
		msg.addDataByte(22);
		msg.addDataByte(23);
		batch.add(msg);

		msg = MrwMessage.createCommandMsg(Command.CFGEND, id, 0);
		batch.add(msg);
	}
}
