/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beleuchtungsmodul</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Beleuchtungsmodul#getLampen <em>Lampen</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBeleuchtungsmodul()
 * @model
 * @generated
 */
public interface Beleuchtungsmodul extends Modul {
	/**
	 * Returns the value of the '<em><b>Lampen</b></em>' containment reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Lampe}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Lampe#getModul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lampen</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lampen</em>' containment reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getBeleuchtungsmodul_Lampen()
	 * @see de.morknet.mrw.metamodel.Lampe#getModul
	 * @model opposite="modul" containment="true" upper="8" changeable="false"
	 * @generated
	 */
	EList<Lampe> getLampen();

} // Beleuchtungsmodul
