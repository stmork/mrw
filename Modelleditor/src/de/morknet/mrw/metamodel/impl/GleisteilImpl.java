/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Gleisteil;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gleisteil</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisteilImpl#getTeile <em>Teile</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisteilImpl#isAInZaehlrichtung <em>AIn Zaehlrichtung</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GleisteilImpl extends BauelementImpl implements Gleisteil {
	/**
	 * The default value of the '{@link #getTeile() <em>Teile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeile()
	 * @generated
	 * @ordered
	 */
	protected static final int TEILE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTeile() <em>Teile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeile()
	 * @generated
	 * @ordered
	 */
	protected int teile = TEILE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAInZaehlrichtung() <em>AIn Zaehlrichtung</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAInZaehlrichtung()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AIN_ZAEHLRICHTUNG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAInZaehlrichtung() <em>AIn Zaehlrichtung</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAInZaehlrichtung()
	 * @generated
	 * @ordered
	 */
	protected boolean aInZaehlrichtung = AIN_ZAEHLRICHTUNG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GleisteilImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.GLEISTEIL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTeile() {
		return teile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTeile(int newTeile) {
		int oldTeile = teile;
		teile = newTeile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEISTEIL__TEILE, oldTeile, teile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAInZaehlrichtung() {
		return aInZaehlrichtung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAInZaehlrichtung(boolean newAInZaehlrichtung) {
		boolean oldAInZaehlrichtung = aInZaehlrichtung;
		aInZaehlrichtung = newAInZaehlrichtung;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEISTEIL__AIN_ZAEHLRICHTUNG, oldAInZaehlrichtung, aInZaehlrichtung));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.GLEISTEIL__TEILE:
				return getTeile();
			case ModelrailwayPackage.GLEISTEIL__AIN_ZAEHLRICHTUNG:
				return isAInZaehlrichtung();
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
			case ModelrailwayPackage.GLEISTEIL__TEILE:
				setTeile((Integer)newValue);
				return;
			case ModelrailwayPackage.GLEISTEIL__AIN_ZAEHLRICHTUNG:
				setAInZaehlrichtung((Boolean)newValue);
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
			case ModelrailwayPackage.GLEISTEIL__TEILE:
				setTeile(TEILE_EDEFAULT);
				return;
			case ModelrailwayPackage.GLEISTEIL__AIN_ZAEHLRICHTUNG:
				setAInZaehlrichtung(AIN_ZAEHLRICHTUNG_EDEFAULT);
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
			case ModelrailwayPackage.GLEISTEIL__TEILE:
				return teile != TEILE_EDEFAULT;
			case ModelrailwayPackage.GLEISTEIL__AIN_ZAEHLRICHTUNG:
				return aInZaehlrichtung != AIN_ZAEHLRICHTUNG_EDEFAULT;
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
		result.append(" (teile: ");
		result.append(teile);
		result.append(", aInZaehlrichtung: ");
		result.append(aInZaehlrichtung);
		result.append(')');
		return result.toString();
	}

} //GleisteilImpl
