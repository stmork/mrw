/*
**
**	$Filename:	ping.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Ping a gateway.
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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "mrw.h"
#include "mcp2515.h"

#include "can_pc.h"

static int flood =   0;
static int max   = 500;

static int ping(int fd)
{
	unsigned char  buffer[8];

	buffer[0] = PING;
	return uart_send_can_data(fd, BROADCAST_SID, buffer, 1);
}

int main(int argc, char * argv[])
{
	int i;
	int result;

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

	i = 0;
	do
	{
		result = ping(fd);
		if (!flood)
		{
			sleep(1);
		}
	}
	while ((++i < max) && (result > 0));
	close(fd);

	return result > 0 ? EXIT_SUCCESS : EXIT_FAILURE;
}
