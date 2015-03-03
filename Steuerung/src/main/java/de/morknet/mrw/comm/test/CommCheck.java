/*
**
**	$Filename:	CommCheck.java $
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

import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse sendet ein PING an eine Modelleisenbahn.
 * @author sm
 *
 */
public class CommCheck extends CANMain
{
	/**
	 * Die zentrale Methode zum Versenden und Empfangen von MRW-Meldungen.
	 * @param args Die Argumentliste des Programmaufrufs.
	 */
	public CommCheck(String[] args)
	{
		super(args);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CommCheck check = new CommCheck(args);
		
		check.execute();
		check.wait(5);
	}

	@Override
	protected void fillBatch(java.util.List<MrwMessage> list) throws Exception
	{
		for (int i = 0; i < 2;i++)
		{
			list.add(MrwMessage.createPingMsg());
		}
	}
}
