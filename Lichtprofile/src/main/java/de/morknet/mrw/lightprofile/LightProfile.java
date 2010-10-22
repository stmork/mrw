/*
**
**	$Filename:	LightProfile.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Light dimming profiles
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

package de.morknet.mrw.lightprofile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import de.morknet.mrw.lightprofile.profiles.Beacon16;
import de.morknet.mrw.lightprofile.profiles.Beacon17;
import de.morknet.mrw.lightprofile.profiles.Beacon18;
import de.morknet.mrw.lightprofile.profiles.Beacon19;
import de.morknet.mrw.lightprofile.profiles.Blink12;
import de.morknet.mrw.lightprofile.profiles.Fast15;
import de.morknet.mrw.lightprofile.profiles.Gas08;
import de.morknet.mrw.lightprofile.profiles.Gas09;
import de.morknet.mrw.lightprofile.profiles.Gas10;
import de.morknet.mrw.lightprofile.profiles.Gas11;
import de.morknet.mrw.lightprofile.profiles.Medium14;
import de.morknet.mrw.lightprofile.profiles.Neon00;
import de.morknet.mrw.lightprofile.profiles.Neon01;
import de.morknet.mrw.lightprofile.profiles.Neon02;
import de.morknet.mrw.lightprofile.profiles.Neon03;
import de.morknet.mrw.lightprofile.profiles.Neon04;
import de.morknet.mrw.lightprofile.profiles.Neon05;
import de.morknet.mrw.lightprofile.profiles.Neon06;
import de.morknet.mrw.lightprofile.profiles.Neon07;
import de.morknet.mrw.lightprofile.profiles.Slow13;

public abstract class LightProfile implements Comparable<LightProfile>
{
	protected final static Set<LightProfile> profiles = new TreeSet<LightProfile>();
	private final BufferedImage image;
	private final float X_SCALE = 2.0f;
	private final float Y_SCALE = 0.5f;
	private final int   X_OFFSET = 8;
	private final int   Y_OFFSET = 8;

	static 
	{
		profiles.add(new Neon00());
		profiles.add(new Neon01());
		profiles.add(new Neon02());
		profiles.add(new Neon03());
		profiles.add(new Neon04());
		profiles.add(new Neon05());
		profiles.add(new Neon06());
		profiles.add(new Neon07());
		profiles.add(new Gas08());
		profiles.add(new Gas09());
		profiles.add(new Gas10());
		profiles.add(new Gas11());
		profiles.add(new Blink12());
		profiles.add(new Slow13());
		profiles.add(new Medium14());
		profiles.add(new Fast15());
		profiles.add(new Beacon16());
		profiles.add(new Beacon17());
		profiles.add(new Beacon18());
		profiles.add(new Beacon19());
	}
	
	protected LightProfile()
	{
		image = new BufferedImage(
				getWidth() + X_OFFSET * 2,
				getHeight() + Y_OFFSET * 2,
				BufferedImage.TYPE_INT_RGB);
	}
	
	final int getWidth()
	{
		return (int) (X_SCALE * getArray().length);
	}
	
	final int getHeight()
	{
		int max = 0;

		for (int i : getArray())
		{
			if (i > max)
			{
				max = i;
			}
		}
		return (int) (max * Y_SCALE);
	}
	
	final String getName()
	{
		return getClass().getSimpleName();
	}
	
	final int getIndex()
	{
		final String name = getName();

		return Integer.parseInt(name.substring(name.length() - 2));
	}
	
	protected boolean isRepeatable()
	{
		return false;
	}
	
	final void save() throws IOException
	{
		Graphics gfx = image.getGraphics();
		FileOutputStream os = null;
		try
		{
			os = new FileOutputStream(getName() + ".gif");
			int [] array = getArray();
			int yMax = getHeight();
			int x, s=0;
	
			gfx.setColor(Color.WHITE);
			gfx.fillRect(0, 0, image.getWidth(), image.getHeight());

			gfx.setColor(Color.LIGHT_GRAY);
			do
			{
				x = s * 16000000 / (256 * 1024);
				gfx.drawString("" + s + "s", X_OFFSET + 2 + (int)(x * X_SCALE), Y_OFFSET + yMax / 2);
				gfx.drawLine(
						X_OFFSET + (int)(x * X_SCALE), Y_OFFSET,
						X_OFFSET + (int)(x * X_SCALE), Y_OFFSET + yMax);
				s++;
			}
			while(x < getWidth());

			gfx.setColor(Color.BLACK);
			for (x = 1; x < array.length; x++)
			{
				gfx.drawLine(
						X_OFFSET + (int)((x-1) * X_SCALE),
						Y_OFFSET + (int)(yMax - array[x-1] * Y_SCALE),
						X_OFFSET + (int)(x * X_SCALE), 
						Y_OFFSET + (int)(yMax - array[x] * Y_SCALE));
			}
			ImageIO.write(image, "gif", os);
			os.flush();
		}
		finally
		{
			if (os != null)
			{
				os.close();
			}
			gfx.dispose();
		}
	}
	
	private final int LENGTH_BITS = 4;
	private final int LENGTH_MAX = (1 << LENGTH_BITS);
	private final int LENGTH_MASK = LENGTH_MAX - 1;

	protected void printArray(PrintWriter writer)
	{
		int [] array = getArray();

		System.out.printf("Writing array of %s (%d bytes)\n", getName(), array.length);
		writer.printf("static const uint8_t %s[LIGHT_PROFILE_SIZE] PROGMEM =\n", getName().toLowerCase());
		writer.println("{");
		for (int i = 0;i < array.length;i++)
		{
			if (i > 0)
			{
				writer.print(",");
			}
			if ((i & LENGTH_MASK) == 0)
			{
				if (i > 0)
				{
					writer.println("");
				}
				writer.print("\t");
			}
			writer.printf(" %3d", array[i]);
		}
		writer.println("\n};");
		writer.println("");
	}

	public abstract int[] getArray();

	private static void printMC() throws IOException
	{
		FileOutputStream fos = null;
		PrintWriter pw;

		try
		{
			fos = new FileOutputStream("light_profile.c");
			pw  = new PrintWriter(fos);
			pw.println("/*");
			pw.println("**");
			pw.println("**\t$Filename:\tlight_profile.c $"); 
			pw.println("**\t$Revision$");
			pw.println("**\t$Date$");
			pw.println("**\t$Author$");
			pw.println("**\t$Id$");
			pw.println("**");
			pw.println("**\tLight dimming profiles");
			pw.println("**");
			pw.println("**\tCopyright (C) 2010 committers of this modelrailway project. All rights reserved.");
			pw.println("**");
			pw.println("**\tThis program and the accompanying materials are made available under the");
			pw.println("**\tterms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0");
			pw.println("**\twhich accompanies this distribution.");
			pw.println("**");
			pw.println("**\tThe Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html");
			pw.println("**");
			pw.println("**");
			pw.println("*/");
			pw.println("");
			pw.println("#include \"light_profile.h\"");
			pw.println("");
			pw.println("#include <avr/pgmspace.h>");
			pw.println("");

			for (LightProfile profile : profiles)
			{
				profile.printArray(pw);
			}
			pw.println("const struct light_profile profiles[] =");
			pw.print("{");
			int i = 0;
			for (LightProfile profile : profiles)
			{
				if (i++ > 0)
				{
					pw.print(",");
				}
				pw.println("");
				pw.printf("\t{ %10s, %d }", profile.getName().toLowerCase(), profile.isRepeatable() ? 1 : 0);
			}
			pw.println("\n};");
			pw.println("");
			pw.println("uint8_t light_profile_count(void)");
			pw.println("{");
			pw.println("\treturn (sizeof(profiles) / sizeof(struct light_profile));");
			pw.println("}");

			pw.flush();
			fos.flush();
		}
		finally
		{
			if (fos != null)
			{
				fos.close();
			}
		}
	}

	public static void main(String [] args)
	{
		for (LightProfile profile : profiles)
		{
			try
			{
				System.out.printf("%2d: %s\n", profile.getIndex(), profile.getName());
				profile.save();
			}
			catch (IOException e)
			{
				e.printStackTrace(System.err);
			}
		}
		try
		{
			printMC();
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
		}
	}

	@Override
	public int compareTo(LightProfile o)
	{
		return getIndex() - o.getIndex();
	}
}