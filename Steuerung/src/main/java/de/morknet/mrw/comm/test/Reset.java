/*
**
**	$Filename:	Resets.java $
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

import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse versendet an alle Mikrocontroller das RESET-Kommando.
 * @author sm
 *
 */
public class Reset extends CANMain 
{
	/**
	 * Die zentrale Methode zum Versenden und Empfangen von MRW-Meldungen.
	 * @param args Die Argumentliste des Programmaufrufs.
	 */
	public Reset(String[] args)
	{
		super(args);
	}

	@Override
	protected void fillBatch(List<MrwMessage> list) throws Exception
	{
		list.add(MrwMessage.createResetMsg());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Reset check = new Reset(args);
		
		check.execute();
		check.wait(4);
	}
}
