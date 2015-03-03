/*
**
**	$Filename:	Colors.java $
**	$Revision$
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Dieses Interface stellt einige hÃâ¬ufig gebrauchte Farben als Konstanten bereit.
 * @author sm
 *
 */
public interface Colors
{
	/**
	 * WeiÃÂ.
	 */
	public final static Color WHITE        = new Color(null, 255, 255, 255); 

	/**
	 * Hellgrau.
	 */
	public final static Color LIGHT_GREY   = new Color(null, 192, 192, 192);

	/**
	 * Grau.
	 */
	public final static Color GREY         = new Color(null, 128, 128, 128);

	/**
	 * Dunkelgrau.
	 */
	public final static Color DARK_GREY    = new Color(null,  64,  64,  64);

	/**
	 * Schwarz.
	 */
	public final static Color BLACK        = new Color(null,   0,   0,   0); 

	/**
	 * Dunkelgelb.
	 */
	public final static Color DARK_YELLOW  = new Color(null, 128, 128,   0); 
	
	/**
	 * Gelb.
	 */
	public final static Color YELLOW       = new Color(null, 255, 255,   0);

	/**
	 * Hellgelb.
	 */
	public final static Color LIGHT_YELLOW = new Color(null, 255, 255, 180); 

	/**
	 * Dunkelrot.
	 */
	public final static Color DARK_RED     = new Color(null, 128,   0,   0);
	
	/**
	 * Rot.
	 */
	public final static Color RED          = new Color(null, 255,   0,   0);
	
	/**
	 * Hellrot.
	 */
	public final static Color LIGHT_RED    = new Color(null, 255, 180, 180); 

	/**
	 * DunkelgrÃÅn.
	 */
	public final static Color DARK_GREEN   = new Color(null,   0, 128,   0);
	
	/**
	 * GrÃÅn.
	 */
	public final static Color GREEN        = new Color(null,   0, 255,   0);
	
	/**
	 * HellgrÃÅn.
	 */
	public final static Color LIGHT_GREEN  = new Color(null, 180, 255, 180); 

	/**
	 * Dunkelblau.
	 */
	public final static Color DARK_BLUE    = new Color(null,   0,   0, 128);
	
	/**
	 * Blau.
	 */
	public final static Color BLUE         = new Color(null,   0,   0, 255);
	
	/**
	 * Hellblau.
	 */
	public final static Color LIGHT_BLUE   = new Color(null, 192, 192, 255); 

	/**
	 * Orange.
	 */
	public final static Color ORANGE       = new Color(null, 255, 160,   0); 

	/**
	 * Die systemweite Vordergrundfarbe.
	 */
	public final static Color FG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
	
	/**
	 * Die systemweite Hintergrundfarbe.
	 */
	public final static Color BG_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
	
	/**
	 * Farbdefinition fÃÅr <em>FahrstraÃÂe wird geschaltet</em>.
	 */
	public final static Color SEGMENT_SWITCHING = YELLOW;
	
	/**
	 * Farbdefinition fÃÅr <em>Gleisabschnitt ist verriegelt und Strom eingeschaltet</em>.
	 */
	public final static Color SEGMENT_ENABLED   = GREEN;

	/**
	 * Farbdefinition fÃÅr <em>Gleisabschnitt ist verriegelt und Strom ausgeschaltet</em>.
	 */
	public final static Color SEGMENT_DISABLED  = LIGHT_GREEN;

	/**
	 * Farbdefinition fÃÅr <em>Gleisabschnitt ist belegt</em>.
	 */
	public final static Color SEGMENT_OCCUPIED  = ORANGE;

	/**
	 * Farbdefinition fÃÅr <em>Gleisabschnitt ist frei</em>.
	 */
	public final static Color SEGMENT_FREE      = WHITE;
}
