/*
**
**	$Filename:	SetId.java $
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

package de.morknet.mrw.comm.test;

import java.util.List;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse versendet das SET_ID-Kommando.
 * @author sm
 *
 */
public class SetId extends CANMain
{
	/**
	 * Die zentrale Methode zum Versenden und Empfangen von MRW-Meldungen.
	 * @param args Die Argumentliste des Programmaufrufs.
	 */
	public SetId(String[] args)
	{
		super(args);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SetId check = new SetId(args);
		
		check.execute();
		check.wait(10);
	}

	@Override
	protected void fillBatch(List<MrwMessage> list) throws Exception
	{
		MrwMessage msg;
		
		msg = MrwMessage.createPingMsg();
		list.add(msg);
		
		msg = new MrwMessage();
		msg.addDataByte(Command.SET_ID);
		msg.addDataWord(104);
		list.add(msg);
	}
}
