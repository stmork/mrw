/*
**
**	$Filename:	ExceptionCallback.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ExceptionCallback.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.batch;

/**
 * Dieses Interface definiert Callbacks, die bei Fehlerauflösungen aufgerufen werden, die aus einem anderem Thread
 * heraus ausgelöst werden
 * @author sm
 *
 */
public interface ExceptionCallback
{
	/**
	 * Dieser Callback wird aufgerufen, wenn beim Versenden von Kommandos ein Fehler aufgetreten ist.
	 */
	public void errorOnSend();

	/**
	 * Dieser Callback wird aufgerufen, wenn beim Auflösen eines Fehlerzustandes wiederum ein Fehler aufgetreten ist.
	 */
	public void errorOnClear();
}
