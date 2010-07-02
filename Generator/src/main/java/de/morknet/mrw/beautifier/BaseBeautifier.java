/*
**
**	$Filename:	BaseBeautifier.java $
**	$Revision: 944 $
**	$Date: 2010-04-23 09:01:13 +0200 (Fr, 23. Apr 2010) $
**	$Author: smork $
**	$Id: BaseBeautifier.java 944 2010-04-23 07:01:13Z smork $
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseBeautifier
{
	private final Log            m_Log    = LogFactory.getLog(BaseBeautifier.class);
	private final FileHandle     m_FileHandle;
	protected StringBuffer       m_Buffer = new StringBuffer();
	protected final List<String> m_Input  = new ArrayList<String>();
	protected final List<String> m_Output = new ArrayList<String>();
	
	BaseBeautifier(FileHandle info)
	{
		m_FileHandle = info;

		try {
			StringReader   sr = new StringReader(m_FileHandle.getBuffer().toString());
			BufferedReader br = new BufferedReader(sr);
			String         line;
		
			while ((line = br.readLine()) != null)
			{
				m_Input.add(line);
			}
			br.close();
		} catch (IOException e) {
			m_Log.error(e);
		}
	}

	abstract void process();
	
	void parse()
	{
		try
		{
			process();
			for (String line : m_Output)
			{
				m_Buffer.append(line).append("\n");
			}
			m_FileHandle.setBuffer(m_Buffer);
		}
		catch(Exception e)
		{
			m_Log.error(e);
		}
	}
}
