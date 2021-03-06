/*
**
**	$Filename:	can_ring.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	CAN message ring buffer
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

#include <avr/io.h>
#include <avr/interrupt.h>

#include "can_ring.h"

/**
 * Diese Methode initialisiert einen Ringbuffer.
 */
void ring_init(struct can_ring *ring)
{
	ring->start = 0;
	ring->pos   = 0;
	ring->size  = 0;
	ring->count = 0;
}

/**
 * Diese Methode gibt die hinterste Position
 * im Ringbuffer an, in die eine CAN-Message
 * geschrieben werden soll.
 */
CAN_message *ring_get_pos(struct can_ring *ring)
{
	uint8_t sreg = SREG;
	
	cli();
	CAN_message *msg = &ring->buffer[ring->pos];
	SREG = sreg;
	
	return msg;
}

/**
 * Diese Methode gibt die vorderste Position im
 * Ringbuffer an, von der eine CAN-Message
 * gelesen werden soll.
 */
CAN_message *ring_get_start(struct can_ring *ring)
{
	uint8_t sreg = SREG;
	
	cli();
	CAN_message *msg = &ring->buffer[ring->start];
	SREG = sreg;
	
	return msg;
}

/**
 * Diese Methode erhöht die Schreibposition im
 * Ringbuffer. Zusätzlich wird geprüft, ob der
 * Ringbuffer vollgelaufen ist.
 */
uint8_t ring_increase(struct can_ring *ring)
{
	register uint8_t sreg = SREG;

	cli();
	register uint8_t pos  = ring->pos;
	register uint8_t size = ring->size;
	pos = (pos + 1) & CAN_RING_MASK;
	size++;

	ring->pos  = pos;
	ring->size = size;
	SREG = sreg;

	return size >= CAN_RING_SIZE;
}

/**
 * Diese Methode erniedrigt die Leseposition im
 * Ringbuffer. Ein Unterlauf wird nicht
 * überprüft.
 */
void ring_decrease(struct can_ring *ring)
{
	uint8_t sreg = SREG;

	cli();
	ring->start = (ring->start + 1) & CAN_RING_MASK;
	ring->size--;
	ring->count++;
	SREG = sreg;
}
