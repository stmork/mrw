/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Gruppe.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gruppe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Gruppe#getModell <em>Modell</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gruppe#getAbschnitt <em>Abschnitt</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGruppe()
 * @model abstract="true"
 * @generated
 */
public interface Gruppe extends Element {
	/**
	 * Returns the value of the '<em><b>Modell</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Modell#getGruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modell</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modell</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGruppe_Modell()
	 * @see de.morknet.mrw.metamodel.Modell#getGruppe
	 * @model opposite="gruppe" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Modell getModell();

	/**
	 * Returns the value of the '<em><b>Abschnitt</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Gleisabschnitt}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getGruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abschnitt</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abschnitt</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGruppe_Abschnitt()
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getGruppe
	 * @model opposite="gruppe" containment="true"
	 * @generated
	 */
	EList<Gleisabschnitt> getAbschnitt();

} // Gruppe
