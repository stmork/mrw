/*
**
**	$Filename:	Command.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
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

package de.morknet.mrw.comm;

import java.util.HashMap;
import java.util.Map;

import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.OccupationCode;

/**
 * Diese Enum listet Kommando-Codes auf. Alle Kommando-Codes werden gemäß diesem Zustandsautomaten in den einzelnen Betriebsmodi
 * verarbeitet:
 * <p>
 * Bild: <img src="doc-files/Kommandoverarbeitung.jpg"/>
 * </p>

 * @author smork
 *
 */
public enum Command
{
	/**
	 * Schalte Weiche nach links bzw. DKW auf Bogen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SETLFT</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <li>{@link MsgCode#MSG_QUEUED}
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * <li>{@link MsgCode#MSG_QUEUE_FULL}
	 * <li>{@link MsgCode#MSG_SWITCH_FAILED}
	 * </ul>
	 * </p>
	 * 
	 */
	SETLFT(0x01),

	/**
	 * Schalte Weiche nach rechts bzw. DKW auf Kreuz.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SETRGT</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <li>{@link MsgCode#MSG_QUEUED}
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * <li>{@link MsgCode#MSG_QUEUE_FULL}
	 * <li>{@link MsgCode#MSG_SWITCH_FAILED}
	 * </ul>
	 * </p>
	 */
	SETRGT(0x02),

	/**
	 * Frage Weichenlage ab.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>GETDIR</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Als Antwort kommt ein sieben Byte langes Frame mit der Weichenlage.
	 * </p>
	 * <p>
	 * <table>
	 * <tr><th align="left" colspan="2">Aufteilung Füllzustand:</th></tr>
	 * <tr><td>Data[6]</td><td>{@link DirectionCode}</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * <li>{@link MsgCode#MSG_UNITTYPE_WRONG}
	 * </ul>
	 * </p>
	 * 
	 * @see DirectionCode
	 */
	GETDIR(0x03),

	/**
	 * Schalte Gleisabschnitt ein.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SETRON</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * </ul>
	 * </p>
	 */
	SETRON(0x11),

	/**
	 * Schalte Gleisabschnitt aus.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SETROF</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * </ul>
	 * </p>
	 */
	SETROF(0x12),

	/**
	 * Frage Gleisbelegtzustand ab.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>GETRBS</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Als Antwort kommt ein Frame mit sieben Bytes mit dem Belegtzustand des Gleisabschnitts.
	 * </p>
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Belegtzustand:</th></tr>
	 * <tr><td>Data[6]</td><td>{@link OccupationCode}</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * </ul>
	 * </p>
	 * 
	 * @see OccupationCode
	 */
	GETRBS(0x13),

	/**
	 * Schalte Signal.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>2</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SETSGN</pre></td></tr>
	 * <tr><td>Data[1]</td><td> {@link SignalCode}</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <li>{@link MsgCode#MSG_QUEUED}
	 * <lI>{@link MsgCode#MSG_OK}
	 * <li>{@link MsgCode#MSG_UNIT_NOT_FOUND}
	 * <li>{@link MsgCode#MSG_UNITTYPE_WRONG}
	 * <li>{@link MsgCode#MSG_QUEUE_FULL}
	 * <li>{@link MsgCode#MSG_SWITCH_FAILED}
	 * </ul>
	 * </p>
	 * 
	 * @see SignalCode
	 */
	SETSGN(0x21),

	/**
	 * Starte Konfigurationsmodus.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGBGN</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * </ul>
	 * </p>
	 */
	CFGBGN(0x41),

	/**
	 * Konfiguriere Weiche/DKW mit Endabschaltung.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>5</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGSWN</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin links/Bogen</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin rechts/Kreuz</td></tr>
	 * <tr><td>Data[3]</td><td>Weichenlage links</td></tr>
	 * <tr><td>Data[4]</td><td>Weichenlage rechts</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGSWN(0x31),

	/**
	 * Konfiguriere Weiche/DKW ohne Endabschaltung.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGSWO</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin links/Bogen</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin rechts/Kreuz</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGSWO(0x32),

	/**
	 * Konfiguriere Gleisabschnitt.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGRAI</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin Gleis</td></tr>
	 * <tr><td>Data[2]</td><td>Abfragepin Gleis</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGRAI(0x33),
	
	/**
	 * Konfiguriere Formvorsignal mit zwei Spulen (Vr0, Vr1).
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGPF2</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin Vr0</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Vr1</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGPF2(0x34),
	
	/**
	 * Konfiguriere Formvorsignal mit drei Spulen (Vr0, Vr1, Vr2).
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>4</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGPF3</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin Vr0</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Vr1</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Vr2</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGPF3(0x35),
	
	/**
	 * Konfiguriere Formhauptsignal mit zwei Spulen (Hp0, Hp1).
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGMF2</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin Hp0</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Hp1</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGMF2(0x36),
	
	/**
	 * Konfiguriere Formhauptsignal mit drei Spulen (Hp0, Hp1, Hp2).
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>4</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGMF3</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin Hp0</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Hp1</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin Hp2</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGMF3(0x37),
	
	/**
	 * Konfiguriere Lichtvorsignal mit zwei Zuständen (Vr0, Vr1) und zwei Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGPL2</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin gelb</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin grün</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGPL2(0x38),

	/**
	 * Konfiguriere Lichtvorsignal mit drei Zuständen (Vr0, Vr1, Vr2) und vier Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>5</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGPL3</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin gelb links oben</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin gelb links unten</td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin grün rechts oben</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin grün rechts unten</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGPL3(0x39),

	/**
	 * Konfiguriere Lichtgleissperrsignal mit zwei Zuständen (Sh0, Sh1) und zwei Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGSL2</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin rot</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin weiß</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGSL2(0x3a),

	/**
	 * Konfiguriere Blocksignal mit zwei Zuständen (Hp0, Hp1) und zwei Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGML2</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin rot</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin grün</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGML2(0x3b),

	/**
	 * Konfiguriere Einfahrsignal mit drei Zuständen (Hp0, Hp1, Hp2) und drei Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>4</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGML3</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin rot</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin grün</td></tr>
	 * <tr><td>Data[3]</td><td>Schaltpin gelb</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGML3(0x3c),

	/**
	 * Konfiguriere Ausfahrsignal mit vier Zuständen (Hp0, Hp1, Hp2, Hp0/Sh1) und fünf Anschlüssen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>Gerätenummer</td></tr>
	 * <tr><td>Länge:</td><td>6</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGML4</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Schaltpin rot links</td></tr>
	 * <tr><td>Data[2]</td><td>Schaltpin rot rechts</td></tr>
	 * <tr><td>Data[3]</td><td>Schaltpin weiß</td></tr>
	 * <tr><td>Data[4]</td><td>Schaltpin grün</td></tr>
	 * <tr><td>Data[5]</td><td>Schaltpin gelb</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGML4(0x3d),
	
	/**
	 * Beende Konfigurationsmodus und führe RESET aus.
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>CFGEND</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_RESET_PENDING}
	 * <lI>{@link MsgCode#MSG_NOT_IN_CONFIG_MODE}
	 * </ul>
	 * </p>
	 */
	CFGEND(0x42),

	/**
	 * Konfiguration abfragen (nicht implementiert).
	 * 
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_UNKNOWN_CMD}
	 * </ul>
	 * </p>
	 */
	GETCFG(0x46),

	/**
	 * Konfiguration abfragen (nicht implementiert).
	 * 
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_UNKNOWN_CMD}
	 * </ul>
	 * </p>
	 */
	GETDVC(0x47),

	/**
	 * Firmware-Update auslösen (funktioniert nur im Bootloader).
	 * 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>5</td></tr>
	 * <tr><td>Data[0]</td><td><pre>FLASH_REQ</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Jumperstellung</td></tr>
	 * <tr><td>Data[2]</td><td>Signaturbyte 1 der MPU</td></tr>
	 * <tr><td>Data[3]</td><td>Signaturbyte 2 der MPU</td></tr>
	 * <tr><td>Data[4]</td><td>Signaturbyte 3 der MPU</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Antwort im Betriebsmodus:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_IGNORED}
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Mögliche Antworten im Bootloader:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_HARDWARE_MISMATCH}
	 * </ul>
	 * </p>
	 */
	FLASH_REQ(0x48),
	
	/**
	 * Programmdatentelegramm (nur im Updatemodus des Bootloaders).
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>6 oder 8</td></tr>
	 * <tr><td>Data[0]</td><td><pre>FLASH_DATA</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Adresse, Low Byte</td></tr>
	 * <tr><td>Data[2]</td><td>Adresse, Mid Byte</td></tr>
	 * <tr><td>Data[3]</td><td>Adresse, High Byte</td></tr>
	 * <tr><td>Data[4]</td><td>Programmbyte</td></tr>
	 * <tr><td>Data[5]</td><td>Programmbyte</td></tr>
	 * <tr><td>Data[6]</td><td>Programmbyte (bei Frame Länge 8)</td></tr>
	 * <tr><td>Data[7]</td><td>Programmbyte (bei Frame Länge 8)</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Antwort im Betriebsmodus:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_IGNORED}
	 * </ul>
	 * </p>
	 * <p>
	 * Antwort im Bootloader:<br>
	 * <ul>
	 * <lI>Keine!
	 * </ul>
	 * </p>
	 */
	FLASH_DATA (0x49),
	
	/**
	 * Prüfsumme des Firmwareupdates (nur im Updatemodus des Bootloaders).
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>2</td></tr>
	 * <tr><td>Data[0]</td><td><pre>FLASH_CHECK</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Prüfsumme</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Antwort im Betriebsmodus:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_IGNORED}
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Mögliche Antworten im Bootloader:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_CHECKSUM_ERROR}
	 * <lI>{@link MsgCode#MSG_RESET_PENDING}
	 * <lI>{@link MsgCode#MSG_BOOTED}
	 * <lI>{@link MsgCode#MSG_INFO}
	 * </ul>
	 * </p>
	 */
	FLASH_CHECK(0x4a),

	/**
	 * Belegung der Sende- und Empfangspuffer abfragen.
	 * 
	 * <p>
	 * <table>
	 * <tr><th align="left" colspan="2">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>QRYBUF</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Als Antwort kommt ein Frame mit dem Füllzustand der Sende- und Empfangspuffer.
	 * </p>
	 * <p>
	 * <table>
	 * <tr><th align="left" colspan="2">Aufteilung Füllzustand:</th></tr>
	 * <tr><th align="left">Data[6]</th><th align="left">Data[7]</th></tr>
	 * <tr><td>Füllzustand Empfangspuffer</td><td>Füllzustand Sendepuffer</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * </ul>
	 * </p>
	 */
	QRYBUF(0x4b),
	
	/**
	 * Fehlerzähler abfragen.
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>QRYERR</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Als Antwort kommen drei Frames mit dem Fehlerzustand. Die Fehlerzustand besteht aus drei Bytes. Für
	 * jedes Byte wird ein Frame gesendet. Das siebte Datenbyte bestimmt den Index des Fehlerzustandes. Das
	 * achte Datenbyte ist das Zustandsbyte.
	 * </p>
	 * <p>
	 * <table>
	 * <tr><th align="left" colspan="2">Aufteilung Fehlerzustand:</th></tr>
	 * <tr><th align="left">Data[6]</th><th align="left">Data[7]</th></tr>
	 * <tr><td>0</td><td>Error Flags</td></tr>
	 * <tr><td>1</td><td>Zähler für Empfangsfehler</td></tr>
	 * <tr><td>2</td><td>Zähler für Sendefehler</td></tr>
	 * </table>
	 * </p>
	 * <p>  
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_INFO}
	 * </ul>
	 * </p>
	 */
	QRYERR(0x4c),
	
	/**
	 * Versionsnummer der Firmware abfragen.
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>GETVER</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Als Antwort kommen drei Frames mit der Versionsnummer. Die Versionsnummer besteht aus drei Bytes. Für
	 * jedes Byte wird ein Frame gesendet. Das siebte Datenbyte bestimmt die Nummer des Versionsbytes. Das
	 * achte Datenbyte ist das Versionsbyte.
	 * </p>
	 * <p>
	 * <table>
	 * <tr><th align="left" colspan="2">Aufteilung Versionsnummer:</th></tr>
	 * <tr><th align="left">Data[6]</th><th align="left">Data[7]</th></tr>
	 * <tr><td>0</td><td>Major Version</td></tr>
	 * <tr><td>1</td><td>Minor Version, Low Byte</td></tr>
	 * <tr><td>2</td><td>Minor Version, High Byte</td></tr>
	 * </table>
	 * </p>
	 * <p>  
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_INFO}
	 * </ul>
	 * </p>
	 */
	GETVER(0x4d),

	/**
	 * Kommunikationstest.
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>PING</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * </ul>
	 * </p>
	 */
	PING(0x44),
	
	/**
	 * Reset auslösen.
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>1</td></tr>
	 * <tr><td>Data[0]</td><td><pre>RESET</pre></td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_RESET_PENDING}
	 * <lI>{@link MsgCode#MSG_BOOTED}
	 * <lI>{@link MsgCode#MSG_INFO}
	 * </ul>
	 * </p>
	 */
	RESET(0x45),

	/**
	 * Setzen einer neuen Mikrocontroller-ID. Ist diese tatsächlich anders, wird die
	 * Konfiguration gelöscht und danach ein RESET ausgelöst. 
	 * <p>
	 * <table>
	 * <tr><th colspan="2" align="left">Aufbau CAN-Frame:</th></tr>
	 * <tr><td>Standard-ID:</td><td>Controller-ID</td></tr>
	 * <tr><td>Extended-ID:</td><td>nicht nötig</td></tr>
	 * <tr><td>Länge:</td><td>3</td></tr>
	 * <tr><td>Data[0]</td><td><pre>SET_ID</pre></td></tr>
	 * <tr><td>Data[1]</td><td>Neue Controller-ID, Low Byte</td></tr>
	 * <tr><td>Data[2]</td><td>Neue Controller-ID, High Byte</td></tr>
	 * </table>
	 * </p>
	 * <p>
	 * Mögliche Antworten:<br>
	 * <ul>
	 * <lI>{@link MsgCode#MSG_OK}
	 * <lI>{@link MsgCode#MSG_ID_NOT_CHANGED}
	 * <lI>{@link MsgCode#MSG_ID_CHANGE_DISABLED}
	 * <lI>{@link MsgCode#MSG_RESET_PENDING}
	 * <lI>{@link MsgCode#MSG_BOOTED}
	 * <lI>{@link MsgCode#MSG_INFO}
	 * </ul>
	 * </p>
	 */
	SET_ID(0x43);

	/**
	 * Die Kategorie der Weichenschaltkommandos.
	 */
	public final static int CAT_SWITCH = 0x00;

	/**
	 * Due Kategorie der Abschnittschaltkommandos.
	 */
	public final static int CAT_RAIL   = 0x10;

	/**
	 * Die Kategorie der Signalschaltkommandos.
	 */
	public final static int CAT_SIGNAL = 0x20;

	/**
	 * Die Kategorie der Konfigurationskommandos.
	 */
	public final static int CAT_CONFIG = 0x30;

	/**
	 * Die Kategorie der Schaltkommandos, die einen Extended CAN-Frame verlangen.
	 */
	public final static int CAT_EXT    = 0x40;

	/**
	 * Die Maske, die die Kategorien filtert.
	 */
	public final static int CAT_MASK   = 0x70;

	/**
	 * Dieses Bit ist ein Flag, um eine {@link MrwMessage}-Antwort auf ein Kommando zu kennzeichnen.
	 */
	private static final int MSG_RESULT    = 0x80;
	private static final int MSG_CODE_MASK = 0x7f;

	private final int cmd;
	private final static Map<Integer, Command> map = new HashMap<Integer, Command>();

	static
	{
		for(Command cmd :Command.values())
		{
			map.put(cmd.getCommand(), cmd);
		}
	}

	private Command(int code)
	{
		this.cmd = code;
	}
	
	/**
	 * Diese Methode gibt den Kommando-Code als Integer für eine {@link MrwMessage} zurück.
	 * @return Der Kommando-Code als Integer für eine {@link MrwMessage}.
	 */
	public int getCommand()
	{
		return cmd;
	}
	
	/**
	 * Diese Methode wandelt den Integer-Wert eines {@link MrwMessage}-Kommandos in eine Command-enum-Instanz um. 
	 * @param code Der Kommando-Code aus einer {@link MrwMessage}.
	 * @return Die umgewandelte Enum-Instanz.
	 * @throws CodeNotFoundException Wenn zum code keine Enum-Instanz gefunden wurde.
	 */
	public static Command getCommand(int code)
	{
		Command result = map.get(code & MSG_CODE_MASK);

		if (result == null)
		{
			throw new CodeNotFoundException(Command.class, code);
		}
		return result;
	}

	/**
	 * Diese Methode testet, ob der Integer-Wert zum Kommando-Code aus einer Rückgabe stammt.
	 * @param code Der zu testende Kommando-Code.
	 * @return Ob der Kommando-Code eine Rückmeldung ist.
	 * @see MrwMessage
	 */
	public static boolean isResult(int code)
	{
		return (code & MSG_RESULT) != 0;
	}
	
	/**
	 * Diese Methode macht aus einer Byte-Repräsentation eines Kommando-Codes ein Integer-Wert mit
	 * Rückmeldeflag.
	 * @param code Der Kommando-Code als Byte.
	 * @return Der Kommando-Code mit Rückmeldeflag als Integer.
	 */
	public static int makeResult(byte code)
	{
		return (code | MSG_RESULT) & 0xff;
	}

	/**
	 * Diese Methode macht aus einem Kommando-Code ein Integer-Wert mit Rückmeldeflag.
	 * @param cmd Der Kommando-Code.
	 * @return Der Kommando-Code mit Rückmeldeflag als Integer.
	 */
	public static int makeResult(Command cmd)
	{
		return cmd.getCommand() | MSG_RESULT;
	}
}
