/*
**
**	$Filename:	RailwayBase.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: RailwayBase.java 931 2010-04-14 08:39:15Z smork $
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.test;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.batch.BatchRunner;

abstract public class RailwayBase
{
	private final static Log       log        = LogFactory.getLog(RailwayBase.class);
	protected final TestController controller = TestController.getController();
	protected final Modell         model      = ModellFactory.getInstance();

	protected void checkRoute(
			final boolean rangier,
			final boolean direction,
			final String ... segmentNames ) throws IOException, InterruptedException
	{
		try
		{
			controller.clearSegmentSelection();
			for (String name : segmentNames)
			{
				controller.addSegmentSelection(model.findSegment(name));
			}
			BatchRunner runner = controller.computeRoute(rangier, direction);
			Route route = runner.getRoute();
			if (route != null)
			{
				runner.join();
				log.info(controller.getSelectedRoute().toString());
				controller.removeRoute(route);
			}
		}
		catch(RuntimeException re)
		{
			log.error(re.getLocalizedMessage(), re);
		}
	}

	protected RailwayBase() throws Exception
	{
		controller.prepare();
	}

	protected void close()
	{
		controller.close();
	}

	protected void test() throws IOException, InterruptedException
	{
		checkRoute(true,  true,  "S4b", "N2c");
		checkRoute(true,  false, "N2c", "1");
		checkRoute(false, false, "S5a", "S2a");
		checkRoute(true,  false, "S2a", "S2b");
		checkRoute(true,  true,  "S4b", "4", "N3c");
		checkRoute(true,  false, "N3c", "S1a");
		checkRoute(true,  true,  "S2b", "N3d");
		checkRoute(true,  false, "N3d", "S5a");
		
		checkRoute(false, true, "SO5a", "3", "N1b");
//		model.findSegment("Abschnitt N2a").besetzt();
//			model.findSegment("Abschnitt N3a").besetzt();
//		model.findSegment("Abschnitt 4").besetzt();
		
		model.findSegment("N5a").free();
		model.findSegment("4").free();
		checkRoute(false, true, "SO4a", "4", "SO5a");
		checkRoute(false, true, "SO3a", "N7a");
		for (int i = 1;i <= 7;i++)
		{
			Abschnitt a = model.findSegment(Integer.toString(i));
			controller.addSegmentSelection(a);
			controller.removeSegmentSelection(a);
		}
		for (Abschnitt a: controller.getSegmentSelection())
		{
			log.info(a);
		}
		
		controller.selectRoute(null);
		controller.removeAllRoutes();
	}
}
