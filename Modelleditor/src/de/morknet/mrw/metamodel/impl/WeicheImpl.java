/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Gleisteil;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Weiche;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Weiche</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#isCIstAbzweig <em>CIst Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#isBIstAbzweig <em>BIst Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#isBIstBevorzugt <em>BIst Bevorzugt</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#isCIstBevorzugt <em>CIst Bevorzugt</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#getB <em>B</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#getC <em>C</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.WeicheImpl#getA <em>A</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WeicheImpl extends VerzweigungImpl implements Weiche {
	/**
	 * The default value of the '{@link #isCIstAbzweig() <em>CIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CIST_ABZWEIG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCIstAbzweig() <em>CIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected boolean cIstAbzweig = CIST_ABZWEIG_EDEFAULT;

	/**
	 * The default value of the '{@link #isBIstAbzweig() <em>BIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BIST_ABZWEIG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBIstAbzweig() <em>BIst Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected boolean bIstAbzweig = BIST_ABZWEIG_EDEFAULT;

	/**
	 * The default value of the '{@link #isBIstBevorzugt() <em>BIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBIstBevorzugt()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BIST_BEVORZUGT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBIstBevorzugt() <em>BIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBIstBevorzugt()
	 * @generated
	 * @ordered
	 */
	protected boolean bIstBevorzugt = BIST_BEVORZUGT_EDEFAULT;

	/**
	 * The default value of the '{@link #isCIstBevorzugt() <em>CIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCIstBevorzugt()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CIST_BEVORZUGT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCIstBevorzugt() <em>CIst Bevorzugt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCIstBevorzugt()
	 * @generated
	 * @ordered
	 */
	protected boolean cIstBevorzugt = CIST_BEVORZUGT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getB() <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getB()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil b;

	/**
	 * The cached value of the '{@link #getC() <em>C</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getC()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil c;

	/**
	 * The cached value of the '{@link #getA() <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil a;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WeicheImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.WEICHE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCIstAbzweig() {
		return cIstAbzweig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCIstAbzweig(boolean newCIstAbzweig) {
		boolean oldCIstAbzweig = cIstAbzweig;
		cIstAbzweig = newCIstAbzweig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__CIST_ABZWEIG, oldCIstAbzweig, cIstAbzweig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBIstAbzweig() {
		return bIstAbzweig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBIstAbzweig(boolean newBIstAbzweig) {
		boolean oldBIstAbzweig = bIstAbzweig;
		bIstAbzweig = newBIstAbzweig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__BIST_ABZWEIG, oldBIstAbzweig, bIstAbzweig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBIstBevorzugt() {
		return bIstBevorzugt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBIstBevorzugt(boolean newBIstBevorzugt) {
		boolean oldBIstBevorzugt = bIstBevorzugt;
		bIstBevorzugt = newBIstBevorzugt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__BIST_BEVORZUGT, oldBIstBevorzugt, bIstBevorzugt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCIstBevorzugt() {
		return cIstBevorzugt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCIstBevorzugt(boolean newCIstBevorzugt) {
		boolean oldCIstBevorzugt = cIstBevorzugt;
		cIstBevorzugt = newCIstBevorzugt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__CIST_BEVORZUGT, oldCIstBevorzugt, cIstBevorzugt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil getB() {
		if (b != null && b.eIsProxy()) {
			InternalEObject oldB = (InternalEObject)b;
			b = (Gleisteil)eResolveProxy(oldB);
			if (b != oldB) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.WEICHE__B, oldB, b));
			}
		}
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil basicGetB() {
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setB(Gleisteil newB) {
		Gleisteil oldB = b;
		b = newB;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__B, oldB, b));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil getC() {
		if (c != null && c.eIsProxy()) {
			InternalEObject oldC = (InternalEObject)c;
			c = (Gleisteil)eResolveProxy(oldC);
			if (c != oldC) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.WEICHE__C, oldC, c));
			}
		}
		return c;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil basicGetC() {
		return c;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setC(Gleisteil newC) {
		Gleisteil oldC = c;
		c = newC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__C, oldC, c));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil getA() {
		if (a != null && a.eIsProxy()) {
			InternalEObject oldA = (InternalEObject)a;
			a = (Gleisteil)eResolveProxy(oldA);
			if (a != oldA) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.WEICHE__A, oldA, a));
			}
		}
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil basicGetA() {
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA(Gleisteil newA) {
		Gleisteil oldA = a;
		a = newA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.WEICHE__A, oldA, a));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.WEICHE__CIST_ABZWEIG:
				return isCIstAbzweig();
			case ModelrailwayPackage.WEICHE__BIST_ABZWEIG:
				return isBIstAbzweig();
			case ModelrailwayPackage.WEICHE__BIST_BEVORZUGT:
				return isBIstBevorzugt();
			case ModelrailwayPackage.WEICHE__CIST_BEVORZUGT:
				return isCIstBevorzugt();
			case ModelrailwayPackage.WEICHE__B:
				if (resolve) return getB();
				return basicGetB();
			case ModelrailwayPackage.WEICHE__C:
				if (resolve) return getC();
				return basicGetC();
			case ModelrailwayPackage.WEICHE__A:
				if (resolve) return getA();
				return basicGetA();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelrailwayPackage.WEICHE__CIST_ABZWEIG:
				setCIstAbzweig((Boolean)newValue);
				return;
			case ModelrailwayPackage.WEICHE__BIST_ABZWEIG:
				setBIstAbzweig((Boolean)newValue);
				return;
			case ModelrailwayPackage.WEICHE__BIST_BEVORZUGT:
				setBIstBevorzugt((Boolean)newValue);
				return;
			case ModelrailwayPackage.WEICHE__CIST_BEVORZUGT:
				setCIstBevorzugt((Boolean)newValue);
				return;
			case ModelrailwayPackage.WEICHE__B:
				setB((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.WEICHE__C:
				setC((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.WEICHE__A:
				setA((Gleisteil)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelrailwayPackage.WEICHE__CIST_ABZWEIG:
				setCIstAbzweig(CIST_ABZWEIG_EDEFAULT);
				return;
			case ModelrailwayPackage.WEICHE__BIST_ABZWEIG:
				setBIstAbzweig(BIST_ABZWEIG_EDEFAULT);
				return;
			case ModelrailwayPackage.WEICHE__BIST_BEVORZUGT:
				setBIstBevorzugt(BIST_BEVORZUGT_EDEFAULT);
				return;
			case ModelrailwayPackage.WEICHE__CIST_BEVORZUGT:
				setCIstBevorzugt(CIST_BEVORZUGT_EDEFAULT);
				return;
			case ModelrailwayPackage.WEICHE__B:
				setB((Gleisteil)null);
				return;
			case ModelrailwayPackage.WEICHE__C:
				setC((Gleisteil)null);
				return;
			case ModelrailwayPackage.WEICHE__A:
				setA((Gleisteil)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelrailwayPackage.WEICHE__CIST_ABZWEIG:
				return cIstAbzweig != CIST_ABZWEIG_EDEFAULT;
			case ModelrailwayPackage.WEICHE__BIST_ABZWEIG:
				return bIstAbzweig != BIST_ABZWEIG_EDEFAULT;
			case ModelrailwayPackage.WEICHE__BIST_BEVORZUGT:
				return bIstBevorzugt != BIST_BEVORZUGT_EDEFAULT;
			case ModelrailwayPackage.WEICHE__CIST_BEVORZUGT:
				return cIstBevorzugt != CIST_BEVORZUGT_EDEFAULT;
			case ModelrailwayPackage.WEICHE__B:
				return b != null;
			case ModelrailwayPackage.WEICHE__C:
				return c != null;
			case ModelrailwayPackage.WEICHE__A:
				return a != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (cIstAbzweig: ");
		result.append(cIstAbzweig);
		result.append(", bIstAbzweig: ");
		result.append(bIstAbzweig);
		result.append(", bIstBevorzugt: ");
		result.append(bIstBevorzugt);
		result.append(", cIstBevorzugt: ");
		result.append(cIstBevorzugt);
		result.append(')');
		return result.toString();
	}

} //WeicheImpl
