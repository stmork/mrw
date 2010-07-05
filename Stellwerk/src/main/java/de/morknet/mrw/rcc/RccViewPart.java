/*
**
**	$Filename:	RccViewPart.java $
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

package de.morknet.mrw.rcc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

/**
 * Diese Klasse steuert Status- und Fehlermeldungen aller Mrw-GUI-{@link ViewPart}s.
 * @author sm
 *
 */
abstract public class RccViewPart extends ViewPart
{
	protected final Log log = LogFactory.getLog(getId());

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
	}
	
	/**
	 * Diese Methode setzt eine Statusmeldung in die Statusleiste der GUI.
	 * @param msg Die Statusmeldung.
	 */
	public void setMessage(String msg)
	{
		log.info(msg);
		IActionBars actionBars = getViewSite().getActionBars();

		actionBars.getStatusLineManager().setMessage(msg);
		actionBars.getStatusLineManager().setErrorMessage(null);
		actionBars.updateActionBars();
	}
	
	/**
	 * Diese Methode setzt eine Fehlermeldung in die Statusleiste der GUI.
	 * @param msg Die Fehlermeldung.
	 */
	public void setErrorMessage(String msg)
	{
		log.error(msg);
		IActionBars actionBars = getViewSite().getActionBars();

		actionBars.getStatusLineManager().setErrorMessage(msg);
		actionBars.updateActionBars();
	}
	
	/**
	 * Diese Methode liefert die View-ID, unter der dieser {@link ViewPart} im Eclipse registriert wird.
	 * @return Die View-ID.
	 */
	public abstract String getId(); 
}
