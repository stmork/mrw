/*
**
**	$Filename:	qryerr.c $
**	$Revision: 967 $
**	$Date: 2010-06-06 20:20:38 +0200 (So, 06. Jun 2010) $
**	$Author: smork $
**	$Id: qryerr.c 967 2010-06-06 18:20:38Z smork $
**
**	Query buffer state.
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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "mrw.h"
#include "mcp2515.h"

#include "can_pc.h"

static void qryerr(int fd)
{
	unsigned char  buffer[8];
	
	buffer[0] = QRYERR;
	uart_send_can_data(fd, BROADCAST_SID, buffer, 1);
}

int main(int argc,char *argv[])
{
	if (argc <= 1)
	{
		printf("USAGE:\n");
		printf("%s tty\n", argv[0]);
		return EXIT_SUCCESS;
	}

	int fd = uart_open(argv[1]);
	if (fd < 0)
	{
		perror(argv[1]);
		return EXIT_FAILURE;
	}

	uart_sync(fd);

	qryerr(fd);

	close(fd);
	return EXIT_SUCCESS;
}
