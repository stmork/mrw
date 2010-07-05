/*
**
**	$Filename:	Application.java $
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
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * Diese Klasse steuert das Verhalten einer Eclipse-Applikation. Hier wird der zentrale {@link Controller}
 * instanziiert, initialisiert, die Applikation gestartet und vor dem Beenden wird der {@link Controller}
 * kontrolliert deinitialisiert. 
 */
public class Application implements IApplication
{
	private static final Log log = LogFactory.getLog(Application.class);

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception
	{
		Display display = PlatformUI.createDisplay();
		try
		{
			final Controller ctrl = Controller.getController();

			// Do the job ;-)
			ctrl.prepare();
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			ctrl.close();

			if (returnCode == PlatformUI.RETURN_RESTART)
			{
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		finally
		{
			display.dispose();
		}
	}

	private static class WorkbenchDisposer implements Runnable
	{
		private final IWorkbench workbench = PlatformUI.getWorkbench();
		private final Display    display   = workbench.getDisplay();

		public void run()
		{
			if (!display.isDisposed())
			{
				workbench.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop()
	{
		final IWorkbench workbench = PlatformUI.getWorkbench();

		if (workbench != null)
		{
			final Display display = workbench.getDisplay();
			display.syncExec(new WorkbenchDisposer());
		}
	}
}
