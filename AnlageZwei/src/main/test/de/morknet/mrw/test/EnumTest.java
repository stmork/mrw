/*
**
**	$Filename:	EnumTest.java $
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

import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.OccupationCode;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.base.Weiche.TurnDirection;
import de.morknet.mrw.comm.CodeNotFoundException;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.SensorCode;
import de.morknet.mrw.comm.SignalCode;

public class EnumTest
{
	@Test
	public void occupationCode()
	{
		Assert.assertTrue(OccupationCode.isFree(0));
		Assert.assertFalse(OccupationCode.isFree(1));
		Assert.assertFalse(OccupationCode.isFree(2));
		Assert.assertFalse(OccupationCode.isOccupied(0));
		Assert.assertTrue(OccupationCode.isOccupied(1));
		Assert.assertTrue(OccupationCode.isOccupied(2));
		Assert.assertSame(OccupationCode.FREE, OccupationCode.getOccupationCode(0));
		Assert.assertSame(OccupationCode.FREE, OccupationCode.valueOf("FREE"));
		Assert.assertSame(OccupationCode.OCCUPIED, OccupationCode.getOccupationCode(1));
		Assert.assertSame(OccupationCode.OCCUPIED, OccupationCode.valueOf("OCCUPIED"));
	}
	
	@Test(expected=CodeNotFoundException.class)
	public void occupationCodeErrorInt()
	{
		OccupationCode.getOccupationCode(2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void occupationCodeErrorString()
	{
		OccupationCode.valueOf("XXX");
	}

	@Test
	public void directionCode()
	{
		Assert.assertSame(DirectionCode.LEFT,  DirectionCode.valueOf("LEFT"));
		Assert.assertSame(DirectionCode.RIGHT, DirectionCode.valueOf("RIGHT"));
		Assert.assertSame(DirectionCode.ARC,   DirectionCode.valueOf("ARC"));
		Assert.assertSame(DirectionCode.CROSS, DirectionCode.valueOf("CROSS"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void directionCodeErrorString()
	{
		DirectionCode.valueOf("XXX");
	}

	@Test
	public void command()
	{
		for(Command cmd : Command.values())
		{
			Assert.assertSame(cmd, Command.valueOf(cmd.name()));
			Assert.assertSame(cmd, Command.getCommand(cmd.getCommand()));
			
			int result = Command.makeResult(cmd);
			Assert.assertTrue(Command.isResult(result));
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void commandErrorString()
	{
		Command.valueOf("XXX");
	}

	@Test
	public void msgCode()
	{
		for(MsgCode code : MsgCode.values())
		{
			Assert.assertSame(code, MsgCode.valueOf(code.name()));
			Assert.assertSame(code, MsgCode.getMsgCode(code.getMsgCode()));
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void msgCodeErrorString()
	{
		MsgCode.valueOf("XXX");
	}

	@Test
	public void signalCode()
	{
		for(SignalCode code : SignalCode.values())
		{
			Assert.assertSame(code, SignalCode.valueOf(code.name()));
			Assert.assertSame(code, SignalCode.getSignalCode(code.getSignalCode()));
			Assert.assertNotNull(code.getShortCode());
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void signalCodeErrorString()
	{
		SignalCode.valueOf("XXX");
	}

	@Test
	public void sensorCode()
	{
		for(SensorCode code : SensorCode.values())
		{
			Assert.assertSame(code, SensorCode.valueOf(code.name()));
			Assert.assertSame(code, SensorCode.getSensorCode(code.getSensorCode()));
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void sensorCodeErrorString()
	{
		SensorCode.valueOf("XXX");
	}

	@Test
	public void turnDirectionBranch()
	{
		for(TurnDirection td : Weiche.TurnDirection.values())
		{
			Assert.assertSame(td, TurnDirection.valueOf(td.name()));
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void turnDirectionBranchErrorString()
	{
		TurnDirection.valueOf("XXX");
	}

	@Test
	public void turnDirectionDKW()
	{
		for(de.morknet.mrw.base.DKW.TurnDirection td : DKW.TurnDirection.values())
		{
			Assert.assertSame(td, de.morknet.mrw.base.DKW.TurnDirection.valueOf(td.name()));
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void turnDirectionDkwErrorString()
	{
		de.morknet.mrw.base.DKW.TurnDirection.valueOf("XXX");
	}
}
