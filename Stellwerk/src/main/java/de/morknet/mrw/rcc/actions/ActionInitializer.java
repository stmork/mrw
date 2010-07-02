/*
**
**	$Filename:	ActionInitializer.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: ActionInitializer.java 954 2010-05-01 09:31:46Z smork $
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
 * Diese Klasse verwaltet die {@link Action}s. Diese können in der GUI als Menüelemente angezeigt werden.
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
	 * Diese Methode gibt alle bekannten {@link MrwAction}s zurück.
	 * @return Die Liste der {@link MrwAction}.
	 */
	public static List<MrwAction> getActions()
	{
		return actions;
	}
}
