/*
**
**	$Filename:	spi.c $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Modellrailway micro controller
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

#include "spi.h"
#include "bit.h"

/**
 * Diese Methode initialisiert das SPI-Interface.
 */
void spi_init(void)
{
	// Aktivieren der Pins für das SPI Interface
	// SCP und MOSI auf out
	// MISO auf in
	DDR_SPI |= _BV(P_SCK);
	SET_PORT_BIT(DDR_SPI, P_MOSI);
	CLR_PORT_BIT(DDR_SPI, P_MISO);

	CLR_PORT_BIT(PORT_SPI, P_SCK);
	CLR_PORT_BIT(PORT_SPI, P_MOSI);
	CLR_PORT_BIT(PORT_SPI, P_MISO);

	// Aktivieren des SPI Master Interfaces, fosc = fclk / 2
	SPCR = _BV(SPE) | _BV(MSTR) | SPI_R002;
	SPSR = SPI_X2002;
}

/**
 * Diese Methode liest ein Byte aus dem SPI aus. Vor
 * dem Lesen wird auf eine evtl. bestehende Übertragung
 * gewartet.
 */
uint8_t spi_readc(void)
{
	// Sendet ein Byte
	spi_putc(0xa9); /* 10101001 */

	// Wartet bis Byte gesendet wurde, Ergebnis in SPDR
	WAIT_SPI;

	// Hier ist das Ergebnis
	return SPDR;
}

/**
 * Diese Methode liest ein Byte aus dem SPI aus. Vor
 * dem Lesen wird nicht auf eine evtl. bestehende Übertragung
 * gewartet.
 */
uint8_t spi_getc(void)
{
	// Sendet ein Byte
	SPDR = 0xa3; /* 10100011*/

	// Wartet bis Byte gesendet wurde, Ergebnis in SPDR
	WAIT_SPI;

	// Hier ist das Ergebnis
	return SPDR;
}

/**
 * Diese Methode stößt das Lesen eines Bytes aus dem SPI an. Es
 * wird nicht auf die Beendigung des Lesevorgangs gewartet.
 */
uint8_t spi_prefetchc(void)
{
	uint8_t result;

	// Wartet bis Byte gesendet wurde, Ergebnis in SPDR
	WAIT_SPI;

	// Hier ist das Ergebnis
	result = SPDR;
	
	// Sendet ein Byte
	SPDR = 0xa5; /* 10100101 */

	return result;	
}

/**
 * Diese Methode schreibt ein Byte auf das SPI aus. Vor der
 * Ausführung wird auf Beendigunge eines evtl. bestehenden
 * Übertragungsvorgangs gewartet. Auf das Ende des Schreibens
 * wird nicht gewartet.
 */
void spi_putc( uint8_t data )
{
	// Wartet bis Byte gesendet wurde
	WAIT_SPI;

	// Sendet ein Byte
	SPDR = data;
}
