/*
**
**	$Filename:	light_profile.c $
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

#include "light_profile.h"

#include <avr/pgmspace.h>

static const uint8_t profile01[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,38,51,63,73,82,90,96,101,105,107,
	128,128,128,128,128,96,96,
	128,160,255,255,255,255,255,
	128,160,255,255,255,255,255,255,
	32,32,32,32,32,32,32,32,32,32,
	144,160,176,208,240,255,255,255,
	32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile02[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,128,128,128,128,96,96,
	128,160,255,255,255,255,255,
	32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,160,255,255,255,255,
	32,32,32,32,32,32,32,
	128,160,255,255,255,255,255,
	144,160,176,208,240,255,255,255,
	32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile03[LIGHT_PROFILE_SIZE] PROGMEM =
{ 0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,128,128,128,128,96,96,
	128,160,255,255,255,255,255,255,255,255,
	32,32,32,32,32,
	128,160,255,255,255,255,255,255,255,255,
	32,32,32,32,32,32,32,32,
	128,160,255,255,255,
	32,32,32,32,32,32,32,32,32,32,32,32,
	128,128,128,128,128,96,96,
	128,160,255,255,255,255,255,
	32,32,32,32,32,32,32,32,
	128,160,255,255,
	32,32,32,32,32,32,32,32,
	128,160,255,255,255,255,255,255,255,255,
	32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile04[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,160,255,255,255,255,
	32,32,32,32,32,
	128,160,255,255,
	32,32,32,
	128,160,255,255,
	32,32,32,32,
	128,160,255,255,
	32,32,32,
	128,160,255,
	32,32,32,32,
	128,160,255,255,255,255,255,255,255,255,
	32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile05[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	128,160,255,255,255,255,
	32,32,160,160,32,32,160,
	128,160,255,255,255,255,
	160,32,32,160,160,32,32,160,160,32,32,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile06[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,128,128,128,128,96,96,
	128,160,255,255,255,255,
	32,32,32,32,32,32,32,
	128,160,255,255,255,255,255,
	32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
	128,160,255,255,255,255,
	32,32,32,32,32,32,32,
	128,160,255,255,255,255,255,
	144,160,176,208,240,255,255,255,
	32,32,32,32,32,32,32,32,32,32,
	255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255, 
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile07[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,48,64,80,96,128,128,128,128,
	255,255,255,255,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
};

static const uint8_t profile08[LIGHT_PROFILE_SIZE] PROGMEM =
{
	0,16,24,32,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	255,255,255,255,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	128,160,255,255,255,255,
	32,32,160,160,32,32,160,
	128,160,255,255,255,255,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	128,160,255,255,255,255,255,255,255,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,160,160,32,32,
	160,160,32,32,160,160,32,32,160,160,32,32,
	128,160,255,255,255,255,
	32,32,160,160,32,32,160,
	128,160,255,255,255,255,
	32,32,160,160,32,32,160,
	128,160,255,255,255,255,
	32,32,160,160,32,32,160,
	128,160,255,255,255,255,
	255,255,255
};

static const uint8_t profile09[LIGHT_PROFILE_SIZE] PROGMEM =
{
	20,40,64,88,112,136,160,184,208,232,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,
	232,184,136,88,40,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	0,0,0,0,0,0,0,0,0,0,0,
	20,40,64,88,112,136,160,184,208,232,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,
	232,184,136,88,40,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	0,0,0,0,0,0,0,0,0,0,0,
	20,40,64,88,112,136,160,184,208,232,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,
	255,255,255,255,255,255,255,255,255,255,
	232,184,136,88,40,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	0,0,0,0,0,0,0,0,0,0,0
};

static const uint8_t profile10[LIGHT_PROFILE_SIZE] PROGMEM =
{
	  2,   4,   6,   8,  10,  12,  14,  16,  18,  20,  22,  24,  26,  28,  30,  32, 
	 34,  36,  38,  40,  42,  44,  46,  48,  50,  52,  54,  56,  58,  60,  62,  64,
	 65,  66,  67,  68,  69,  70,  71,  72,  73,  74,  75,  76,  77,  78,  79,  80,
	 81,  82,  83,  84,  85,  86,  87,  88,  89,  90,  91,  92,  93,  94,  95,  96,
	 97,  98,  99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112,
	113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128,
	129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144,
	145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160,
	161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176,
	177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192,
	193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208,
	209, 210 ,211, 212, 213 ,214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224,
	225, 225, 226, 226, 227, 227, 228, 228, 229, 229, 230, 230, 231, 231, 232, 232,
	233, 233, 234, 234, 235, 235, 236, 236, 237, 237, 238, 238, 239, 239, 240, 240,
	241, 241, 242, 242, 243, 243, 244, 244, 245, 245, 246, 246, 247, 247, 248, 248,
	249, 249, 250, 250, 251, 251, 252, 252, 253, 253, 254, 254, 255, 255, 255, 255
};

static const uint8_t profile11[LIGHT_PROFILE_SIZE] PROGMEM =
{
	  2,   4,   0,   0,  10,  12,   0,   0,  18,  20,   0,   0,  26,  28,   0,   0, 
	 34,  36,   0,   0,  42,  44,   0,   0,  50,  52,   0,   0,  58,  60,   0,   0,
	 65,  66,   0,   0,  69,  70,   0,   0,  73,  74,   0,   0,  77,  78,   0,   0,
	 81,  82,   0,   0,  85,  86,   0,   0,  89,  90,   0,   0,  93,  94,   0,   0,
	 97,  98,   0,   0, 101, 102,   0,   0, 105, 106,   0,   0, 109, 110,   0,   0,
	113, 114,   0,   0, 117, 118,   0,   0, 121, 122,   0,   0, 125, 126,   0,   0,
	129, 130,   0,   0, 133, 134,   0,   0, 137, 138,   0,   0, 141, 142,   0,   0,
	145, 146,  32,  32, 149, 150,  32,  32, 153, 164,  32,  32, 157, 158,  32,  32,
	161, 162,  64,  64, 165, 166,  64,  64, 169, 170,  64,  64, 173, 174,  64,  64,
	177, 178,  96,  96, 181, 182,  96,  96, 185, 186,  96,  96, 189, 190,  96,  96,
	193, 194, 128, 128, 197, 198, 128, 128, 201, 202, 128, 128, 205, 206, 128, 128,
	209, 210 ,160, 160, 213 ,214, 160, 160, 217, 218, 160, 160, 221, 222, 160, 160,
	225, 225, 192, 192, 227, 227, 192, 192, 229, 229, 192, 192, 231, 231, 192, 192,
	233, 233, 224, 224, 235, 235, 224, 224, 237, 237, 224, 224, 239, 239, 224, 224,
	241, 241, 240, 240, 243, 243, 240, 240, 245, 245, 240, 240, 247, 247, 240, 240,
	249, 249, 250, 250, 251, 251, 252, 252, 253, 253, 254, 254, 255, 255, 255, 255
};

struct light_profile profiles[] =
{
	{ profile08, 1 },
	{ profile09, 1 },
	{ profile10, 0 },
	{ profile11, 0 },
	{ profile01, 0 },
	{ profile02, 0 },
	{ profile03, 0 },
	{ profile04, 0 },
	{ profile05, 0 },
	{ profile06, 0 },
	{ profile07, 0 }
};
