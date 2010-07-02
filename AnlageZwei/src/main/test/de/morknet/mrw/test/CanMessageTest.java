/*
**
**	$Filename:	CanMessageTest.java $
**	$Revision: 967 $
**	$Date: 2010-06-06 20:20:38 +0200 (So, 06. Jun 2010) $
**	$Author: smork $
**	$Id: CanMessageTest.java 967 2010-06-06 18:20:38Z smork $
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

import de.morknet.mrw.comm.can.CANMessage;
import de.morknet.mrw.util.LogUtil;

public class CanMessageTest
{
	private final static int TEST_SID = 1;
	private final static int TEST_EID = 2;

	@Test
	public void empty()
	{
		CANMessage msg = new CANMessage(0, 0, 0);
		Assert.assertEquals("Eine leere CAN-Message muss 7 Bytes haben!", msg.getBytes().length, 7);
		Assert.assertEquals("Eine leere CAN-Message darf keine Datenbytes haben!", msg.length(), 0);
		msg.dump("Test");
	}
	
	@Test
	public void id()
	{
		CANMessage msg = new CANMessage(0, 0, 0);

		msg.setSid(TEST_SID);
		Assert.assertEquals("Die Microcontroller-ID ist falsch!", msg.getSid(), TEST_SID);
		Assert.assertEquals("Die Gerätenummer ist falsch!", msg.getEid(), 0);
		Assert.assertFalse("Der CAN-Frame darf kein Extended Frame sein!", msg.isExtended());

		msg.setExtended(true);
		Assert.assertTrue("Der CAN-Frame muss ein Extended Frame sein!", msg.isExtended());

		msg.setEid(0);
		Assert.assertFalse("Der CAN-Frame darf kein Extended Frame sein!", msg.isExtended());

		msg.setEid(TEST_EID);
		Assert.assertEquals("Die Gerätenummer ist falsch!", msg.getEid(), TEST_EID);
		Assert.assertTrue("Der CAN-Frame muss ein Extended Frame sein!", msg.isExtended());
	}

	@Test
	public void bytedata()
	{
		for (int i = 0;i <= 8;i++)
		{
			CANMessage msg = new CANMessage(0, 0, 0);
			for (int b = 0; b < i;b++)
			{
				msg.addDataByte(b);
				Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b), b);
			}
			Assert.assertEquals(LogUtil.printf("Diese CAN-Message muss %d Bytes haben!", 7+i), msg.getBytes().length, 7 + i);
			Assert.assertEquals(LogUtil.printf("Diese CAN-Message muss %d Datenbytes haben!", i), msg.length(), i);
		}
	}

	@Test
	public void intdata()
	{
		for (int i = 0;i <= 4;i++)
		{
			CANMessage msg = new CANMessage(0, 0, 0);
			for (int b = 0; b < i;b++)
			{
				msg.addDataWord(b);
				Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b + b), b);
				Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b + b + 1), 0);
			}
			Assert.assertEquals(LogUtil.printf("Diese CAN-Message muss %d Bytes haben!", 7+i+i), msg.getBytes().length, 7+i+i);
			Assert.assertEquals(LogUtil.printf("Diese CAN-Message muss %d Datenbytes haben!", i+i), msg.length(), i + i);
		}
	}

	@Test(expected=IllegalStateException.class)
	public void overflow_byte()
	{
		CANMessage msg = new CANMessage(0, 0, 0);
		for (int b = 0; b < 8;b++)
		{
			msg.addDataByte(b);
			Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b), b);
		}
		msg.addDataByte(0xaa);
	}

	@Test(expected=IllegalStateException.class)
	public void overflow_int()
	{
		CANMessage msg = new CANMessage(0, 0, 0);
		for (int b = 0; b < 4;b++)
		{
			msg.addDataWord(b);
			Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b + b), b);
			Assert.assertEquals("Die Datenbytes müssen identisch sein!", msg.getData(b + b + 1), 0);
		}
		msg.addDataByte(0xaa);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getUnderflow()
	{
		CANMessage msg = new CANMessage(0, 0, 0);

		msg.getData(-1);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void modifyUnderflow()
	{
		CANMessage msg = new CANMessage(0, 0, 0);

		msg.modifyData(-1, 0xaf);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void modifyOverflow()
	{
		CANMessage msg = new CANMessage(0, 0, 0);

		msg.modifyData(0, 0xaf);
	}
}
