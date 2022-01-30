/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bauelement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Bauelement#getAbschnitt <em>Abschnitt</em>}</li>
 * </ul>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBauelement()
 * @model abstract="true"
 * @generated
 */
public interface Bauelement extends Element {
	/**
	 * Returns the value of the '<em><b>Abschnitt</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getBauelement <em>Bauelement</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abschnitt</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abschnitt</em>' container reference.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBauelement_Abschnitt()
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getBauelement
	 * @model opposite="bauelement" required="true" transient="false" changeable="false"
	 * @generated
	 */
	Gleisabschnitt getAbschnitt();

} // Bauelement
