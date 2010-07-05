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
 * A representation of the model object '<em><b>Controller</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Controller#getId <em>Id</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Controller#getAnschluss <em>Anschluss</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Controller#getModules <em>Modules</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Controller#getModell <em>Modell</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getController()
 * @model
 * @generated
 */
public interface Controller extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getController_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Controller#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Anschluss</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Anschluss}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Anschluss#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anschluss</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anschluss</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getController_Anschluss()
	 * @see de.morknet.mrw.metamodel.Anschluss#getController
	 * @model opposite="controller" containment="true"
	 * @generated
	 */
	EList<Anschluss> getAnschluss();

	/**
	 * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Modul}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Modul#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modules</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getController_Modules()
	 * @see de.morknet.mrw.metamodel.Modul#getController
	 * @model opposite="controller" containment="true"
	 * @generated
	 */
	EList<Modul> getModules();

	/**
	 * Returns the value of the '<em><b>Modell</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Modell#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modell</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modell</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getController_Modell()
	 * @see de.morknet.mrw.metamodel.Modell#getController
	 * @model opposite="controller" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Modell getModell();

} // Controller
