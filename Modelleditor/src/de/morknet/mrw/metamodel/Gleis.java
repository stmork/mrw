/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Gleis.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gleis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Gleis#isIstAbzweig <em>Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleis#isIstHauptgleis <em>Ist Hauptgleis</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleis#getA <em>A</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Gleis#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleis()
 * @model
 * @generated
 */
public interface Gleis extends Gleisteil {
	/**
	 * Returns the value of the '<em><b>Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ist Abzweig</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ist Abzweig</em>' attribute.
	 * @see #setIstAbzweig(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleis_IstAbzweig()
	 * @model
	 * @generated
	 */
	boolean isIstAbzweig();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleis#isIstAbzweig <em>Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ist Abzweig</em>' attribute.
	 * @see #isIstAbzweig()
	 * @generated
	 */
	void setIstAbzweig(boolean value);

	/**
	 * Returns the value of the '<em><b>Ist Hauptgleis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ist Hauptgleis</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ist Hauptgleis</em>' attribute.
	 * @see #setIstHauptgleis(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleis_IstHauptgleis()
	 * @model
	 * @generated
	 */
	boolean isIstHauptgleis();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleis#isIstHauptgleis <em>Ist Hauptgleis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ist Hauptgleis</em>' attribute.
	 * @see #isIstHauptgleis()
	 * @generated
	 */
	void setIstHauptgleis(boolean value);

	/**
	 * Returns the value of the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>A</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>A</em>' reference.
	 * @see #setA(Gleisteil)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleis_A()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getA();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleis#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(Gleisteil value);

	/**
	 * Returns the value of the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>B</em>' reference.
	 * @see #setB(Gleisteil)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getGleis_B()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getB();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Gleis#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(Gleisteil value);

} // Gleis
