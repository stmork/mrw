/*
**
**	TCP connection handler
**
**	(C) Copyright 2010-2022  Steffen A. Mork
**	    All Rights Reserved
**
**
*/

#pragma once

#ifndef SERVER_SOCKET_H
#define SERVER_SOCKET_H

#include <netinet/ip.h>

class ServerSocket
{
	int s;
	struct sockaddr_in client_addr;
	unsigned int clientlen;

public:
	ServerSocket();
	virtual ~ServerSocket()
	{
		Close();
	}

	int Accept();

private:
	void Close();
};

#endif
