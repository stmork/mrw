/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lichtsignal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Lichtsignal#getAnschluss <em>Anschluss</em>}</li>
 * </ul>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLichtsignal()
 * @model abstract="true"
 * @generated
 */
public interface Lichtsignal extends Signal {

	/**
	 * Returns the value of the '<em><b>Anschluss</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Anschluss#getLichtsignale <em>Lichtsignale</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anschluss</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anschluss</em>' reference.
	 * @see #setAnschluss(Anschluss)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLichtsignal_Anschluss()
	 * @see de.morknet.mrw.metamodel.Anschluss#getLichtsignale
	 * @model opposite="lichtsignale" required="true"
	 * @generated
	 */
	Anschluss getAnschluss();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Lichtsignal#getAnschluss <em>Anschluss</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anschluss</em>' reference.
	 * @see #getAnschluss()
	 * @generated
	 */
	void setAnschluss(Anschluss value);

} // Lichtsignal
