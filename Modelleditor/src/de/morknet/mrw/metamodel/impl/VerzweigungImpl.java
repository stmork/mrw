/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Impulsmodul;
import de.morknet.mrw.metamodel.Magnetartikel;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Unit;
import de.morknet.mrw.metamodel.Verzweigung;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Verzweigung</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl#getSpulen <em>Spulen</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl#getModul <em>Modul</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl#getUnit_no <em>Unit no</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl#isNeu <em>Neu</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class VerzweigungImpl extends GleisteilImpl implements Verzweigung {
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
	 * The default value of the '{@link #isNeu() <em>Neu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeu()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEU_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeu() <em>Neu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeu()
	 * @generated
	 * @ordered
	 */
	protected boolean neu = NEU_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VerzweigungImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.VERZWEIGUNG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.VERZWEIGUNG__SPULEN, oldSpulen, spulen));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.VERZWEIGUNG__MODUL, oldModul, modul));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.VERZWEIGUNG__MODUL, oldModul, newModul);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.VERZWEIGUNG__MODUL, newModul, newModul));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.VERZWEIGUNG__UNIT_NO, oldUnit_no, unit_no));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeu() {
		return neu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeu(boolean newNeu) {
		boolean oldNeu = neu;
		neu = newNeu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.VERZWEIGUNG__NEU, oldNeu, neu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
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
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
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
			case ModelrailwayPackage.VERZWEIGUNG__SPULEN:
				return getSpulen();
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
				if (resolve) return getModul();
				return basicGetModul();
			case ModelrailwayPackage.VERZWEIGUNG__UNIT_NO:
				return getUnit_no();
			case ModelrailwayPackage.VERZWEIGUNG__NEU:
				return isNeu();
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
			case ModelrailwayPackage.VERZWEIGUNG__SPULEN:
				setSpulen((Integer)newValue);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
				setModul((Impulsmodul)newValue);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__UNIT_NO:
				setUnit_no((Integer)newValue);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__NEU:
				setNeu((Boolean)newValue);
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
			case ModelrailwayPackage.VERZWEIGUNG__SPULEN:
				setSpulen(SPULEN_EDEFAULT);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
				setModul((Impulsmodul)null);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__UNIT_NO:
				setUnit_no(UNIT_NO_EDEFAULT);
				return;
			case ModelrailwayPackage.VERZWEIGUNG__NEU:
				setNeu(NEU_EDEFAULT);
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
			case ModelrailwayPackage.VERZWEIGUNG__SPULEN:
				return spulen != SPULEN_EDEFAULT;
			case ModelrailwayPackage.VERZWEIGUNG__MODUL:
				return modul != null;
			case ModelrailwayPackage.VERZWEIGUNG__UNIT_NO:
				return unit_no != UNIT_NO_EDEFAULT;
			case ModelrailwayPackage.VERZWEIGUNG__NEU:
				return neu != NEU_EDEFAULT;
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
		if (baseClass == Magnetartikel.class) {
			switch (derivedFeatureID) {
				case ModelrailwayPackage.VERZWEIGUNG__SPULEN: return ModelrailwayPackage.MAGNETARTIKEL__SPULEN;
				case ModelrailwayPackage.VERZWEIGUNG__MODUL: return ModelrailwayPackage.MAGNETARTIKEL__MODUL;
				default: return -1;
			}
		}
		if (baseClass == Unit.class) {
			switch (derivedFeatureID) {
				case ModelrailwayPackage.VERZWEIGUNG__UNIT_NO: return ModelrailwayPackage.UNIT__UNIT_NO;
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
		if (baseClass == Magnetartikel.class) {
			switch (baseFeatureID) {
				case ModelrailwayPackage.MAGNETARTIKEL__SPULEN: return ModelrailwayPackage.VERZWEIGUNG__SPULEN;
				case ModelrailwayPackage.MAGNETARTIKEL__MODUL: return ModelrailwayPackage.VERZWEIGUNG__MODUL;
				default: return -1;
			}
		}
		if (baseClass == Unit.class) {
			switch (baseFeatureID) {
				case ModelrailwayPackage.UNIT__UNIT_NO: return ModelrailwayPackage.VERZWEIGUNG__UNIT_NO;
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
		result.append(" (spulen: ");
		result.append(spulen);
		result.append(", unit_no: ");
		result.append(unit_no);
		result.append(", neu: ");
		result.append(neu);
		result.append(')');
		return result.toString();
	}

} //VerzweigungImpl
