/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Licht</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Licht#getAnschluss <em>Anschluss</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLicht()
 * @model
 * @generated
 */
public interface Licht extends Beleuchtungsmittel {
	/**
	 * Returns the value of the '<em><b>Anschluss</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Anschluss#getLichter <em>Lichter</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anschluss</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anschluss</em>' container reference.
	 * @see #setAnschluss(Anschluss)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLicht_Anschluss()
	 * @see de.morknet.mrw.metamodel.Anschluss#getLichter
	 * @model opposite="lichter" required="true" transient="false"
	 * @generated
	 */
	Anschluss getAnschluss();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Licht#getAnschluss <em>Anschluss</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anschluss</em>' container reference.
	 * @see #getAnschluss()
	 * @generated
	 */
	void setAnschluss(Anschluss value);

} // Licht
