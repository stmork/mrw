/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: MagnetartikelImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Impulsmodul;
import de.morknet.mrw.metamodel.Magnetartikel;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Magnetartikel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.MagnetartikelImpl#getSpulen <em>Spulen</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.MagnetartikelImpl#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MagnetartikelImpl extends BauelementImpl implements Magnetartikel {
	/**
	 * The default value of the '{@link #getSpulen() <em>Spulen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpulen()
	 * @generated
	 * @ordered
	 */
	protected static final int SPULEN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSpulen() <em>Spulen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpulen()
	 * @generated
	 * @ordered
	 */
	protected int spulen = SPULEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModul() <em>Modul</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModul()
	 * @generated
	 * @ordered
	 */
	protected Impulsmodul modul;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MagnetartikelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.MAGNETARTIKEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSpulen() {
		return spulen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpulen(int newSpulen) {
		int oldSpulen = spulen;
		spulen = newSpulen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.MAGNETARTIKEL__SPULEN, oldSpulen, spulen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Impulsmodul getModul() {
		if (modul != null && modul.eIsProxy()) {
			InternalEObject oldModul = (InternalEObject)modul;
			modul = (Impulsmodul)eResolveProxy(oldModul);
			if (modul != oldModul) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.MAGNETARTIKEL__MODUL, oldModul, modul));
			}
		}
		return modul;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Impulsmodul basicGetModul() {
		return modul;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModul(Impulsmodul newModul, NotificationChain msgs) {
		Impulsmodul oldModul = modul;
		modul = newModul;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.MAGNETARTIKEL__MODUL, oldModul, newModul);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModul(Impulsmodul newModul) {
		if (newModul != modul) {
			NotificationChain msgs = null;
			if (modul != null)
				msgs = ((InternalEObject)modul).eInverseRemove(this, ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL, Impulsmodul.class, msgs);
			if (newModul != null)
				msgs = ((InternalEObject)newModul).eInverseAdd(this, ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL, Impulsmodul.class, msgs);
			msgs = basicSetModul(newModul, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.MAGNETARTIKEL__MODUL, newModul, newModul));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				if (modul != null)
					msgs = ((InternalEObject)modul).eInverseRemove(this, ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL, Impulsmodul.class, msgs);
				return basicSetModul((Impulsmodul)otherEnd, msgs);
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
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				return basicSetModul(null, msgs);
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
			case ModelrailwayPackage.MAGNETARTIKEL__SPULEN:
				return getSpulen();
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				if (resolve) return getModul();
				return basicGetModul();
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
			case ModelrailwayPackage.MAGNETARTIKEL__SPULEN:
				setSpulen((Integer)newValue);
				return;
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				setModul((Impulsmodul)newValue);
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
			case ModelrailwayPackage.MAGNETARTIKEL__SPULEN:
				setSpulen(SPULEN_EDEFAULT);
				return;
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				setModul((Impulsmodul)null);
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
			case ModelrailwayPackage.MAGNETARTIKEL__SPULEN:
				return spulen != SPULEN_EDEFAULT;
			case ModelrailwayPackage.MAGNETARTIKEL__MODUL:
				return modul != null;
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
		result.append(" (spulen: ");
		result.append(spulen);
		result.append(')');
		return result.toString();
	}

} //MagnetartikelImpl
