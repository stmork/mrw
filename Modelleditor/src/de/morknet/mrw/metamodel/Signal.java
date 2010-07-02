/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Signal.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Signal#isInZaehlrichtung <em>In Zaehlrichtung</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getSignal()
 * @model abstract="true"
 * @generated
 */
public interface Signal extends Bauelement, Unit {
	/**
	 * Returns the value of the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Zaehlrichtung</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Zaehlrichtung</em>' attribute.
	 * @see #setInZaehlrichtung(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getSignal_InZaehlrichtung()
	 * @model
	 * @generated
	 */
	boolean isInZaehlrichtung();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Signal#isInZaehlrichtung <em>In Zaehlrichtung</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Zaehlrichtung</em>' attribute.
	 * @see #isInZaehlrichtung()
	 * @generated
	 */
	void setInZaehlrichtung(boolean value);

} // Signal
