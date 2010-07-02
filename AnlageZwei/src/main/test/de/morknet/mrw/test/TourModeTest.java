/*
**
**	$Filename:	TourModeTest.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: TourModeTest.java 931 2010-04-14 08:39:15Z smork $
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.TourPoint;
import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.gui.info.TourInfo;
import de.morknet.mrw.util.LogUtil;

public class TourModeTest extends ControllerTestBase
{
	private final static Log log = LogFactory.getLog(TourModeTest.class);

	private final static int MAX_CYCLIC_SEGMENTS = 25;

	@Test
	public void tourmodes()
	{
		Route route;

		for (TourInfo info : model.getTourInfos())
		{
			TourMode tour = TourMode.getTourMode(controller, info);

			controller.clearSegmentSelection();

			Assert.assertNotNull(LogUtil.printf("Die Tour %s konnte nicht bereitgestellt werden!", info), tour);
			log.info("============ Simuliere Tour " + tour + " ============");
			TourPoint start = tour.getFirstTourPoint();
			Assert.assertNotNull("Es muss Informationen über den Startpunkt geben!", start);

			Abschnitt a = start.getSegments().get(0);
			Assert.assertNotNull("Es muss einen Gleisabschnitt im Startpunkt geben!", a);

			controller.addSegmentSelection(a);
			Assert.assertTrue("Es muss exakt ein Gleisabschnitt ausgewählt sein!",
					controller.getSegmentSelection().size() == 1);
			Assert.assertSame("Der gewählte Startpunkt muss gleich dem Startgleisabschnitt sein",
					controller.getSegmentSelection().get(0), a);

			// Fahrt starten.
			Assert.assertFalse(LogUtil.printf("Die Fahrt '%s' muss abgeschaltet sein!", tour), tour.isActive());
			tour.run();
			Assert.assertTrue(LogUtil.printf("Die Fahrt '%s' muss aktiv sein!", tour), tour.isActive());
			route = tour.getRoute();
			Assert.assertNotNull("Es muss eine Fahrstraße vorhanden sein!", route);
			simulateTour(route, info.isLoop() ? MAX_CYCLIC_SEGMENTS : 0);
			tour.deactivate();
			
			// Alle Gleise und Fahrstraßen freigeben!
			controller.removeAllRoutes();
			for(Abschnitt abschnitt : model.getSegments())
			{
				abschnitt.free();
			}
		}
	}
}
