/*
**
**	$Filename:	BatchElement.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: BatchElement.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.batch;

import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse repräsentiert ein CAN-Kommando und das dazugehörige Gerät.
 * @author smork
 *
 */
public class BatchElement
{
	private final Batch      batch;
	private final DeviceUnit device;
	private final MrwMessage msg;
	private       MsgCode    resultCode;

	/**
	 * Dieser Konstruktur initialisiert alle Informationen für ein Schaltkommando
	 * @param batch Der {@link Batch}, der das Kommando enthält.
	 * @param device Das zu schaltende Gerät.
	 * @param msg Die MRW-Message
	 * @see DeviceUnit
	 * @see MrwMessage
	 */
	public BatchElement(Batch batch, DeviceUnit device, MrwMessage msg)
	{
		this.batch  = batch;
		this.msg    = msg;
		this.device = device;
	}

	/**
	 * Diese Methode gibt den Batch zurück, der zu diesem Element gehört.
	 * @return Der Batch, der dieses BatchElement enthält.
	 */
	public Batch getBatch()
	{
		return this.batch;
	}
			
	/**
	 * Diese Methode gibt das Kommando als CAN-Meldung zurück. 
	 * @return Die CAN-Meldung dieses BatchElements.
	 */
	public MrwMessage getMessage()
	{
		return this.msg;
	}

	/**
	 * Setzt den Rückmelde-Code zu diesem Batchelement.
	 * @param result Der zu setzende Rückmelde-Code.
	 */
	public void setResultCode(MsgCode result)
	{
		this.resultCode = result;
	}
	
	/**
	 * Diese Methode gibt Informationen zu diesem BatchElement als Text zurück.
	 * @return Infotext zu diesem BatchElement.
	 */
	public String toString()
	{
		return resultCode != null ?
				LogUtil.printf("%s %02x [%s] %s", msg, resultCode.getMsgCode(), getDeviceUnit().getName(), batch.getIdString()) :
				LogUtil.printf("%s -- [%s] %s", msg, getDeviceUnit().getName(), batch.getIdString());
	}

	/**
	 * Gibt das mit diesem BatchElement verbundene Gerät zurück.
	 * @return Das Gerät dieses BatchElements.
	 */
	public DeviceUnit getDeviceUnit()
	{
		return this.device;
	}
}
