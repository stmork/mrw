/*
**
**	$Filename:	ShellBeautifier.java $
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

package de.morknet.mrw.beautifier;

import org.eclipse.xpand2.output.FileHandle;

public class ShellBeautifier extends BaseBeautifier {
	
	ShellBeautifier(FileHandle info) {
		super(info);
	}
	
	void process()
	{
		boolean text = false;
		
		for (String line : m_Input)
		{
			if (line.length() > 0)
			{
				text = true;
			}
			if (text)
			{
				m_Output.add(line);
			}
		}
	}
	static boolean isShellScript(FileHandle info)
	{
		String name = info.getTargetFile().getName();
		
		return name.endsWith(".sh") || name.endsWith(".csh");
	}

}
