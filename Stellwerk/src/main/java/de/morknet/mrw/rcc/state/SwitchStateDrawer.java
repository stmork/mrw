/*
**
**	$Filename:	SwitchStateDrawer.java $
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

package de.morknet.mrw.rcc.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.graphics.GC;

import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.rcc.Colors;
import de.morknet.mrw.rcc.Controller;
import de.morknet.mrw.rcc.Coordinates;

/**
 * Diese Klasse zeichnet den Lagezustand einer Verzweigung.
 * @author sm
 *
 */
abstract public class SwitchStateDrawer extends StateDrawer implements Coordinates, Colors
{
	private final static Log log  = LogFactory.getLog(SwitchStateDrawer.class);
	private final static int PART = 2;

	/**
	 * Diese Methode blendet den Schaltzustand einer Verzweigung aus.
	 * @param gc Der Grafikkontext, in den gezeichnet werden soll.
	 * @param v Die zu zeichnende Verzweigung.
	 * @param dir Das benachbarte Gleisteil.
	 * @param xOffset Die Richtung, in die gezeichnet werden soll.
	 */
	static void drawSwitchDir(GC gc, Verzweigung v, Gleisteil dir, int xOffset)
	{
		int x1 = v.getLogX() * SCALE_X;
		int y1 = v.getLogY() * SCALE_Y;

		if (!v.aIsHigh())
		{
			xOffset = -xOffset;
		}

		if (v.getGruppe().isInvertedDirection())
		{
			xOffset = -xOffset;
		}

		if (dir != null)
		{
			int bx = x1 - xOffset * SCALE_X / PART;
			int by = y1;
			if (dir.getLogY() > v.getLogY())
			{
				by += SCALE_Y / PART;
			}
			else if(dir.getLogY() < v.getLogY())
			{
				by -= SCALE_Y / PART;
			}
			gc.setForeground(BLACK);
			gc.drawLine(x1, y1, bx, by);
		}
	}

	/**
	 * Diese Methode bereitet einen Grafikkontext zum Zeichnen von Verzweigungen vor.
	 * @param gc Der Grafikkontext.
	 */
	public static void prepare(GC gc)
	{
		gc.setLineWidth(MRW_LINE_WIDTH + 1);
	}

	/**
	 * Diese Methode berechnet den Namen einer Weiche. Dabei wird ein eventueller Prefix entfernt.
	 * @param t Das betreffende Gleisteil.
	 * @return Der Prefix-freie Name.
	 */
	private static String getSwitchName(Gleisteil t)
	{
		String name = t.getName();
		int index = name.indexOf(" ");
		
		if (index > 0)
		{
			name = name.substring(index);
		}
		return name.trim();
	}
	
	/**
	 * Diese Methode zeichnet eine Verzweigung in einen Grafikkontext.
	 * @param gc Der Grafikkontext.
	 * @param v Die zu zeichnende Verzweigung.
	 */
	public static void drawState(GC gc, Verzweigung v)
	{
		try
		{
			int x1 = v.getLogX() * SCALE_X;
			int y1 = v.getLogY() * SCALE_Y;

			// Schreibe Weichennummer, evtl. blinkend (Umlauf signalisierend)
			String name = getSwitchName(v);
			gc.setForeground(Controller.getController().isOff(v) ? BG_COLOR : BLACK);
			gc.drawString(name, x1 - name.length() * 4, y1 + 10);

			SwitchStateDrawer drawer = getInstance(v);
			
			drawer.prepare();
			drawer.draw(gc);
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	@Override
	int getGX()
	{
		return 0;
	}

	@Override
	void prepare()
	{
	}

	private static SwitchStateDrawer getInstance(Verzweigung v)
	{
		StateDrawer drawer = drawers.get(v);
		if (drawer == null)
		{
			drawer = createInstance(v);
			drawers.put(v, drawer);
		}
		return (SwitchStateDrawer)drawer;
	}

	/**
	 * Diese Methode holt eine Instanz, die eine Verzweigung zeichnen soll.
	 * @param v Die zu zeichnende Verzweigung.
	 * @return Den Zeichner fÃÅr die Verzweigung.
	 */
	private static SwitchStateDrawer createInstance(Verzweigung v)
	{
		if (v instanceof Weiche)
		{
			return new SwitchDrawer(v);
		}
		else if (v instanceof DKW)
		{
			return new DKWDrawer(v);
		}
		throw new IllegalArgumentException("Verzweigungstyp unbekannt: " + v.getClass().getName());
	}
}
