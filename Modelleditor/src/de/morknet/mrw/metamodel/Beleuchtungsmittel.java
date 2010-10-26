/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beleuchtungsmittel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Beleuchtungsmittel#getSchwellwert <em>Schwellwert</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBeleuchtungsmittel()
 * @model abstract="true"
 * @generated
 */
public interface Beleuchtungsmittel extends Element, Unit {
	/**
	 * Returns the value of the '<em><b>Schwellwert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schwellwert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schwellwert</em>' attribute.
	 * @see #setSchwellwert(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBeleuchtungsmittel_Schwellwert()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getSchwellwert();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Beleuchtungsmittel#getSchwellwert <em>Schwellwert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schwellwert</em>' attribute.
	 * @see #getSchwellwert()
	 * @generated
	 */
	void setSchwellwert(int value);

} // Beleuchtungsmittel
