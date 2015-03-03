/*
**
**	$Filename:	ActionInitializer.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.rcc.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;

/**
 * Diese Klasse verwaltet die {@link Action}s. Diese kÃÂ¶nnen in der GUI als MenÃÅelemente angezeigt werden.
 * @author sm
 *
 */
abstract public class ActionInitializer
{
	private final static List<MrwAction> actions = new ArrayList<MrwAction>();

	/**
	 * Mit dieser Methode wird eine {@link MrwAction} registriert.
	 * @param action Die zu registrierende {@link MrwAction}.
	 */
	public static void addAction(MrwAction action)
	{
		actions.add(action);
	}
	
	/**
	 * Diese Methode gibt alle bekannten {@link MrwAction}s zurÃÅck.
	 * @return Die Liste der {@link MrwAction}.
	 */
	public static List<MrwAction> getActions()
	{
		return actions;
	}
}
