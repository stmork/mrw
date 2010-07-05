/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verzweigung</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Verzweigung#isNeu <em>Neu</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getVerzweigung()
 * @model abstract="true"
 * @generated
 */
public interface Verzweigung extends Gleisteil, Magnetartikel, Unit {
	/**
	 * Returns the value of the '<em><b>Neu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Neu</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Neu</em>' attribute.
	 * @see #setNeu(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getVerzweigung_Neu()
	 * @model
	 * @generated
	 */
	boolean isNeu();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Verzweigung#isNeu <em>Neu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Neu</em>' attribute.
	 * @see #isNeu()
	 * @generated
	 */
	void setNeu(boolean value);

} // Verzweigung
