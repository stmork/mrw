/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Gleismodul.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gleismodul</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Gleismodul#getAbschnitte <em>Abschnitte</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleismodul()
 * @model
 * @generated
 */
public interface Gleismodul extends Modul {
	/**
	 * Returns the value of the '<em><b>Abschnitte</b></em>' reference list.
	 * The list contents are of type {@link de.morknet.mrw.metamodel.Gleisabschnitt}.
	 * It is bidirectional and its opposite is '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getModul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abschnitte</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abschnitte</em>' reference list.
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleismodul_Abschnitte()
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getModul
	 * @model opposite="modul"
	 * @generated
	 */
	EList<Gleisabschnitt> getAbschnitte();

} // Gleismodul
