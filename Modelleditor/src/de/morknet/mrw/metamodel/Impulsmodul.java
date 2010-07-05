/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Impulsmodul</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Impulsmodul#getMagnetartikel <em>Magnetartikel</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getImpulsmodul()
 * @model
 * @generated
 */
public interface Impulsmodul extends Modul {
	/**
	 * Returns the value of the '<em><b>Magnetartikel</b></em>' reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Magnetartikel}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Magnetartikel#getModul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Magnetartikel</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Magnetartikel</em>' reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getImpulsmodul_Magnetartikel()
	 * @see de.morknet.mrw.metamodel.Magnetartikel#getModul
	 * @model opposite="modul"
	 * @generated
	 */
	EList<Magnetartikel> getMagnetartikel();

} // Impulsmodul
