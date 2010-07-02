/*
**
**	$Filename:	MrwBeautifier.java $
**	$Revision: 944 $
**	$Date: 2010-04-23 09:01:13 +0200 (Fr, 23. Apr 2010) $
**	$Author: smork $
**	$Id: MrwBeautifier.java 944 2010-04-23 07:01:13Z smork $
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
import org.eclipse.xpand2.output.PostProcessor;

public class MrwBeautifier implements PostProcessor {

	public void beforeWriteAndClose(FileHandle info) {
		BaseBeautifier beautifier = null;
		
		if (EmptyLineRemover.isLog(info))
		{
			beautifier = new EmptyLineRemover(info);
		}
		else if (ShellBeautifier.isShellScript(info))
		{
			beautifier = new ShellBeautifier(info);
		}
		else if (ConfBeautifier.isConfiguration(info))
		{
			beautifier = new ConfBeautifier(info);
		}
		
		if (beautifier != null)
		{
			beautifier.parse();
		}
	}

	public void afterClose(FileHandle info)
	{
	}
}
