/*
**
**	$Filename:	ButtonView.java $
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import de.morknet.mrw.Route;
import de.morknet.mrw.util.NameUtil;

/**
 * Diese Klasse stellt eine View mit die Fahrtkontrollen dar. Es handelt sich dabei um fÃÅnf Buttons:
 * <ul>
 * <li>Fahrt links
 * <li>Rangieren links
 * <li>Fahrt erweitern
 * <li>Rangieren rechts
 * <li>Fahrt rechts.
 * </ul>
 * @author sm
 *
 */
public class ButtonView extends RccViewPart implements ButtonViewId
{
	private final static Log log = LogFactory.getLog(ButtonView.class);
	private final Controller controller = Controller.getController();
	private Button fahrt_links;
	private Button rangieren_links;
	private Button erweitern;
	private Button rangieren_rechts;
	private Button fahrt_rechts;

	private class DoAction implements SelectionListener
	{
		private final boolean shunting;
		private final boolean inDirection;

		public DoAction(boolean rangieren, boolean inDirection)
		{
			this.shunting    = rangieren;
			this.inDirection = inDirection;
		}

		public void widgetDefaultSelected(SelectionEvent event)
		{
			log.info(NameUtil.logRouteType(shunting, inDirection));
			try
			{
				setMessage("FahrstraÃÂe wird geschaltet...");
				controller.computeRoute(shunting, inDirection);
			}
			catch (Exception e)
			{
				setErrorMessage(e.getMessage()); 
				log.error(e.getMessage(), e);
			}
		}

		public void widgetSelected(SelectionEvent event)
		{
			widgetDefaultSelected(event);
		}
	}

	private class ExtendAction implements SelectionListener
	{
		public void widgetDefaultSelected(SelectionEvent event)
		{
			Route route = Controller.getController().getSelectedRoute();
			if (route != null)
			{
				log.info(NameUtil.logRouteType(route));
				try
				{
					setMessage("FahrstraÃÂe wird verlÃâ¬ngert...");
					controller.extendRoute(route);
				}
				catch (Exception e)
				{
					setErrorMessage(e.getMessage()); 
					log.error(e.getMessage(), e);
				}
			}
		}

		public void widgetSelected(SelectionEvent se)
		{
			widgetDefaultSelected(se);
		}
		
	}

	/**
	 * Diese Methode erzeugt die Buttons.
	 * @param parent Der {@link Composite}, in den die {@link Button}s gehÃâ¬ngt werden.
	 */
	public void createPartControl(Composite parent)
	{

		fahrt_links = new Button(parent, SWT.PUSH);
		fahrt_links.setText("Fahrt &links");
		fahrt_links.addSelectionListener(new DoAction(false, false));

		rangieren_links = new Button(parent, SWT.PUSH);
		rangieren_links.setText("Rangieren links");
		rangieren_links.addSelectionListener(new DoAction(true, false));

		erweitern = new Button(parent, SWT.PUSH);
		erweitern.setText("&Erweitern");
		erweitern.addSelectionListener(new ExtendAction());
		
		rangieren_rechts = new Button(parent, SWT.PUSH);
		rangieren_rechts.setText("Rangieren rechts");
		rangieren_rechts.addSelectionListener(new DoAction(true, true));
		
		fahrt_rechts = new Button(parent, SWT.PUSH);
		fahrt_rechts.setText("Fahrt &rechts");
		fahrt_rechts.addSelectionListener(new DoAction(false, true));
		
		controller.register(this);
	}
	
	private class ButtonEnabler extends Thread
	{
		private final boolean enabled;
		private final boolean rangieren;
		
		private ButtonEnabler(boolean enabled, boolean rangieren)
		{
			this.enabled   = enabled;
			this.rangieren = rangieren;
		}

		public void run()
		{
			enable(fahrt_links,      enabled);
			enable(rangieren_links,  enabled && rangieren);
			enable(erweitern,        (controller.getSelectedRoute() != null) && (controller.getSegmentSelection().size() > 0));
			enable(rangieren_rechts, enabled && rangieren);
			enable(fahrt_rechts,     enabled);
		}
		
		private void enable(Control control, boolean enable)
		{
			if (!control.isDisposed())
			{
				control.setEnabled(enable);
			}
		}
	}

	/**
	 * Diese Methode aktiviert je nach Auswahlzustand die Buttons in diesem View. Die Aktualisierung erfolgt asynchron.
	 * @param enabled Aktivierung.
	 * @param rangieren Rangierflag.
	 */
	public void enable(boolean enabled, boolean rangieren)
	{
		Display display = PlatformUI.getWorkbench().getDisplay();
		if (!display.isDisposed())
		{
			display.asyncExec(new ButtonEnabler(enabled, rangieren));
		}
	}

	@Override
	public String getId()
	{
		return ID;
	}
}
