/*
**
**	$Filename:	Renumberer.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.metamodel.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Beleuchtungsmodul;
import de.morknet.mrw.metamodel.Controller;
import de.morknet.mrw.metamodel.Formsignal;
import de.morknet.mrw.metamodel.Gleismodul;
import de.morknet.mrw.metamodel.Impulsmodul;
import de.morknet.mrw.metamodel.Licht;
import de.morknet.mrw.metamodel.Lichtsignal;
import de.morknet.mrw.metamodel.Magnetartikel;
import de.morknet.mrw.metamodel.Modell;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Modul;
import de.morknet.mrw.metamodel.Unit;
import de.morknet.mrw.metamodel.Verzweigung;

public class Renumberer
{
	public  final static ModelrailwayPackage mrwPackage = ModelrailwayPackage.eINSTANCE;
	private final Resource resource;
	private final Modell model;

	private Renumberer(String modelfile) throws IOException
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("modelrailway", new XMIResourceFactoryImpl());

		URI uri = URI.createURI(modelfile);
		resource = resourceSet.createResource(uri);

		resource.load(null);
		model = (Modell)resource.getContents().get(0);
	}

	private static List<Unit> getUnits(Modul modul)
	{
		List<Unit> units = new ArrayList<Unit>();

		if (modul instanceof Impulsmodul)
		{
			Impulsmodul im = (Impulsmodul)modul;
			for(Magnetartikel ma : im.getMagnetartikel())
			{
				if ((ma instanceof Verzweigung) || (ma instanceof Formsignal))
				{
					units.add((Unit)ma);
				}
			}
		}
		else if (modul instanceof Beleuchtungsmodul)
		{
			Beleuchtungsmodul bm = (Beleuchtungsmodul)modul;
			units.addAll(bm.getLampen());
		}
		else if (modul instanceof Gleismodul)
		{
			Gleismodul gm = (Gleismodul)modul;
			units.addAll(gm.getAbschnitte());
		}
		return units;
	}

	private void traverse()
	{
		for (Controller controller : model.getController())
		{
			int ID = controller.getId();
			int newID;
			int newUnitNo;

			System.out.printf("ID=%d\n", ID);
			newID = ID * 100 + 40;
			newUnitNo = ID * 0x100 + 0x40;

			for (Anschluss a : controller.getAnschluesse())
			{
				System.out.printf("  A:    %5d ->  %5d\n", a.getNummer(), newID);
				a.setNummer(newID);
				newID -= 10;

				newUnitNo -= 0x10;
				for (Lichtsignal signal : a.getLichtsignale())
				{
					System.out.printf("    LS: %5d -> 0x%04x\n", signal.getUnit_no(), newUnitNo);
					signal.setUnit_no(newUnitNo++);
				}
				for (Licht licht : a.getLichter())
				{
					System.out.printf("    L:  %5d -> 0x%04x\n", licht.getUnit_no(), newUnitNo);
					licht.setUnit_no(newUnitNo++);
				}
			}

			newID = ID * 100 + 1;
			newUnitNo = ID * 0x100 + 0x80;
			for(Modul m : controller.getModule())
			{
				System.out.printf("  M:    %5d ->  %5d\n", m.getNummer(), newID);
				m.setNummer(newID);
				newID += 1;

				for(Unit unit : getUnits(m))
				{
					System.out.printf("    U:  %5d -> 0x%04x (%s)\n",
							unit.getUnit_no(), newUnitNo, unit.getClass().getSimpleName());
					unit.setUnit_no(newUnitNo++);
				}
				newUnitNo += 0x10;
			}
		}
	}

	private void save() throws IOException
	{
		resource.save(null);
	}
	
	private static void addProject(String projectname) throws IOException
	{
		File parent = new File("../" + projectname);
		URI  uri    = URI.createFileURI(parent.getCanonicalPath() + "/");

		EcorePlugin.getPlatformResourceMap().put(projectname, uri);
	}

	private static String makeURI(String name) throws IOException
	{
		File file = new File(name);
		URI  uri  = URI.createFileURI(file.getCanonicalPath());
		return uri.toString();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		addProject("AnlageZwei");
		Renumberer renumberer = new Renumberer(makeURI("../AnlageZwei/src/main/resources/AnlageZwei.modelrailway"));
		
		renumberer.traverse();
		renumberer.save();
	}

}
