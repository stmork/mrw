/*
**
**	$Filename:	MrwRandom.java $
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

package de.morknet.mrw.util;

import java.security.SecureRandom;

/**
 * Diese Klasse bietet gute Zufallszahlen. Als Basis wird die Klasse {@link SecureRandom} genommen.
 * @author sm
 *
 */
public class MrwRandom extends SecureRandom
{
	private final static int RANGE      = 32767;
	private final static int DICE_COUNT = 16;

	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor initialisiert die Zufallszahlen.
	 */
	public MrwRandom()
	{
		super(SecureRandom.getSeed(4));
	}
	
	@Override
	public int nextInt(int size)
	{
		final int max = RANGE - RANGE % size;
		int q = 0;
		for (int i = 0;i < DICE_COUNT;i++)
		{
			q += super.nextInt(max);
		}
		return q % size;
	}
}
