/*
**
**	$Filename:	SelectTrackException.java $
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

package de.morknet.mrw.automatic.beermode;

/**
 * Diese Exception weist auf einen Fehler bei der Auswahl eines Gleises im Biermodus hin.
 * @see BeerMode
 * @author sm
 *
 */
public class SelectTrackException extends IllegalStateException
{
	/**
	 * Der Konstruktur bereitet die Fehlermeldung auf.
	 * @param message Die Fehlerursache.
	 */
	public SelectTrackException(String message)
	{
		super(message);
	}

	private static final long serialVersionUID = 1L;
}
