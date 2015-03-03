/*
**
**	$Filename:	SmokeTest.java $
**	$Revision$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
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

import org.junit.Test;

public class SmokeTest extends ControllerTestBase
{
	@Test()
	public void ping()
	{
		controller.pingModelRailWay();
	}
}
