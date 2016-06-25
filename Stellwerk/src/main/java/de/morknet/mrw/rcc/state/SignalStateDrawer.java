/*
**
**	$Filename:	SignalStateDrawer.java $
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Transform;

import de.morknet.mrw.base.Ausfahrsignal;
import de.morknet.mrw.base.Blocksignal;
import de.morknet.mrw.base.Einfahrsignal;
import de.morknet.mrw.base.Formhauptsignal;
import de.morknet.mrw.base.Gleissperrsignal;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Vorsignal;
import de.morknet.mrw.rcc.Colors;
import de.morknet.mrw.rcc.Controller;
import de.morknet.mrw.rcc.Coordinates;

/**
 * Diese Klasse steuert das Zeichnen eines Signals. Dabei werden Formsignale als Lichtsignale
 * gezeichnet, da sich deren Funktionalität nicht voneinander unterscheidet. Es wird dabei auch die
 * jeweilige Fahrtrichtung beachtet, sodass das Signal je nach Fahrtrichtung oberhalb bzw. umgedreht
 * unterhalb des Gleises gezeichnet wird.
 * @author sm
 *
 */
abstract public class SignalStateDrawer extends StateDrawer implements Colors, Coordinates
{
	private   final static Log    log = LogFactory.getLog(SignalStateDrawer.class);
	
	/**
	 * Das zu zeichnende Signal.
	 */
	protected final        Signal signal;

	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse.
	 * @param signal Das zu zeichnende Signal
	 */
	SignalStateDrawer(final Signal signal)
	{
		this.signal = signal;
	}

	/**
	 * Diese Methode bereitet eine affine Transformation vor, mit der die Fahrtrichtung beim Zeichnen berücksichtigt wird.
	 * @param gc Der Grafikkontext, in den gezeicnet werden soll.
	 * @param s Das zu zeichnende Signal.
	 */
	private static void setTransform(final GC gc, final Signal s)
	{
		Transform f = new Transform(null);

		int x1 = s.getLogX() * SCALE_X;
		int y1 = s.getLogY() * SCALE_Y;

		if (s.isDirection(s.getSegment().getGroup().isInvertedDirection()))
		{
			f.rotate(180);
			f.translate(-x1, 1 - y1);
		}
		else
		{
			f.translate(x1, y1);
		}
		gc.setTransform(f);
	}

	/**
	 * Diese Methode zeichnet den Untergrund eines Signals ohne Signalbild.
	 * @param gc Der Grafikkontext, in den gezeichnet werden soll.
	 * @param s Das zu zeichnende Signal.
	 */
	private void drawBackground(final GC gc, final Signal s)
	{
		final int gx = getGX();

		gc.setForeground(BLACK);
		gc.setBackground(BLACK);
		if (s instanceof Gleissperrsignal)
		{
			gc.fillRectangle(gx, 4, 7, 9);
		}
		else if (s instanceof Vorsignal)
		{
			gc.fillRectangle(gx, 4, 8, 9);
		}
		else
		{
			gc.fillRectangle(gx, 4, 16, 9);
		}
		gc.drawLine(gx, 8, -SCALE_X >> 1, 8);
		gc.drawLine(-SCALE_X >> 1, 4, -SCALE_X >> 1, 12);

		if (!(s instanceof Vorsignal))
		{
			final String number = s.getNumber();

			gc.setBackground(BG_COLOR);
			gc.setForeground(Controller.getController().isOff(s) ? BG_COLOR : BLACK);
			gc.drawString(number, -(SCALE_Y >> 1) - number.length() * 8, 1, true);
		}
	}

	/**
	 * Diese Methode bereitet einen Grafikkontext zum Zeichnen von Verzweigungen vor.
	 * @param gc Der Grafikkontext.
	 */
	public static void prepare(GC gc)
	{
		gc.setLineWidth(1);
		gc.setLineStyle(SWT.LINE_SOLID);
	}
	
	/**
	 * Diese Methode zeichnet ein Signal in einen Grafikkontext.
	 * @param gc Der Grafikkontext.
	 * @param s Das zu zeichnende Signal.
	 */
	public static void drawState(GC gc, Signal s)
	{
		try
		{
			SignalStateDrawer drawer = getInstance(s);
			
			setTransform(gc, s);
			drawer.drawBackground(gc, s);
			drawer.prepare();
			drawer.draw(gc);
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	private static SignalStateDrawer getInstance(Signal s)
	{
		StateDrawer drawer = drawers.get(s);
		if (drawer == null)
		{
			drawer = createInstance(s);
			drawers.put(s, drawer);
		}
		return (SignalStateDrawer)drawer;
	}
	
	/**
	 * Diese Methode holt eine Instanz, die ein Signal zeichnen soll.
	 * @param v Das zu zeichnende Signal.
	 * @return Den Zeichner fÃÅr das Signal.
	 */
	private static SignalStateDrawer createInstance(Signal s)
	{
		if (s instanceof Gleissperrsignal)
		{
			return new GleissperrsignalDrawer(s);
		}
		else if (s instanceof Vorsignal)
		{
			return new VorsignalDrawer(s);
		}
		else if ((s instanceof Einfahrsignal) || (s instanceof Formhauptsignal))
		{
			return new EinfahrsignalDrawer(s);
		}
		else if (s instanceof Ausfahrsignal)
		{
			return new AusfahrsignalDrawer(s);
		}
		else if (s instanceof Blocksignal)
		{
			return new BlocksignalDrawer(s);
		}
		throw new IllegalArgumentException("Signaltyp unbekannt: " + s.getClass().getName());
	}
}
