/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
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
 *   <li>{@link de.morknet.mrw.metamodel.Modul#getNummer <em>Nummer</em>}</li>
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
	 * Returns the value of the '<em><b>Nummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nummer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nummer</em>' attribute.
	 * @see #setNummer(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModul_Nummer()
	 * @model
	 * @generated
	 */
	int getNummer();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Modul#getNummer <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nummer</em>' attribute.
	 * @see #getNummer()
	 * @generated
	 */
	void setNummer(int value);

	/**
	 * Returns the value of the '<em><b>Controller</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Controller#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModul_Controller()
	 * @see de.morknet.mrw.metamodel.Controller#getModule
	 * @model opposite="module" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Controller getController();

} // Modul
