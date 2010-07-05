/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gleisabschnitt</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Gleisabschnitt#getGruppe <em>Gruppe</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleisabschnitt#getModul <em>Modul</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleisabschnitt#getBauelement <em>Bauelement</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisabschnitt()
 * @model
 * @generated
 */
public interface Gleisabschnitt extends Element, Unit {
	/**
	 * Returns the value of the '<em><b>Gruppe</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gruppe#getAbschnitt <em>Abschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gruppe</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gruppe</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisabschnitt_Gruppe()
	 * @see de.morknet.mrw.metamodel.Gruppe#getAbschnitt
	 * @model opposite="abschnitt" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Gruppe getGruppe();

	/**
	 * Returns the value of the '<em><b>Modul</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gleismodul#getAbschnitte <em>Abschnitte</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modul</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modul</em>' reference.
	 * @see #setModul(Gleismodul)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisabschnitt_Modul()
	 * @see de.morknet.mrw.metamodel.Gleismodul#getAbschnitte
	 * @model opposite="abschnitte" required="true"
	 * @generated
	 */
	Gleismodul getModul();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getModul <em>Modul</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modul</em>' reference.
	 * @see #getModul()
	 * @generated
	 */
	void setModul(Gleismodul value);

	/**
	 * Returns the value of the '<em><b>Bauelement</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Bauelement}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Bauelement#getAbschnitt <em>Abschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bauelement</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bauelement</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleisabschnitt_Bauelement()
	 * @see de.morknet.mrw.metamodel.Bauelement#getAbschnitt
	 * @model opposite="abschnitt" containment="true" required="true"
	 * @generated
	 */
	EList<Bauelement> getBauelement();

} // Gleisabschnitt
