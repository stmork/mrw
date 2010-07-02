/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Modell.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Modell#getController <em>Controller</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Modell#getGruppe <em>Gruppe</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModell()
 * @model
 * @generated
 */
public interface Modell extends Element {
	/**
	 * Returns the value of the '<em><b>Controller</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Controller}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Controller#getModell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModell_Controller()
	 * @see de.morknet.mrw.metamodel.Controller#getModell
	 * @model opposite="modell" containment="true"
	 * @generated
	 */
	EList<Controller> getController();

	/**
	 * Returns the value of the '<em><b>Gruppe</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Gruppe}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gruppe#getModell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gruppe</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gruppe</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getModell_Gruppe()
	 * @see de.morknet.mrw.metamodel.Gruppe#getModell
	 * @model opposite="modell" containment="true"
	 * @generated
	 */
	EList<Gruppe> getGruppe();

} // Modell
