/*
**
**	$Filename:	SwitchTest.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: SwitchTest.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.UnknownDirectionCodeException;
import de.morknet.mrw.UnknownTurnStateException;
import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Weiche;

public class SwitchTest extends SwitchTestBase
{
	private final Weiche    swt = (Weiche)model.findMagneticPart("Weiche 7");
	private final Gleisteil a   = swt.getA();
	private final Gleisteil b   = swt.getB();
	private final Gleisteil c   = swt.getC();

	@Test
	public void turnSwitch()
	{
		swt.turn(batch, a, b);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.LEFT,  swt.getDirectionCode());
		swt.turn(batch, a, c);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.RIGHT, swt.getDirectionCode());
		swt.turn(batch, b, a);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.LEFT,  swt.getDirectionCode());
		swt.turn(batch, c, a);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.RIGHT, swt.getDirectionCode());
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchError()
	{
		swt.turn(batch, swt, swt);
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchErrorAA()
	{
		swt.turn(batch, a, a);
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchErrorBB()
	{
		swt.turn(batch, b, b);
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchErrorCC()
	{
		swt.turn(batch, c, c);
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchErrorBC()
	{
		swt.turn(batch, b, c);
	}
	
	@Test(expected=UnknownTurnStateException.class)
	public void turnSwitchErrorCB()
	{
		swt.turn(batch, c, b);
	}
	
	@Test
	public void dkwDirCode()
	{
		swt.setDir(DirectionCode.LEFT);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.LEFT,  swt.getDirectionCode());
		swt.setDir(DirectionCode.RIGHT);
		Assert.assertSame("Weichenlage falsch!", DirectionCode.RIGHT, swt.getDirectionCode());
	}
	
	@Test(expected=UnknownDirectionCodeException.class)
	public void dkwDirCodeArctError()
	{
		swt.setDir(DirectionCode.ARC);
	}
	
	@Test(expected=UnknownDirectionCodeException.class)
	public void dkwDirCodeCrossError()
	{
		swt.setDir(DirectionCode.CROSS);
	}
}
