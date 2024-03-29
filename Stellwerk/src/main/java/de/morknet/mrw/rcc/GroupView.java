/*
**
**	$Filename:	GroupView.java $
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.Gleis;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.rcc.state.SignalStateDrawer;
import de.morknet.mrw.rcc.state.SwitchStateDrawer;

/**
 * Diese Klasse stellt die Betriebsgruppe als Gleisbildstellwerk dar und
 * verwaltet die Benutzerinteraktion.
 * 
 * @author smork
 *
 */
abstract public class GroupView extends RccViewPart implements Colors, Coordinates, PaintListener
{
	/**
	 * Das Log.
	 */
	protected final static Log log = LogFactory.getLog(GroupView.class);
	
	/**
	 * Die hier dargestellte Betriebsgruppe.
	 */
	protected final Gruppe gruppe;

	/**
	 * Das übergeordnete GUI-Element.
	 */
	protected Composite parent;

	/**
	 * Der GUI-Controller.
	 */
	protected Controller controller = Controller.getController();
	
	/**
	 * Menge aller Abschnittsmarkierungen.
	 */
	private final static Set<Button> buttons = new HashSet<Button>();

	/**
	 * Liste aller Hauptgleise.
	 */
	private final List<Gleisteil> hauptgleise = new ArrayList<Gleisteil>();
	
	/**
	 * Vorberechneter Wert abhängig von der Zählrichtung.
	 */
	private        final int VIEW_DIRECTION;

	protected GroupView(final String name)
	{
		this.gruppe = ModellFactory.getInstance().findGroup(name);
		VIEW_DIRECTION = gruppe.isInvertedDirection() ? -1 : 1;
	}

	private final static boolean DEBUG = false;

	/**
	 * Diese Methode zeichnet ein einzelnes Gleis. Die Koordinaten müssen nicht
	 * sortiert sein.
	 * @param gc Der Grafikkontext, in den gezeichnet werden soll
	 * @param x1 Startecke x
	 * @param y1 Startecke y
	 * @param x2 Endecke x
	 * @param y2 Endecke y
	 * @param color Die zu benutzende Farbe
	 */
	private static void drawRail(
			final GC gc,
			      int x1,
			      int y1,
			      int x2,
			      int y2,
			final Boolean reverse,
			final Color color)
	{
		if (x1 > x2)
		{
			// swap x1/x2
			int aux = x1;
			x1 = x2;
			x2 = aux;

			// Swap y1/y2
			aux = y1;
			y1 = y2;
			y2 = aux;
		}
		int xd = x2 - x1;
		int yd = y2 - y1;
		

		if ((x2 - x1) == Math.abs(yd))
		{
			int path[] = new int[4];

			path[0] = x1;
			path[1] = y1;
			path[2] = x2;
			path[3] = y2;
			drawRailPath(gc, color, path);
		}
		else
		{
			int path[] = new int[6];
			
			path[0] = x1;
			path[1] = y1;
			path[4] = x2;
			path[5] = y2;
			
			if (yd > 0)
			{
				if (xd > Math.abs(yd))
				{
					if (reverse)
					{
						// __
						//   \
						path[2] = x2 - yd;
						path[3] = y1;
					}
					else
					{
						// \__
						path[2] = x1 + yd;
						path[3] = y2;
					}
				}
				else
				{
					// \
					//  \
					//  |
					path[2] = x2;
					path[3] = y1 + xd;
				}
			}
			else
			{
				if (xd > Math.abs(yd))
				{
					if (reverse)
					{
						// __/
						path[2] = x2 + yd;
						path[3] = y1;
					}
					else
					{
						//  __
						// /
						path[2] = x1 - yd;
						path[3] = y2;
					}
				}
				else
				{
					//  |
					//  /
					// /
					path[2] = x2;
					path[3] = y2 + xd;
				}
			}

			drawRailPath(gc, color, path);
		}
	}

	/**
	 * Diese Methode berechnet für die Farbgebung eines Gleisteils den Abstand
	 * vom Rasterrand. Pro Rasterelement
	 * wird nur ein Balken gemalt. 
	 * @param diff Das Steigungsdreieck.
	 * @return Je nach Steigung negativer BASE_OFFSET, Null oder positiver
	 * BASE_OFFSET
	 */
	private static int getOffset(final int diff)
	{
		return diff == 0 ? 0 : (diff < 0 ? -BASE_OFFSET : BASE_OFFSET);
	}

	/**
	 * Diese Methode zeichnet ein gewinkeltes Gleis anhand vorgegebener
	 * Koordinaten inklusive der Farbgebung für
	 * die Gleisnutzung.
	 * @param gc Der Grafikkontext, in den gezeichnet wird.
	 * @param color Die Farbgebung für die Gleisteile.
	 * @param path Der Koordinatenpfad für das Gleis.
	 */
	private static void drawRailPath(final GC gc, final Color color, final int[] path)
	{
		gc.setLineWidth(MRW_LINE_WIDTH + 2);
		gc.setLineStyle(SWT.LINE_SOLID);
		gc.setForeground(BLACK);
		gc.drawPolyline(path);

		gc.setLineWidth(MRW_LINE_WIDTH);
		gc.setForeground(color);

		int x1 = path[0];
		int y1 = path[1];
		for (int i = 2; i < path.length;i+=2)
		{
			int x2 = path[i];
			int y2 = path[i+1];
			
			int xDiff   = x2 - x1;
			int yDiff   = y2 - y1;
			int xCount  = xDiff / SCALE_X;
			int yCount  = yDiff / SCALE_Y;
			int count   = xCount < yCount ? yCount : xCount;
			int xOffset = getOffset(xDiff);
			int yOffset = getOffset(yDiff);
			
			int xs = x1;
			int ys = y1;
			for (int k = 1;k <= count;k++)
			{
				int xe = x1 + k * xDiff / count;
				int ye = y1 + k * yDiff / count;
				gc.drawLine(
						xs + xOffset, ys + yOffset,
						xe - xOffset, ye - yOffset);
				xs = xe;
				ys = ye;
			}
			x1 = x2;
			y1 = y2;
		}
	}

	/**
	 * Diese Methode zeichnet den Gleisplan.
	 * @param gc Der Grafikkontext, in den gezeichnet werden soll.
	 */
	private void draw(GC gc)
	{
		if (DEBUG)
		{
			log.debug(">draw()    " + gruppe.getName());
		}
		gc.setBackground(BG_COLOR);
	
		if (DEBUG)
		{
//			drawRaster(gc);
		}
		for (Gleisteil gt : gruppe.getTrackElements())
		{
			int             x1        = gt.getLogX() * SCALE_X;
			int             y1        = gt.getLogY() * SCALE_Y;
			Abschnitt       abschnitt = gt.getSegment();
			List<Gleisteil> list      = gt.getRouting(true);
			Color           color;
			
			// Test auf Abstellgleis
			if (list.isEmpty())
			{
				// Gleisteil ist Abstellgleis und damit hier zu Ende.
				color = getSegmentState(abschnitt, gt, null);

				drawRail(gc,
						x1, y1,
						x1 + (OVERRUN_X * SCALE_X * VIEW_DIRECTION), y1, false, color);
			}
			
			// Jeder Nachbar wird "durchgeackert".
			for (Gleisteil next : list)
			{
				int     nx1,ny1;
				Gruppe  nachbar = next.getSegment().getGroup();

				// PrÃÅfung, ob Gleisteil in andere Betriebsgruppe reicht.
				if (nachbar == gruppe)
				{
					// Nein, Gleisteil bleibt in Betriebsgruppe
					nx1    = next.getLogX() * SCALE_X;
					ny1    = next.getLogY() * SCALE_Y;
				}
				else
				{
					// Ja, Nachbar ist in anderer Betriebsgruppe
					nx1    = x1 + (OVERRUN_X * SCALE_X * VIEW_DIRECTION);
					ny1    = y1;
				}
				
				color = getSegmentState(abschnitt, gt, next);
				drawRail(gc, x1, y1, nx1, ny1, (gt instanceof Gleis) && (next instanceof Verzweigung), color);
			}
		}

		// Hier werden alle Verzweigungen gezeichnet.
		SwitchStateDrawer.prepare(gc);
		for (Gleisteil gt : gruppe.getTrackElements())
		{
			if (gt instanceof Verzweigung)
			{
				SwitchStateDrawer.drawState(gc, (Verzweigung)gt);
			}
		}
		
		// Hier werden alle Signale gezeichnet.
		SignalStateDrawer.prepare(gc);
		for (Signal s : gruppe.getAllSignals())
		{
			SignalStateDrawer.drawState(gc, s);
		}
		if (DEBUG)
		{
			log.debug("<draw()    " + gruppe.getName());
		}
	}

	/**
	 * Diese Methode bestimmt die Farbe für das zu zeichnende Gleisteil.
	 * @param route Die Fahrstraße des aktuellen Gleisteils. Kann null sein.
	 * @param segment Der Gleisabschnitt zu dem das Gleisteil gehört.
	 * @param gt Das Gleisteil selbst.
	 * @param next Ein Gleisteil, zu dem hingezeichnet werden muss.
	 * @return Die berechnete Farbe.
	 */
	private Color getSegmentState(
			final Abschnitt segment, final Gleisteil gt, final Gleisteil next)
	{
		Color color;
		
		synchronized(gt)
		{
			Route route = gt.getRoute();

			// Test, ob Gleisabschnitt belegt ist.
			if (segment.isOccupied())
			{
				// Gleisabschnitt belegt, daher immer orange.
				color = SEGMENT_OCCUPIED;
			}
			else if (route != null)
			{
				// Gleisteil gehört zu einer Fahrstraße.
				if (route.isPart(gt, next))
				{
					// Nächstes Gleisteil gehört auch zur Fahrstraße
					color = gt.isLocked() ?
							(segment.isEnabled() ?
									SEGMENT_ENABLED :
									SEGMENT_DISABLED) :
							SEGMENT_SWITCHING;
				}
				else
				{
					// Gleisteil ist nicht beteiligt.
					color = SEGMENT_FREE;
				}
			}
			else
			{
				// Gleisteil ist nirgends beteiligt.
				color = SEGMENT_FREE;
			}
		}
		return color;
	}

	public void paintControl(PaintEvent e)
	{
		draw(e.gc);
	}

	/**
	 * Wechselt den Zustand einer Abschnittsauswahl.
	 * @param e Das Ereignis für die Auswahl.
	 */
	private void addSelection(SelectionEvent e)
	{
		if (e.getSource() instanceof Button)
		{
			Button btn = (Button)e.getSource();
			String txt = btn.getText();
			Abschnitt abschnitt = ModellFactory.getInstance().findSegment(txt);

			if (btn.getSelection())
			{
				controller.addSegmentSelection(abschnitt);
				log.info("Auswahl: +" + abschnitt.getNumber());
				setMessage("Abschnitt " + abschnitt.getNumber() + " ausgewählt.");
			}
			else
			{
				controller.removeSegmentSelection(abschnitt);
				log.info("Auswahl: -" + abschnitt.getNumber());
				setMessage("Abschnitt " + abschnitt.getNumber() + " abgewählt.");
			}
		}
	}

	/**
	 * Diese innere Klasse empfängt das Ereignis, eine Abschnitts-Checkbox
	 * ausgewählt zu haben.
	 * @author smork
	 *
	 */
	private class SegmentSelector implements SelectionListener
	{
		public void widgetDefaultSelected(SelectionEvent e) {
			addSelection(e);
		}

		public void widgetSelected(SelectionEvent e) {
			addSelection(e);
		}
	}


	/**
	 * Diese Methode legt alle Widgets in dieser Anzeige an. Für jeden
	 * Abschnitt mit einem Gleis wird dafür eine Auswahl-Checkbox angelegt.
	 */
	public void createPartControl(Composite grandparent) {
		FormLayout layout = new FormLayout();
		Composite parent = new Composite(grandparent,SWT.DOUBLE_BUFFERED);
		parent.setLayout(layout);
		parent.addPaintListener(this);
		parent.setBackground(BG_COLOR);
		this.parent = parent;

		for(Abschnitt a : gruppe.getSegments())
		{
			Gleisteil g = a.getStartTrack();
			if (g != null)
			{
				FormData data = new FormData();

				data.left = new FormAttachment(0, g.getLogX() * SCALE_X -  8);
				data.top  = new FormAttachment(0, g.getLogY() * SCALE_Y - 19);
				Button button = new Button(parent, SWT.CHECK);
				button.setLayoutData(data);
				button.setText(a.getNumber());
				button.addSelectionListener(new SegmentSelector());
				buttons.add(button);
			}
		}
		controller.register(this);

		/*
		if (0 != 0)
		{
			createLayout(true);
			createLayout(false);
		}
		*/
	}

	/**
	 * Löscht die Auswahl aller Abschnitts-Checkboxen.
	 */
	public static void clearSelection()
	{
		for (Button b : buttons)
		{
			b.setSelection(false);
		}
	}
	
	/**
	 * Diese Methode erzwingt ein Neuzeichnen dieser Anzeige.
	 */
	public void invalidate()
	{
		Display display = PlatformUI.getWorkbench().getDisplay();
		if (!display.isDisposed())
		{
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					if (!parent.isDisposed())
					{
						parent.redraw();
					}
				}
			});
		}
	}

	/**
	 * Diese Methode markiert Hauptgleise.
	 * @param gleisname Der Name des Hauptgleises.
	 */
	protected void addHauptgleis(String gleisname)
	{
		Gleisteil gt = ModellFactory.getInstance().find(gleisname);
		if (gt != null)
		{
			hauptgleise.add(gt);
		}
	}
}
