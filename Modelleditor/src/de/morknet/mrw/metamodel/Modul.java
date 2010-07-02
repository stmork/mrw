/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Modul.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modul</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Modul#getNumber <em>Number</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Modul#getController <em>Controller</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModul()
 * @model abstract="true"
 * @generated
 */
public interface Modul extends EObject {
	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModul_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Modul#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

	/**
	 * Returns the value of the '<em><b>Controller</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Controller#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModul_Controller()
	 * @see de.morknet.mrw.metamodel.Controller#getModules
	 * @model opposite="modules" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Controller getController();

} // Modul
