/*
**
**	$Filename:	ApplicationActionBarAdvisor.java $
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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.gui.info.TourInfo;
import de.morknet.mrw.rcc.actions.ActionInitializer;
import de.morknet.mrw.rcc.actions.BeerModeAction;
import de.morknet.mrw.rcc.actions.ClearAction;
import de.morknet.mrw.rcc.actions.ErrorProtocolAction;
import de.morknet.mrw.rcc.actions.InvertSwitchesAction;
import de.morknet.mrw.rcc.actions.MaintainanceAction;
import de.morknet.mrw.rcc.actions.MrwAction;
import de.morknet.mrw.rcc.actions.PingAction;
import de.morknet.mrw.rcc.actions.QueryMicroControllerStateAction;
import de.morknet.mrw.rcc.actions.QueryModelStateAction;
import de.morknet.mrw.rcc.actions.ResetMicroControllerAction;
import de.morknet.mrw.rcc.actions.ResetModelRailWayAction;
import de.morknet.mrw.rcc.actions.SeparatorAction;
import de.morknet.mrw.rcc.actions.TestAction;
import de.morknet.mrw.rcc.actions.TourModeAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private final static Log                    log         = LogFactory.getLog(ApplicationActionBarAdvisor.class);

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private Action pingMcuAction;
	private Action clrModelAction;
	private Action testModelAction;
	private Action queryMcuStateAction;
	private Action queryModelStateAction;
	private Action resetMcuAction;
	private Action resetModelAction;
	private Action invertSwitchesAction;
	private Action maintainanceAction;
	private Action errorProtocolAction;

	/**
	 * Konstruktur fÃÅr diese Klasse.
	 * @param configurer Die Konfiguration.
	 */
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer)
	{
		super(configurer);
	}

	protected void makeActions(final IWorkbenchWindow window)
	{
		// Creates the actions and registers them.
		// Registering is needed to ensure that key bindings work.
		// The corresponding commands keybindings are defined in the plugin.xml
		// file.
		// Registering also provides automatic disposal of the actions when
		// the window is closed.

		exitAction            = ActionFactory.QUIT.create(window);
		aboutAction           = ActionFactory.ABOUT.create(window);
		pingMcuAction         = new PingAction();
		clrModelAction        = new ClearAction();
		testModelAction       = new TestAction();
		queryMcuStateAction   = new QueryMicroControllerStateAction();
		queryModelStateAction = new QueryModelStateAction();
		resetMcuAction        = new ResetMicroControllerAction();
		resetModelAction      = new ResetModelRailWayAction();
		errorProtocolAction   = new ErrorProtocolAction();
		invertSwitchesAction  = new InvertSwitchesAction();
		maintainanceAction    = new MaintainanceAction();

		BeerModeAction.initBeerModeActions();

		register(exitAction);
		register(aboutAction);

		List<TourInfo> infos = ModellFactory.getInstance().getTourInfos();

		if (infos.size() > 0)
		{
			ActionInitializer.addAction(new SeparatorAction());
		}
		
		for(TourInfo info : infos)
		{
			try
			{
				log.info("Registriere Tour: " + info.getName());
				TourModeAction action = new TourModeAction(info);
				ActionInitializer.addAction(action);
				register(action);
			}
			catch(RuntimeException re)
			{
				log.error(re.getMessage(), re);
			}
		}
	}

	protected void fillMenuBar(IMenuManager menuBar)
	{
		MenuManager fileMenu = new MenuManager("&Datei",
				IWorkbenchActionConstants.M_FILE);
		menuBar.add(fileMenu);
		fileMenu.add(exitAction);

		MenuManager automaticMenu = new MenuManager("&Automatik", "automatic");
		menuBar.add(automaticMenu);
		for (MrwAction action : ActionInitializer.getActions())
		{
			if (action instanceof SeparatorAction)
			{
				automaticMenu.add(new Separator());
			}
			else
			{
				automaticMenu.add(action);
			}
		}

		MenuManager diagnoseMenu = new MenuManager("&Diagnose", "diagnose");
		menuBar.add(diagnoseMenu);
		diagnoseMenu.add(pingMcuAction);
		diagnoseMenu.add(resetMcuAction);
		diagnoseMenu.add(queryMcuStateAction);
		diagnoseMenu.add(new Separator());
		diagnoseMenu.add(resetModelAction);
		diagnoseMenu.add(queryModelStateAction);
		diagnoseMenu.add(clrModelAction);
		diagnoseMenu.add(testModelAction);
		diagnoseMenu.add(invertSwitchesAction);
		diagnoseMenu.add(maintainanceAction);
		diagnoseMenu.add(new Separator());
		diagnoseMenu.add(errorProtocolAction);

		MenuManager helpMenu = new MenuManager("&Hilfe", "command");
		menuBar.add(helpMenu);
		helpMenu.add(aboutAction);
	}
}
