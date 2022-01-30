/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Lichtsignal;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lichtsignal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LichtsignalImpl#getAnschluss <em>Anschluss</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LichtsignalImpl extends SignalImpl implements Lichtsignal {
	/**
	 * The cached value of the '{@link #getAnschluss() <em>Anschluss</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnschluss()
	 * @generated
	 * @ordered
	 */
	protected Anschluss anschluss;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LichtsignalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.LICHTSIGNAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Anschluss getAnschluss() {
		if (anschluss != null && anschluss.eIsProxy()) {
			InternalEObject oldAnschluss = (InternalEObject)anschluss;
			anschluss = (Anschluss)eResolveProxy(oldAnschluss);
			if (anschluss != oldAnschluss) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS, oldAnschluss, anschluss));
			}
		}
		return anschluss;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anschluss basicGetAnschluss() {
		return anschluss;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnschluss(Anschluss newAnschluss, NotificationChain msgs) {
		Anschluss oldAnschluss = anschluss;
		anschluss = newAnschluss;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS, oldAnschluss, newAnschluss);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAnschluss(Anschluss newAnschluss) {
		if (newAnschluss != anschluss) {
			NotificationChain msgs = null;
			if (anschluss != null)
				msgs = ((InternalEObject)anschluss).eInverseRemove(this, ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE, Anschluss.class, msgs);
			if (newAnschluss != null)
				msgs = ((InternalEObject)newAnschluss).eInverseAdd(this, ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE, Anschluss.class, msgs);
			msgs = basicSetAnschluss(newAnschluss, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS, newAnschluss, newAnschluss));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				if (anschluss != null)
					msgs = ((InternalEObject)anschluss).eInverseRemove(this, ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE, Anschluss.class, msgs);
				return basicSetAnschluss((Anschluss)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				return basicSetAnschluss(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				if (resolve) return getAnschluss();
				return basicGetAnschluss();
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
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				setAnschluss((Anschluss)newValue);
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
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				setAnschluss((Anschluss)null);
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
			case ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS:
				return anschluss != null;
		}
		return super.eIsSet(featureID);
	}

} //LichtsignalImpl
