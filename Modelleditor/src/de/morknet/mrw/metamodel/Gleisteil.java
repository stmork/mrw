/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Gleisteil.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gleisteil</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Gleisteil#getTeile <em>Teile</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleisteil#isAInZaehlrichtung <em>AIn Zaehlrichtung</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisteil()
 * @model abstract="true"
 * @generated
 */
public interface Gleisteil extends Bauelement {
	/**
	 * Returns the value of the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Teile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teile</em>' attribute.
	 * @see #setTeile(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisteil_Teile()
	 * @model
	 * @generated
	 */
	int getTeile();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleisteil#getTeile <em>Teile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Teile</em>' attribute.
	 * @see #getTeile()
	 * @generated
	 */
	void setTeile(int value);

	/**
	 * Returns the value of the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AIn Zaehlrichtung</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AIn Zaehlrichtung</em>' attribute.
	 * @see #setAInZaehlrichtung(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisteil_AInZaehlrichtung()
	 * @model
	 * @generated
	 */
	boolean isAInZaehlrichtung();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleisteil#isAInZaehlrichtung <em>AIn Zaehlrichtung</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AIn Zaehlrichtung</em>' attribute.
	 * @see #isAInZaehlrichtung()
	 * @generated
	 */
	void setAInZaehlrichtung(boolean value);

} // Gleisteil
