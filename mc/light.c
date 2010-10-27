/*
**
**	$Filename:	light.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Light dimming test firmware
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

#include <avr/pgmspace.h>

#include "light.h"
#include "bit.h"
#include "light_profile.h"
#include "pwm.h"
#include "random.h"

static uint8_t has_lights = 0;

/*********************/
/* Lampen ohne Profil */
/*********************/

void simple_light_init(struct mrw_simple_light *dvc)
{
	dvc->on        =   0;
	dvc->lightness = 255;
}

uint8_t simple_light_set_lightness(struct mrw_simple_light *dvc, uint8_t lightness)
{
	uint8_t changed = 0;

	if (lightness != dvc->lightness)
	{
		uint8_t turn_on  = dvc->threshold;
		uint8_t turn_off = dvc->threshold + 4;

		if ((dvc->lightness < turn_off) && (turn_off <= lightness) && dvc->on)
		{
			/* Zustandsübergang: Ausschalten */
			dvc->on = 0;
			changed = 1;
		}
		else if ((lightness < turn_on) && (turn_on <= dvc->lightness) && !dvc->on)
		{
			/* Zustandsübergang: Einschalten */
			dvc->on = 1;
			changed = 1;
		}
		dvc->lightness = lightness;
	}
	return changed;
}

/*********************/
/* Lampen mit Profil */
/*********************/

void light_init(struct mrw_light *dvc)
{
	dvc->on        =   0;
	dvc->counter   =   0;
	dvc->lightness = 255;
	dvc->profile   = get_light_profile(dvc->type);
	out_pin(&dvc->pin);
	set_dimm(dvc, 0);
	has_lights++;
}

uint8_t light_available(void)
{
	return has_lights;
}

void light_dimm(struct mrw_light *dvc)
{
	if (dvc->on)
	{
		if (dvc->profile->repeat)
		{
			dvc->counter++;
		}
		else if (dvc->counter < LIGHT_PROFILE_MASK)
		{
			dvc->counter++;
		}
		set_dimm(dvc, pgm_read_byte(&dvc->profile->values[dvc->counter & LIGHT_PROFILE_MASK]));
	}
}

void light_set_lightness(struct mrw_light *dvc, uint8_t lightness)
{
	if (lightness != dvc->lightness)
	{
		uint8_t turn_on  = dvc->threshold;
		uint8_t turn_off = dvc->threshold + 4;

		if ((dvc->lightness < turn_off) && (turn_off <= lightness) && dvc->on)
		{
			/* Zustandsübergang: Ausschalten */
			set_dimm(dvc, 0);
			dvc->counter = 0;
			dvc->on      = 0;
		}
		else if ((lightness < turn_on) && (turn_on <= dvc->lightness) && !dvc->on)
		{
			/* Zustandsübergang: Einschalten */
			dvc->counter = 0;
			dvc->on      = 1;
		}
		dvc->lightness = lightness;
	}
}

const struct light_profile *get_light_profile(uint8_t type)
{
	uint8_t idx;

	if (type < light_profile_count())
	{
		idx = type;
	}
	else switch (type)
	{
	case  64:
		idx = (random_timer() % 15) & 7;
		break;

	case  65:
		idx = (random_timer() & 3)  + 8;
		break;

	case  66:
		idx = (random_timer() & 3) + 16;
		break;

	case 0xff:
		/* Profillose Konfiguration! */
		return 0;

	default:
		idx = 14;
		break;
	}
	return &profiles[idx];
}
