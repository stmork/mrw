/*
**
**	$Filename:	Eisenbahn.java $
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

package de.itemis.mrw.test;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.gui.info.Layout;

public class Eisenbahn {
	private final static Log log = LogFactory.getLog(Eisenbahn.class);

	public static void main(String[] args) {
		log.info("Guten Tag!");
		Modell model = ModellFactory.getInstance();
		
		int errors = model.validate();
		log.info("Validierung vollstÃ€ndig. Fehlerzahl: " + errors);
		
		if (errors == 0)
		{
			try
			{
				Layout layout = new Layout(model);

				layout.save();
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}
}
