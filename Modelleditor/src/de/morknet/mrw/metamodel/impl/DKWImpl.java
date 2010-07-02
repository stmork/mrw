/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: DKWImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.DKW;
import de.morknet.mrw.metamodel.Gleisteil;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DKW</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#isAdIstAbzweig <em>Ad Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#isBcIstAbzweig <em>Bc Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#getA <em>A</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#getC <em>C</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#getB <em>B</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.DKWImpl#getD <em>D</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DKWImpl extends VerzweigungImpl implements DKW {
	/**
	 * The default value of the '{@link #isAdIstAbzweig() <em>Ad Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAdIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AD_IST_ABZWEIG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAdIstAbzweig() <em>Ad Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAdIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected boolean adIstAbzweig = AD_IST_ABZWEIG_EDEFAULT;

	/**
	 * The default value of the '{@link #isBcIstAbzweig() <em>Bc Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBcIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BC_IST_ABZWEIG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBcIstAbzweig() <em>Bc Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBcIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected boolean bcIstAbzweig = BC_IST_ABZWEIG_EDEFAULT;

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
	 * The cached value of the '{@link #getC() <em>C</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getC()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil c;

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
	 * The cached value of the '{@link #getD() <em>D</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getD()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil d;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DKWImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.DKW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAdIstAbzweig() {
		return adIstAbzweig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdIstAbzweig(boolean newAdIstAbzweig) {
		boolean oldAdIstAbzweig = adIstAbzweig;
		adIstAbzweig = newAdIstAbzweig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__AD_IST_ABZWEIG, oldAdIstAbzweig, adIstAbzweig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBcIstAbzweig() {
		return bcIstAbzweig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBcIstAbzweig(boolean newBcIstAbzweig) {
		boolean oldBcIstAbzweig = bcIstAbzweig;
		bcIstAbzweig = newBcIstAbzweig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__BC_IST_ABZWEIG, oldBcIstAbzweig, bcIstAbzweig));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.DKW__A, oldA, a));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__A, oldA, a));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.DKW__C, oldC, c));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__C, oldC, c));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.DKW__B, oldB, b));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__B, oldB, b));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil getD() {
		if (d != null && d.eIsProxy()) {
			InternalEObject oldD = (InternalEObject)d;
			d = (Gleisteil)eResolveProxy(oldD);
			if (d != oldD) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.DKW__D, oldD, d));
			}
		}
		return d;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisteil basicGetD() {
		return d;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setD(Gleisteil newD) {
		Gleisteil oldD = d;
		d = newD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.DKW__D, oldD, d));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.DKW__AD_IST_ABZWEIG:
				return isAdIstAbzweig();
			case ModelrailwayPackage.DKW__BC_IST_ABZWEIG:
				return isBcIstAbzweig();
			case ModelrailwayPackage.DKW__A:
				if (resolve) return getA();
				return basicGetA();
			case ModelrailwayPackage.DKW__C:
				if (resolve) return getC();
				return basicGetC();
			case ModelrailwayPackage.DKW__B:
				if (resolve) return getB();
				return basicGetB();
			case ModelrailwayPackage.DKW__D:
				if (resolve) return getD();
				return basicGetD();
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
			case ModelrailwayPackage.DKW__AD_IST_ABZWEIG:
				setAdIstAbzweig((Boolean)newValue);
				return;
			case ModelrailwayPackage.DKW__BC_IST_ABZWEIG:
				setBcIstAbzweig((Boolean)newValue);
				return;
			case ModelrailwayPackage.DKW__A:
				setA((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.DKW__C:
				setC((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.DKW__B:
				setB((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.DKW__D:
				setD((Gleisteil)newValue);
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
			case ModelrailwayPackage.DKW__AD_IST_ABZWEIG:
				setAdIstAbzweig(AD_IST_ABZWEIG_EDEFAULT);
				return;
			case ModelrailwayPackage.DKW__BC_IST_ABZWEIG:
				setBcIstAbzweig(BC_IST_ABZWEIG_EDEFAULT);
				return;
			case ModelrailwayPackage.DKW__A:
				setA((Gleisteil)null);
				return;
			case ModelrailwayPackage.DKW__C:
				setC((Gleisteil)null);
				return;
			case ModelrailwayPackage.DKW__B:
				setB((Gleisteil)null);
				return;
			case ModelrailwayPackage.DKW__D:
				setD((Gleisteil)null);
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
			case ModelrailwayPackage.DKW__AD_IST_ABZWEIG:
				return adIstAbzweig != AD_IST_ABZWEIG_EDEFAULT;
			case ModelrailwayPackage.DKW__BC_IST_ABZWEIG:
				return bcIstAbzweig != BC_IST_ABZWEIG_EDEFAULT;
			case ModelrailwayPackage.DKW__A:
				return a != null;
			case ModelrailwayPackage.DKW__C:
				return c != null;
			case ModelrailwayPackage.DKW__B:
				return b != null;
			case ModelrailwayPackage.DKW__D:
				return d != null;
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
		result.append(" (adIstAbzweig: ");
		result.append(adIstAbzweig);
		result.append(", bcIstAbzweig: ");
		result.append(bcIstAbzweig);
		result.append(')');
		return result.toString();
	}

} //DKWImpl
