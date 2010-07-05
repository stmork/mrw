/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Magnetartikel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Magnetartikel#getSpulen <em>Spulen</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Magnetartikel#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getMagnetartikel()
 * @model abstract="true"
 * @generated
 */
public interface Magnetartikel extends Bauelement {
	/**
	 * Returns the value of the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spulen</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spulen</em>' attribute.
	 * @see #setSpulen(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getMagnetartikel_Spulen()
	 * @model
	 * @generated
	 */
	int getSpulen();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Magnetartikel#getSpulen <em>Spulen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spulen</em>' attribute.
	 * @see #getSpulen()
	 * @generated
	 */
	void setSpulen(int value);

	/**
	 * Returns the value of the '<em><b>Modul</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Impulsmodul#getMagnetartikel <em>Magnetartikel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modul</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modul</em>' reference.
	 * @see #setModul(Impulsmodul)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getMagnetartikel_Modul()
	 * @see de.morknet.mrw.metamodel.Impulsmodul#getMagnetartikel
	 * @model opposite="magnetartikel" required="true"
	 * @generated
	 */
	Impulsmodul getModul();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Magnetartikel#getModul <em>Modul</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modul</em>' reference.
	 * @see #getModul()
	 * @generated
	 */
	void setModul(Impulsmodul value);

} // Magnetartikel
