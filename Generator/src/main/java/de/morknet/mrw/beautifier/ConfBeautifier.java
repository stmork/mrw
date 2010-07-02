/*
**
**	$Filename:	ConfBeautifier.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ConfBeautifier.java 931 2010-04-14 08:39:15Z smork $
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

public class ConfBeautifier extends BaseBeautifier {

	ConfBeautifier(FileHandle info) {
		super(info);
	}

	@Override
	void process() {
		for (String line : m_Input)
		{
			if (line.length() > 0)
			{
				if (line.equals("##"))
				{
					m_Output.add("#");
				}
				else if(line.equals("#"))
				{
					m_Output.add("");
				}
				else
				{
					m_Output.add(line);
				}
			}
		}
	}

	static boolean isConfiguration(FileHandle info)
	{
		String name = info.getTargetFile().getName();
		
		return
			name.endsWith(".conf") ||
			name.endsWith(".cnf") ||
			name.endsWith(".dot") ||
			name.endsWith(".cfg") ||
			name.equals("iftab") ||
			name.equals("interfaces") ||
			name.equals("sudoers") ||
			name.startsWith("common-");
	}
}
