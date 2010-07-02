/*
**
**	$Filename:	MrwAction.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: MrwAction.java 954 2010-05-01 09:31:46Z smork $
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

import org.eclipse.jface.action.Action;

import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.util.NameUtil;

/**
 * Diese Klasse definiert das Verhalten von bestimmten Aktivierungszuständen einer {@link Action}.
 * @author sm
 *
 */
public abstract class MrwAction extends org.eclipse.jface.action.Action
{
	/**
	 * Das steuernde {@link MrwActionControl}
	 */
	protected final MrwActionControl actionControl;
	private   final String           id;

	/**
	 * Dieser Konstruktur initialisiert diese Action.
	 * @param name Der Name dieser Action, der im Menü angezeigt wird.
	 * @param actionControl Der steuernde {@link MrwActionControl}.
	 */
	protected MrwAction(final String name, final MrwActionControl actionControl)
	{
		super(name, AS_CHECK_BOX);
		this.id = NameUtil.getRegistryIdString(name);
		this.actionControl = actionControl;
	}

	/**
	 * Dieser Callback sorgt für das Aktualisieren der GUI.
	 */
	abstract public void update();

	/**
	 * Dieser Callback wird aufgerufen, wenn diese Action aktiviert wurde.
	 * @return Erfolg
	 */
	protected final boolean onActivate()
	{
		return actionControl.onActivate();
	}

	/**
	 * Dieser Callback wird aufgerufen, wenn diese Action deaktiviert wurde.
	 * @return Erfolg
	 * @see MrwActionControl
	 */
	protected final boolean onDeactivate()
	{
		return actionControl.onDeactivate();
	}

	/**
	 * Diese Methode wird beim De-/aktivieren dieser {@link Action} aufgerufen. Die Implementierung
	 * steuert den Aktivierungszustand und ruft ggf. Aktualisierungen der GUI auf.
	 */
	@Override
	public final void run()
	{
		actionControl.run();
	}

	/**
	 * Diese Methode gibt den Aktivierungszustand dieser Action wieder.
	 * @return Aktivierungszustand.
	 */
	public final boolean isActive()
	{
		return actionControl.isActive();
	}

	/**
	 * Diese Methode deaktiviert diese Action.
	 */
	public final void deactivate()
	{
		actionControl.deactivate();
	}

	@Override
	public final String getId()
	{
		return this.id;
	}

	/**
	 * Diese Methode gibt die zu dieser Action gehörenden Fahrstraße zurück.
	 * @return Die Fahrstraße
	 * @see Route
	 */
	public final Route getRoute()
	{
		return actionControl.getRoute();
	}
}
