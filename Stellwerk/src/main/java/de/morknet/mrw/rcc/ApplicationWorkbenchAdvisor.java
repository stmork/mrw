/*
**
**	$Filename:	ApplicationWorkbenchAdvisor.java $
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse initialisiert das Verhalten dieser Applikation.
 * @author sm
 *
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor implements PerspectiveId
{
	private static final Log log = LogFactory.getLog(ApplicationWorkbenchAdvisor.class);

	@Override
	public String getInitialWindowPerspectiveId()
	{
		return PERSPECTIVE_ID;
	}

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor	(IWorkbenchWindowConfigurer configurer)
	{
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public void postStartup()
	{
		super.postStartup();
		log.info("=================================================================================");
		log.info(LogUtil.now());
		log.info(LogUtil.printf("Anlage %s ist betriebsbereit.", ModellFactory.getInstance().getName()));
		log.info("=================================================================================");
	}

	@Override
	public void eventLoopException(Throwable exception)
	{
		super.eventLoopException(exception);
		Controller.getController().setErrorMessage(exception.getMessage());
		log.error(exception.getMessage(), exception);
	}

	private static class SyncRemoveRoutes implements Runnable
	{
		public void run()
		{
			try
			{
				Controller.getController().removeAllRoutes();
			}
			catch (Exception e)
			{
				log.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public boolean preShutdown()
	{
		boolean shutdown = super.preShutdown();
		
		Display display = PlatformUI.getWorkbench().getDisplay();
		if (!display.isDisposed())
		{
			display.syncExec(new SyncRemoveRoutes());
		}
		return shutdown;
	}
	
	@Override
	public void postShutdown()
	{
		super.postShutdown();
		MrwException.protocol();
		log.info("=================================================================================");
		log.info(LogUtil.now());
		log.info(LogUtil.printf("Anlage %s ist heruntergefahren.", ModellFactory.getInstance().getName()));
		log.info("=================================================================================");
	}
}
