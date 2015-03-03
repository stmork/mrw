/*
**
**	$Filename:	Controller.java $
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

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.rcc.actions.ActionInitializer;
import de.morknet.mrw.rcc.actions.MrwAction;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.MrwProperties;

/**
 * Diese Klasse stellt den zentralen Controller gemÃâ¬ÃÂ dem MVC-Pattern dar. Dieser Controller ist von {@link MrwController}
 * abgeleitet, welcher unabhÃâ¬ngig von der BenutzeroberflÃâ¬che die FunktionalitÃâ¬t der Eisenbahnsteuerung zur VerfÃÅgung stellt.
 * @author sm
 *
 */
public class Controller extends MrwController
{
	private final static Log             log      = LogFactory.getLog(Controller.class);
	private final        List<GroupView> groups   = new LinkedList<GroupView>();
	private final static Controller      ctrl     = new Controller();
	private final static Timer           timer    = new Timer("Weichenschaltzustand");
	private              ButtonView      buttons;
	private              InfoView        info;
	private final        BlinkTask       task;

	private Controller()
	{
		log.info("=================================================================================");
		log.info(LogUtil.now());
		log.info(LogUtil.printf("Stellwerk fÃÅr %s auf Rechner %s.", model.getName(), MrwProperties.getSimpleHostName()));
		log.info("$Revision$");
		log.info("Copyright (C) committers of the modelrailway project 2007-2010");
		log.info("=================================================================================");
		this.task = new BlinkTask(this);
	}

	/**
	 * Diese Methode initialisiert diesen Controller. 
	 */
	@Override
	public void prepare() throws Exception
	{
		super.prepare();
		timer.schedule(task, 0, 250);
	}

	/**
	 * Diese Methode gibt eine Instanz des Controllers zurÃÅck. Der Controller ist als Singleton implementiert.
	 * @return Der steuernde Controller.
	 */
	public static Controller getController()
	{
		return ctrl;
	}

	/**
	 * ÃÂber diese Methode wird diesem Controller der {@link ButtonView} mitgeteilt.
	 * @param buttons Der {@link ButtonView} mit den Fahrtkontrollen. 
	 */
	public void register(ButtonView buttons)
	{
		this.buttons = buttons;
		updateButtons();
	}

	/**
	 * ÃÂber diese Methode wird der {@link InfoView} registriert. In diesem werden die FahrstraÃÂen und die ausgewÃâ¬hlten
	 * Gleisabschnitte angezeigt.
	 * @param view Der {@link InfoView} mit FahrstraÃÂen und ausgewÃâ¬hlten Gleisabschnitten.
	 */
	public void register(InfoView view)
	{
		this.info = view;
	}

	/**
	 * ÃÂber diese Methode wird eine Tab mit der Darstellung einer Betriebsgruppe registriert.
	 * @param grouping Die zu registrierende Darstellung einer Betriebsgruppe.
	 * @see GroupView
	 */
	public void register(GroupView grouping)
	{
		groups.add(grouping);
	}
	
	/**
	 * Diese Methode veranlasst das Aktualisieren der InfoView.
	 */
	public void updateInfoView()
	{
		if (info != null)
		{
			info.update();
		}
	}

	/**
	 * Diese Methode veranlasst das Aktualisieren der Betriebsgruppen.
	 */
	public void updateGroupViews()
	{
		log.debug(">updateGroupViews()");
		for (GroupView view : groups)
		{
			view.invalidate();
		}
		log.debug("<updateGroupViews()");
	}

	/**
	 * Diese Methode veranlasst das Aktualisieren der {@link ButtonView}. Die Buttons werden je nach Auswahlzustand
	 * der FahrtstraÃÂen im InfoView bzw. der Auswahl der Gleisabschnitte aktiviert oder deaktiviert.
	 */
	public void updateButtons()
	{
		log.debug(">updateButtons()");
		boolean enabled = getSegmentSelection().size() >= 2;
		boolean rangieren = true;
		Gruppe gruppe = null;

		for (Abschnitt a : getSegmentSelection())
		{
			if (gruppe != null)
			{
				if (gruppe != a.getGroup())
				{
					rangieren = false;
				}
			}
			else
			{
				gruppe = a.getGroup();
			}
		}
		buttons.enable(enabled, rangieren);
		log.debug("<updateButtons()");
	}

	@Override
	public void updateActions()
	{
		log.debug(">updateActions()");
		for(MrwAction action : ActionInitializer.getActions())
		{
			action.update();
		}
		log.debug("<updateActions()");
	}

	@Override
	public final void deactivateAction(final Route route)
	{
		log.debug(LogUtil.printf(">deactivateAction(%s)", route));
		for (MrwAction action : ActionInitializer.getActions())
		{
			if (action.getRoute() == route)
			{
				action.deactivate();
			}
		}
		log.debug("<deactivateActions()");
	}

	@Override
	protected final void deactivateActions()
	{
		log.debug(">deactivateActions()");
		for (MrwAction action : ActionInitializer.getActions())
		{
			if (action.isActive())
			{
				action.deactivate();
			}
		}
		log.debug("<deactivateActions()");
	}

	/**
	 * Diese Methode gibt die in der FahrstraÃÂenansicht ausgewÃâ¬hlte FahrstraÃÂe zurÃÅck.
	 * @return Die ausgewÃâ¬hlte FahrstraÃÂe.
	 */
	@Override
	public Route getSelectedRoute()
	{
		return info.getSelectedRoute();
	}

	/**
	 * Diese Methode wÃâ¬hlt in der Info-Ansicht eine FahrstraÃÂe aus.
	 * @param route Die auszuwÃâ¬hlende FahrstraÃÂe.
	 */
	@Override
	public void selectRoute(Route route)
	{
		info.select(route);
	}

	/**
	 * Diese Methode setzt eine Statusmeldung in die Statuszeile des Stellwerksfensters.
	 * @param message Die Statusmeldung.
	 */
	@Override
	public void setMessage(String message)
	{
		final String msg = message;

		Display display = PlatformUI.getWorkbench().getDisplay();
		if (!display.isDisposed())
		{
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					info.setMessage(msg);
					buttons.setMessage(msg);
				}
			});
		}
	}

	/**
	 * Diese Methode setzt eine Fehlermeldung in die Statuszeile des Stellwerksfensters.
	 * @param message Die Fehlermeldung.
	 */
	@Override
	public void setErrorMessage(String message)
	{
		final String msg = message;

		Display display = PlatformUI.getWorkbench().getDisplay();
		if (!display.isDisposed())
		{
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					info.setErrorMessage(msg);
					buttons.setErrorMessage(msg);
				}
			});
		}
	}
	
	/**
	 * Diese Methode aktualisiert alle Bestandteile in der GUI.
	 */
	@Override
	public void updateCompleteUI()
	{
		updateButtons();
		updateGroupViews();
		updateInfoView();
		updateActions();
	}

	/**
	 * Diese Methode aktualisiert den Gleisplan in der GUI.
	 */
	@Override
	public void updateTrackPlan()
	{
		updateGroupViews();
		updateInfoView();
	}
	
	@Override
	public void updateClearedSelection()
	{
		GroupView.clearSelection();
	}

	/**
	 * Diese Methode gibt den Blinkstatus des angegebenen GerÃâ¬ts zurÃÅck.
	 * @param device Das zu prÃÅfende GerÃâ¬t.
	 * @return Ob der Blinkstatus an oder aus ist.
	 */
	public boolean isOff(DeviceUnit device)
	{
		return device.isProcessing() && task.getToggle();
	}
}
