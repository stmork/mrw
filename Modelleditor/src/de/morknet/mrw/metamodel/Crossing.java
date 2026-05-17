/**
 * Copyright (C) 2007-2026 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Crossing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Crossing#getAnschluss <em>Anschluss</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Crossing#getAbschnitte <em>Abschnitte</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getCrossing()
 * @model
 * @generated
 */
public interface Crossing extends Unit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (C) 2007-2026 committers of this modelrailway project. All rights reserved.";

	/**
	 * Returns the value of the '<em><b>Anschluss</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Anschluss#getCrossing <em>Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anschluss</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anschluss</em>' container reference.
	 * @see #setAnschluss(Anschluss)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getCrossing_Anschluss()
	 * @see de.morknet.mrw.metamodel.Anschluss#getCrossing
	 * @model opposite="crossing" required="true" transient="false"
	 * @generated
	 */
	Anschluss getAnschluss();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Crossing#getAnschluss <em>Anschluss</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anschluss</em>' container reference.
	 * @see #getAnschluss()
	 * @generated
	 */
	void setAnschluss(Anschluss value);

	/**
	 * Returns the value of the '<em><b>Abschnitte</b></em>' reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Gleisabschnitt}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getCrossing <em>Crossing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abschnitte</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abschnitte</em>' reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getCrossing_Abschnitte()
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getCrossing
	 * @model opposite="crossing" required="true"
	 * @generated
	 */
	EList<Gleisabschnitt> getAbschnitte();

} // Crossing
