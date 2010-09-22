/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lampe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Lampe#getSchwellwert <em>Schwellwert</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Lampe#getTyp <em>Typ</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Lampe#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe()
 * @model
 * @generated
 */
public interface Lampe extends Element, Unit {
	/**
	 * Returns the value of the '<em><b>Schwellwert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schwellwert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schwellwert</em>' attribute.
	 * @see #setSchwellwert(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe_Schwellwert()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getSchwellwert();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Lampe#getSchwellwert <em>Schwellwert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schwellwert</em>' attribute.
	 * @see #getSchwellwert()
	 * @generated
	 */
	void setSchwellwert(int value);

	/**
	 * Returns the value of the '<em><b>Typ</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typ</em>' attribute.
	 * @see #setTyp(int)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe_Typ()
	 * @model required="true"
	 * @generated
	 */
	int getTyp();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Lampe#getTyp <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typ</em>' attribute.
	 * @see #getTyp()
	 * @generated
	 */
	void setTyp(int value);

	/**
	 * Returns the value of the '<em><b>Modul</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Beleuchtungsmodul#getLampe <em>Lampe</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modul</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modul</em>' container reference.
	 * @see #setModul(Beleuchtungsmodul)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe_Modul()
	 * @see de.morknet.mrw.metamodel.Beleuchtungsmodul#getLampe
	 * @model opposite="lampe" required="true" transient="false"
	 * @generated
	 */
	Beleuchtungsmodul getModul();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Lampe#getModul <em>Modul</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modul</em>' container reference.
	 * @see #getModul()
	 * @generated
	 */
	void setModul(Beleuchtungsmodul value);

} // Lampe
