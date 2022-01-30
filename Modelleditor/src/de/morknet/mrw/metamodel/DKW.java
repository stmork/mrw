/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DKW</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#isAdIstAbzweig <em>Ad Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#isBcIstAbzweig <em>Bc Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#getA <em>A</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#getC <em>C</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#getB <em>B</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.DKW#getD <em>D</em>}</li>
 * </ul>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW()
 * @model
 * @generated
 */
public interface DKW extends Verzweigung {
	/**
	 * Returns the value of the '<em><b>Ad Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ad Ist Abzweig</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ad Ist Abzweig</em>' attribute.
	 * @see #setAdIstAbzweig(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_AdIstAbzweig()
	 * @model
	 * @generated
	 */
	boolean isAdIstAbzweig();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#isAdIstAbzweig <em>Ad Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ad Ist Abzweig</em>' attribute.
	 * @see #isAdIstAbzweig()
	 * @generated
	 */
	void setAdIstAbzweig(boolean value);

	/**
	 * Returns the value of the '<em><b>Bc Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bc Ist Abzweig</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bc Ist Abzweig</em>' attribute.
	 * @see #setBcIstAbzweig(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_BcIstAbzweig()
	 * @model
	 * @generated
	 */
	boolean isBcIstAbzweig();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#isBcIstAbzweig <em>Bc Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bc Ist Abzweig</em>' attribute.
	 * @see #isBcIstAbzweig()
	 * @generated
	 */
	void setBcIstAbzweig(boolean value);

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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_A()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getA();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(Gleisteil value);

	/**
	 * Returns the value of the '<em><b>C</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>C</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>C</em>' reference.
	 * @see #setC(Gleisteil)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_C()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getC();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#getC <em>C</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C</em>' reference.
	 * @see #getC()
	 * @generated
	 */
	void setC(Gleisteil value);

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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_B()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getB();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(Gleisteil value);

	/**
	 * Returns the value of the '<em><b>D</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>D</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>D</em>' reference.
	 * @see #setD(Gleisteil)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getDKW_D()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getD();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.DKW#getD <em>D</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>D</em>' reference.
	 * @see #getD()
	 * @generated
	 */
	void setD(Gleisteil value);

} // DKW
