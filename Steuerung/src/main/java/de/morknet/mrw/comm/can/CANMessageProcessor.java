/*
**
**	$Filename:	CANMessageProvider.java $
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

package de.morknet.mrw.comm.can;

/**
 * Dieses Interface definiert die Schnittstelle zum Auswerten von CAN-Messages.
 * @author sm
 *
 */
public interface CANMessageProcessor
{
	/**
	 * Diese Methode wertet eine empfangene CAN-Message aus.
	 * @param msg Die empfangene CAN-Message.
	 */
	public void process(CANMessage msg);
	
	/**
	 * Diese Methode instantiiert eine CAN-Message.
	 * @return Die erzeugte leere CAN-Message.
	 */
	public CANMessage createMsg();
}
