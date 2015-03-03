/*
**
**	$Filename:	HearableTests.java $
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
package de.morknet.mrw.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {
	UtilTest.class,
	EnumTest.class,
	CanMessageTest.class,
	MrwMessageTest.class,
	ControllerTest.class,
	SimpleRouteTest.class})
public class HearableTests
{
}
