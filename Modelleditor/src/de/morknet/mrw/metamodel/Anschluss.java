/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anschluss</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Anschluss#getNumber <em>Number</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Anschluss#getLichtsignal <em>Lichtsignal</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Anschluss#getController <em>Controller</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getAnschluss()
 * @model
 * @generated
 */
public interface Anschluss extends EObject {
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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getAnschluss_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Anschluss#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

	/**
	 * Returns the value of the '<em><b>Lichtsignal</b></em>' reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Lichtsignal}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Lichtsignal#getAnschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lichtsignal</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lichtsignal</em>' reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getAnschluss_Lichtsignal()
	 * @see de.morknet.mrw.metamodel.Lichtsignal#getAnschluss
	 * @model opposite="anschluss"
	 * @generated
	 */
	EList<Lichtsignal> getLichtsignal();

	/**
	 * Returns the value of the '<em><b>Controller</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Controller#getAnschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getAnschluss_Controller()
	 * @see de.morknet.mrw.metamodel.Controller#getAnschluss
	 * @model opposite="anschluss" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Controller getController();

} // Anschluss
