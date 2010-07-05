/*
**
**	$Filename:	MrwMessageTest.java $
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

package de.morknet.mrw.test;

import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;

public class MrwMessageTest
{
	private final static int     TEST_SID     = 1;
	private final static int     TEST_EID     = 2;
	private final static Command TEST_COMMAND = Command.GETRBS;
	private final static MsgCode TEST_MSGCODE = MsgCode.MSG_OK;

	@Test
	public void create()
	{
		Assert.assertNotNull("PING-Message konnte nicht erzeugt werden!", MrwMessage.createPingMsg());
		Assert.assertNotNull("RESET-Message konnte nicht erzeugt werden!", MrwMessage.createResetMsg());
		Assert.assertNotNull("QRYBUF-Message konnte nicht erzeugt werden!", MrwMessage.createQueryBufferMsg());
		Assert.assertNotNull("QRYERR-Message konnte nicht erzeugt werden!", MrwMessage.createQueryErrorMsg());
		Assert.assertNotNull("GETVER-Message konnte nicht erzeugt werden!", MrwMessage.createGetVersionMsg());
		
		MrwMessage msg = MrwMessage.createCommandMsg(TEST_COMMAND, TEST_SID, TEST_EID);
		Assert.assertEquals("Die Microcontroller-ID ist falsch!", msg.getSid(), TEST_SID);
		Assert.assertEquals("Die Gerätenummer ist falsch!", msg.getEid(), TEST_EID);
		Assert.assertTrue("Der CAN-Frame muss ein Extended Frame sein!", msg.isExtended());
	}

	@Test
	public void result()
	{
		MrwMessage msg = MrwMessage.createResultMessage(TEST_COMMAND, TEST_MSGCODE, TEST_SID, TEST_EID);
		
		Assert.assertTrue("MRW-Message muss Antwort sein!", msg.isResult());
		Assert.assertEquals("Der Kommando-Code ist falsch!", msg.getCommand(), TEST_COMMAND);
		Assert.assertEquals("Der Antwort-Code ist falsch!", msg.getResultCode(), TEST_MSGCODE);
		Assert.assertEquals("Die Microcontroller-ID ist falsch", msg.getSourceControllerId(), TEST_SID);
		Assert.assertEquals("Die Gerätenummer ist falsch!", msg.getSourceUnitNo(), TEST_EID);
		Assert.assertEquals("Die Result-ID ist falsch!", msg.getResultId(), (TEST_SID << 16) | TEST_EID);
	}
	
	@Test(expected=IllegalStateException.class)
	public void command()
	{
		MrwMessage msg = new MrwMessage();
		
		msg.addDataByte(0);
		msg.addDataByte(TEST_COMMAND);
	}
}
