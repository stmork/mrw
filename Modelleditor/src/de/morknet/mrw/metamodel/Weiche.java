/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: Weiche.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Weiche</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#isCIstAbzweig <em>CIst Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#isBIstAbzweig <em>BIst Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#isBIstBevorzugt <em>BIst Bevorzugt</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#isCIstBevorzugt <em>CIst Bevorzugt</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#getB <em>B</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#getC <em>C</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.Weiche#getA <em>A</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche()
 * @model
 * @generated
 */
public interface Weiche extends Verzweigung {
	/**
	 * Returns the value of the '<em><b>CIst Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CIst Abzweig</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CIst Abzweig</em>' attribute.
	 * @see #setCIstAbzweig(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_CIstAbzweig()
	 * @model
	 * @generated
	 */
	boolean isCIstAbzweig();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#isCIstAbzweig <em>CIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CIst Abzweig</em>' attribute.
	 * @see #isCIstAbzweig()
	 * @generated
	 */
	void setCIstAbzweig(boolean value);

	/**
	 * Returns the value of the '<em><b>BIst Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BIst Abzweig</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BIst Abzweig</em>' attribute.
	 * @see #setBIstAbzweig(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_BIstAbzweig()
	 * @model
	 * @generated
	 */
	boolean isBIstAbzweig();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#isBIstAbzweig <em>BIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BIst Abzweig</em>' attribute.
	 * @see #isBIstAbzweig()
	 * @generated
	 */
	void setBIstAbzweig(boolean value);

	/**
	 * Returns the value of the '<em><b>BIst Bevorzugt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BIst Bevorzugt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BIst Bevorzugt</em>' attribute.
	 * @see #setBIstBevorzugt(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_BIstBevorzugt()
	 * @model
	 * @generated
	 */
	boolean isBIstBevorzugt();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#isBIstBevorzugt <em>BIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BIst Bevorzugt</em>' attribute.
	 * @see #isBIstBevorzugt()
	 * @generated
	 */
	void setBIstBevorzugt(boolean value);

	/**
	 * Returns the value of the '<em><b>CIst Bevorzugt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CIst Bevorzugt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CIst Bevorzugt</em>' attribute.
	 * @see #setCIstBevorzugt(boolean)
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_CIstBevorzugt()
	 * @model
	 * @generated
	 */
	boolean isCIstBevorzugt();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#isCIstBevorzugt <em>CIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CIst Bevorzugt</em>' attribute.
	 * @see #isCIstBevorzugt()
	 * @generated
	 */
	void setCIstBevorzugt(boolean value);

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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_B()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getB();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(Gleisteil value);

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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_C()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getC();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#getC <em>C</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>C</em>' reference.
	 * @see #getC()
	 * @generated
	 */
	void setC(Gleisteil value);

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
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#getWeiche_A()
	 * @model required="true"
	 * @generated
	 */
	Gleisteil getA();

	/**
	 * Sets the value of the '{@link de.morknet.mrw.metamodel.Weiche#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(Gleisteil value);

} // Weiche
