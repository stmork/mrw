/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Unit#getUnit_no <em>Unit no</em>}</li>
 * </ul>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getUnit()
 * @model abstract="true"
 * @generated
 */
public interface Unit extends EObject {
	/**
	 * Returns the value of the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit no</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit no</em>' attribute.
	 * @see #setUnit_no(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getUnit_Unit_no()
	 * @model
	 * @generated
	 */
	int getUnit_no();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Unit#getUnit_no <em>Unit no</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit no</em>' attribute.
	 * @see #getUnit_no()
	 * @generated
	 */
	void setUnit_no(int value);

} // Unit
