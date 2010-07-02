/*
**
**	$Filename:	CodeNotFoundException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: CodeNotFoundException.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.comm;

import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese RuntimeException wird geworfen, wenn ein Code in einer Enum nicht aufgelöst werden kann. Die
 * enums {@link SignalCode}, {@link MsgCode} und {@link Command} stellen diese Funktionalität zur Verfügung.
 * @author smork
 *
 */
public class CodeNotFoundException extends MrwException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor bereitet eine Fehlermeldung auf.
	 * @param cls Die Enumeration, die den Code nicht auflösen konnte 
	 * @param code Der nicht aufzulösende Code.
	 */
	public CodeNotFoundException(Class<?> cls, int code)
	{
		super(LogUtil.printf("Code %d für %s nicht gefunden!", code, cls.getSimpleName()));
	}
}
