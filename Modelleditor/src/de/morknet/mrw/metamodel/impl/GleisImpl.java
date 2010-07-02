/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: GleisImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Gleis;
import de.morknet.mrw.metamodel.Gleisteil;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gleis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisImpl#isIstAbzweig <em>Ist Abzweig</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisImpl#isIstHauptgleis <em>Ist Hauptgleis</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisImpl#getA <em>A</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisImpl#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GleisImpl extends GleisteilImpl implements Gleis {
	/**
	 * The default value of the '{@link #isIstAbzweig() <em>Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IST_ABZWEIG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIstAbzweig() <em>Ist Abzweig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstAbzweig()
	 * @generated
	 * @ordered
	 */
	protected boolean istAbzweig = IST_ABZWEIG_EDEFAULT;

	/**
	 * The default value of the '{@link #isIstHauptgleis() <em>Ist Hauptgleis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstHauptgleis()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IST_HAUPTGLEIS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIstHauptgleis() <em>Ist Hauptgleis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstHauptgleis()
	 * @generated
	 * @ordered
	 */
	protected boolean istHauptgleis = IST_HAUPTGLEIS_EDEFAULT;

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
	 * The cached value of the '{@link #getB() <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getB()
	 * @generated
	 * @ordered
	 */
	protected Gleisteil b;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GleisImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.GLEIS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIstAbzweig() {
		return istAbzweig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIstAbzweig(boolean newIstAbzweig) {
		boolean oldIstAbzweig = istAbzweig;
		istAbzweig = newIstAbzweig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEIS__IST_ABZWEIG, oldIstAbzweig, istAbzweig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIstHauptgleis() {
		return istHauptgleis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIstHauptgleis(boolean newIstHauptgleis) {
		boolean oldIstHauptgleis = istHauptgleis;
		istHauptgleis = newIstHauptgleis;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEIS__IST_HAUPTGLEIS, oldIstHauptgleis, istHauptgleis));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.GLEIS__A, oldA, a));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEIS__A, oldA, a));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.GLEIS__B, oldB, b));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEIS__B, oldB, b));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.GLEIS__IST_ABZWEIG:
				return isIstAbzweig();
			case ModelrailwayPackage.GLEIS__IST_HAUPTGLEIS:
				return isIstHauptgleis();
			case ModelrailwayPackage.GLEIS__A:
				if (resolve) return getA();
				return basicGetA();
			case ModelrailwayPackage.GLEIS__B:
				if (resolve) return getB();
				return basicGetB();
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
			case ModelrailwayPackage.GLEIS__IST_ABZWEIG:
				setIstAbzweig((Boolean)newValue);
				return;
			case ModelrailwayPackage.GLEIS__IST_HAUPTGLEIS:
				setIstHauptgleis((Boolean)newValue);
				return;
			case ModelrailwayPackage.GLEIS__A:
				setA((Gleisteil)newValue);
				return;
			case ModelrailwayPackage.GLEIS__B:
				setB((Gleisteil)newValue);
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
			case ModelrailwayPackage.GLEIS__IST_ABZWEIG:
				setIstAbzweig(IST_ABZWEIG_EDEFAULT);
				return;
			case ModelrailwayPackage.GLEIS__IST_HAUPTGLEIS:
				setIstHauptgleis(IST_HAUPTGLEIS_EDEFAULT);
				return;
			case ModelrailwayPackage.GLEIS__A:
				setA((Gleisteil)null);
				return;
			case ModelrailwayPackage.GLEIS__B:
				setB((Gleisteil)null);
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
			case ModelrailwayPackage.GLEIS__IST_ABZWEIG:
				return istAbzweig != IST_ABZWEIG_EDEFAULT;
			case ModelrailwayPackage.GLEIS__IST_HAUPTGLEIS:
				return istHauptgleis != IST_HAUPTGLEIS_EDEFAULT;
			case ModelrailwayPackage.GLEIS__A:
				return a != null;
			case ModelrailwayPackage.GLEIS__B:
				return b != null;
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
		result.append(" (istAbzweig: ");
		result.append(istAbzweig);
		result.append(", istHauptgleis: ");
		result.append(istHauptgleis);
		result.append(')');
		return result.toString();
	}

} //GleisImpl
