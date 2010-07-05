/*
**
**	$Filename:	ByteProcessor.java $
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

package de.morknet.mrw.comm;

import de.morknet.mrw.comm.can.CANMessage;

/**
 * Dieses Interface definiert das Verhalten für das Verarbeiten eines einzelnen Bytes, dass von der Modelleisenbahnanlage
 * gesendet wurde. Die Bytes werden gesammelt und zu einer MRW-Message zusammengebaut.
 * @see CANMessage
 * @author sm
 *
 */
public interface ByteProcessor
{
	/**
	 * Diese Methode bearbeitet ein einzelnes Byte. Aufeinanderfolgende Bytes werden zu einer CAN-Message zusammengebaut.
	 * @param input Das empfangene Byte.
	 */
	public void processByte(int input);
}
