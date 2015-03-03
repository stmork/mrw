/*
**
**	$Filename:	Eisenbahn.java $
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Eisenbahn extends RailwayBase
{
	private final static Log log = LogFactory.getLog(Eisenbahn.class);

	private Eisenbahn() throws Exception
	{
		super();
	}

	public static void main(String[] args)
	{
		log.info("Guten Tag!");
		
		try
		{
			Eisenbahn eisenbahn = new Eisenbahn();
			eisenbahn.test();
			eisenbahn.close();
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
