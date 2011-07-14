/*
**
**	$Filename:	UtilTest.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
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

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.MrwProperties;
import de.morknet.mrw.util.MrwRandom;
import de.morknet.mrw.util.NameUtil;
import de.morknet.mrw.util.Protocol;
import de.morknet.mrw.util.ReferenceCounter;

public class UtilTest
{
	private final static Log log = LogFactory.getLog(UtilTest.class);

	@Test
	public void now()
	{
		Assert.assertNotNull("Das heutige Datum fehlt!", LogUtil.now());
	}
	
	@Test
	public void format()
	{
		Assert.assertNotNull("Das heutige Datum fehlt!", LogUtil.format(new Date()));
	}
	
	@Test
	public void printf()
	{
		Assert.assertEquals("A 1 b Test C.", LogUtil.printf("A %d b %s C.", 1, "Test"));
	}
	
	@Test
	public void pad()
	{
		Assert.assertEquals("X    ",  LogUtil.pad("X", 5));
		Assert.assertEquals("X23456", LogUtil.pad("X23456", 5));
		Assert.assertEquals("X____",  LogUtil.pad("X", 5, '_'));
		Assert.assertEquals("X23456", LogUtil.pad("X23456", 5));
	}
	
	@Test
	public void padLeft()
	{
		Assert.assertEquals("    X",  LogUtil.padLeft("X", 5));
		Assert.assertEquals("X23456", LogUtil.padLeft("X23456", 5));
		Assert.assertEquals("____X",  LogUtil.padLeft("X", 5, '_'));
		Assert.assertEquals("X23456", LogUtil.padLeft("X23456", 5));
	}

	@Test
	public void empty()
	{
		for (int i = 0;i < 100;i++)
		{
			final String empty = LogUtil.empty(i);

			Assert.assertEquals("Die erwartete Länge des Leerstrings stimmt nicht!", i, empty.length());
		}
	}

	@Test
	public void id()
	{
		Assert.assertEquals("0001:0005", NameUtil.getIdString(0x00010005));
		Assert.assertEquals("0012:0056", NameUtil.getIdString(0x00120056));
		Assert.assertEquals("0123:0567", NameUtil.getIdString(0x01230567));
		Assert.assertEquals("1234:5678", NameUtil.getIdString(0x12345678));
		Assert.assertEquals("8000:4000", NameUtil.getIdString(0x80004000));
		Assert.assertEquals("8700:4300", NameUtil.getIdString(0x87004300));
		Assert.assertEquals("8760:4320", NameUtil.getIdString(0x87604320));
		Assert.assertEquals("8765:4321", NameUtil.getIdString(0x87654321));
	}
	
	@Test
	public void route()
	{
		Assert.assertNotNull(NameUtil.logRouteType(true,  true));
		Assert.assertNotNull(NameUtil.logRouteType(true,  false));
		Assert.assertNotNull(NameUtil.logRouteType(false, true));
		Assert.assertNotNull(NameUtil.logRouteType(false, false));
	}

	@Test
	public void registry()
	{
		Assert.assertEquals("heute.ist.ein.schoener.tag", NameUtil.getRegistryIdString("Heute ist ein schöner Tag!"));
		Assert.assertEquals("heute.luecke",               NameUtil.getRegistryIdString("Heute     Lücke........"));
		Assert.assertEquals("umlaute.ae.oe.uessaeoeue",   NameUtil.getRegistryIdString("Umlaute:  ÄÖÜßäöü"));
	}
	
	@Test
	public void signal()
	{
		final String signalName = "Bahnhof Alt Ulm N3";

		final Modell model = ModellFactory.getInstance();
		Assert.assertNotNull("Die Modelleisenbahn konnte nicht gefunden werden!", model);

		final Signal signal = model.findSignal(signalName);
		Assert.assertNotNull("Signal " + signalName + " konnte nicht gefunden werden!", signal);
		Assert.assertEquals("N3", NameUtil.getNumber(signal));
	}

	@Test
	public void protocol()
	{
		Protocol protocol = new Protocol();
		
		protocol.protocol();
		protocol.add("1. Eintrag");
		protocol.add("2. Eintrag");
		protocol.add("3. Eintrag");
		protocol.protocol();
		protocol.clear();
		protocol.protocol();
	}
	
	@Test
	public void hostname()
	{
		Assert.assertNotNull("Es muss einen Hostnamen geben!", MrwProperties.getSimpleHostName());
	}
	
	@Test
	public void properties()
	{
		Assert.assertNotNull("Es muss eine Modelleisenbahn konfiguriert sein!", MrwProperties.getProperty(ModellFactory.RAILWAY_KEY));
	}
	
	@Test
	public void random3()
	{
		random(3);
	}
	
	@Test
	public void random4()
	{
		random(4);
	}
	
	@Test
	public void random5()
	{
		random(5);
	}
	
	@Test
	public void random6()
	{
		random(6);
	}

	@Test
	public void referenceCounter()
	{
		ReferenceCounter<String> counter = new ReferenceCounter<String>();
		
		Assert.assertEquals(0, counter.getValue("unknown"));
		counter.count("val1");
		counter.count("val1");
		Assert.assertEquals(2, counter.getValue("val1"));
		counter.count("val2", 2);
		Assert.assertEquals(2, counter.getValue("val1"));
		Assert.assertEquals(2, counter.getValue("val2"));
		counter.count("val3", 0);
		Assert.assertEquals(2, counter.getValue("val1"));
		Assert.assertEquals(2, counter.getValue("val2"));
		Assert.assertEquals(0, counter.getValue("val3"));
		counter.count("val3", -1);
		Assert.assertEquals(2, counter.getValue("val1"));
		Assert.assertEquals(2, counter.getValue("val2"));
		Assert.assertEquals(-1, counter.getValue("val3"));
		counter.count("val1");
		Assert.assertEquals(3, counter.getValue("val1"));
		Assert.assertEquals(2, counter.getValue("val2"));
		Assert.assertEquals(-1, counter.getValue("val3"));
		counter.zero();
		Assert.assertEquals(0, counter.getValue("val1"));
		Assert.assertEquals(0, counter.getValue("val2"));
		Assert.assertEquals(0, counter.getValue("val3"));
		Assert.assertEquals(3, counter.keySet().size());
		counter.clear();
		Assert.assertEquals(0, counter.getValue("val1"));
		Assert.assertEquals(0, counter.getValue("val2"));
		Assert.assertEquals(0, counter.getValue("val3"));
		Assert.assertEquals(0, counter.keySet().size());
	}

	private void random(final int dom)
	{
		final int       max  = 100;
		final double    avrg = (dom - 1) * 0.5;
		final int       count [] = new int[dom];
		final MrwRandom random   = new MrwRandom();
	          int       r = 0;
		
		for (int i = 0;i < max;i++)
		{
			int q = random.nextInt(dom); 
			count[q]++;
			r += q;
		}
		
		// Auswertung des Gesamtfehlers
		double s   = (double)r / max;
		double err = s/avrg * 100;
		log.info(LogUtil.printf("%d %f - %f (%f%%)", r, s, avrg, err));
		Assert.assertTrue("Der Fehler ist zu groß!", (75.0 < err) && (err <= 133.3));

		// Auswertung der Einzelfehler. Wie häufig wurde eine bestimmte Zahl gewürfelt?
		int errCount = 0;
		for (int i = 0; i < dom;i++)
		{
			err = count[i] * 100.0 * dom / max; 
			log.info(LogUtil.printf("%2d: %5d (%f%%)", i, count[i], err));
			if ((err <= 66.66) || (150.0 < err))
			{
				errCount++;
			}
		}
		log.info(LogUtil.printf("Fehler: %d, (%f%%)", errCount, (errCount * 100.0 / dom)));
		Assert.assertTrue("Es tauchen zu viele Abweichungen auf!", (errCount * 100.0 / dom) <= 40.0 );
	}
}
