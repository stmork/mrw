/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Beleuchtungsmittel;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Unit;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Beleuchtungsmittel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.BeleuchtungsmittelImpl#getUnit_no <em>Unit no</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.BeleuchtungsmittelImpl#getSchwellwert <em>Schwellwert</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BeleuchtungsmittelImpl extends ElementImpl implements Beleuchtungsmittel {
	/**
	 * The default value of the '{@link #getUnit_no() <em>Unit no</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit_no()
	 * @generated
	 * @ordered
	 */
	protected static final int UNIT_NO_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUnit_no() <em>Unit no</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit_no()
	 * @generated
	 * @ordered
	 */
	protected int unit_no = UNIT_NO_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchwellwert() <em>Schwellwert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchwellwert()
	 * @generated
	 * @ordered
	 */
	protected static final int SCHWELLWERT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSchwellwert() <em>Schwellwert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchwellwert()
	 * @generated
	 * @ordered
	 */
	protected int schwellwert = SCHWELLWERT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BeleuchtungsmittelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.BELEUCHTUNGSMITTEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUnit_no() {
		return unit_no;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit_no(int newUnit_no) {
		int oldUnit_no = unit_no;
		unit_no = newUnit_no;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO, oldUnit_no, unit_no));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSchwellwert() {
		return schwellwert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchwellwert(int newSchwellwert) {
		int oldSchwellwert = schwellwert;
		schwellwert = newSchwellwert;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.BELEUCHTUNGSMITTEL__SCHWELLWERT, oldSchwellwert, schwellwert));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO:
				return getUnit_no();
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__SCHWELLWERT:
				return getSchwellwert();
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
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO:
				setUnit_no((Integer)newValue);
				return;
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__SCHWELLWERT:
				setSchwellwert((Integer)newValue);
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
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO:
				setUnit_no(UNIT_NO_EDEFAULT);
				return;
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__SCHWELLWERT:
				setSchwellwert(SCHWELLWERT_EDEFAULT);
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
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO:
				return unit_no != UNIT_NO_EDEFAULT;
			case ModelrailwayPackage.BELEUCHTUNGSMITTEL__SCHWELLWERT:
				return schwellwert != SCHWELLWERT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Unit.class) {
			switch (derivedFeatureID) {
				case ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO: return ModelrailwayPackage.UNIT__UNIT_NO;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Unit.class) {
			switch (baseFeatureID) {
				case ModelrailwayPackage.UNIT__UNIT_NO: return ModelrailwayPackage.BELEUCHTUNGSMITTEL__UNIT_NO;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (unit_no: ");
		result.append(unit_no);
		result.append(", schwellwert: ");
		result.append(schwellwert);
		result.append(')');
		return result.toString();
	}

} //BeleuchtungsmittelImpl
