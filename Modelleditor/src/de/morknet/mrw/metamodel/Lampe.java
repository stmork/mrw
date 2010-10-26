/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lampe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Lampe#getTyp <em>Typ</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Lampe#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe()
 * @model
 * @generated
 */
public interface Lampe extends Beleuchtungsmittel {
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
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Beleuchtungsmodul#getLampen <em>Lampen</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modul</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modul</em>' container reference.
	 * @see #setModul(Beleuchtungsmodul)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getLampe_Modul()
	 * @see de.morknet.mrw.metamodel.Beleuchtungsmodul#getLampen
	 * @model opposite="lampen" required="true" transient="false"
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
