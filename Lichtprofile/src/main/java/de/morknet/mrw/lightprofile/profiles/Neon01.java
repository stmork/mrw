/*
**
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Light dimming profiles
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

package de.morknet.mrw.lightprofile.profiles;

import de.morknet.mrw.lightprofile.LightProfile;

public class Neon01 extends LightProfile
{
	private final static int array[] =
	{
		0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
		128,128,128,128,128,96,96,
		128,160,255,255,255,255,255,
		32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
		128,160,255,255,255,255,
		32,32,32,32,32,32,32,
		128,160,255,255,255,255,255,
		144,160,176,208,240,255,255,255,
		32,32,32,32,32,32,32,32,32,64,
		128,131,134,137,140,143,
		146,149,152,155,158,161,164,167,170,173,176,179,182,185,188,191,194,197,200,203,
		205,207,209,211,213,215,217,219,221,223,224,225,226,227,228,229,230,231,232,233, 
		234,235,236,237,238,239,240,240,241,241,242,242,243,243,244,244,245,245,246,246,
		247,247,248,248,248,248,249,249,249,249,250,250,250,250,251,251,251,251,252,252, 
		252,252,253,253,253,253,254,254,254,254,254,254,254,255,255,255,255,255,255,255, 
		255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
		255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
		255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
	};

	@Override
	protected int [] getArray()
	{
		return array;
	}
}
