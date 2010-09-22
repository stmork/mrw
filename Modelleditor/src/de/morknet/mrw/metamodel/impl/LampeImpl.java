/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Beleuchtungsmodul;
import de.morknet.mrw.metamodel.Lampe;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import de.morknet.mrw.metamodel.Unit;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lampe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getUnit_no <em>Unit no</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getSchwellwert <em>Schwellwert</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getTyp <em>Typ</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LampeImpl extends ElementImpl implements Lampe {
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
	 * The default value of the '{@link #getTyp() <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTyp()
	 * @generated
	 * @ordered
	 */
	protected static final int TYP_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTyp() <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTyp()
	 * @generated
	 * @ordered
	 */
	protected int typ = TYP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LampeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.LAMPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LAMPE__UNIT_NO, oldUnit_no, unit_no));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LAMPE__SCHWELLWERT, oldSchwellwert, schwellwert));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTyp() {
		return typ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTyp(int newTyp) {
		int oldTyp = typ;
		typ = newTyp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LAMPE__TYP, oldTyp, typ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Beleuchtungsmodul getModul() {
		if (eContainerFeatureID() != ModelrailwayPackage.LAMPE__MODUL) return null;
		return (Beleuchtungsmodul)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModul(Beleuchtungsmodul newModul, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModul, ModelrailwayPackage.LAMPE__MODUL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModul(Beleuchtungsmodul newModul) {
		if (newModul != eInternalContainer() || (eContainerFeatureID() != ModelrailwayPackage.LAMPE__MODUL && newModul != null)) {
			if (EcoreUtil.isAncestor(this, newModul))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModul != null)
				msgs = ((InternalEObject)newModul).eInverseAdd(this, ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE, Beleuchtungsmodul.class, msgs);
			msgs = basicSetModul(newModul, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.LAMPE__MODUL, newModul, newModul));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.LAMPE__MODUL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModul((Beleuchtungsmodul)otherEnd, msgs);
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
			case ModelrailwayPackage.LAMPE__MODUL:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ModelrailwayPackage.LAMPE__MODUL:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE, Beleuchtungsmodul.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelrailwayPackage.LAMPE__UNIT_NO:
				return getUnit_no();
			case ModelrailwayPackage.LAMPE__SCHWELLWERT:
				return getSchwellwert();
			case ModelrailwayPackage.LAMPE__TYP:
				return getTyp();
			case ModelrailwayPackage.LAMPE__MODUL:
				return getModul();
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
			case ModelrailwayPackage.LAMPE__UNIT_NO:
				setUnit_no((Integer)newValue);
				return;
			case ModelrailwayPackage.LAMPE__SCHWELLWERT:
				setSchwellwert((Integer)newValue);
				return;
			case ModelrailwayPackage.LAMPE__TYP:
				setTyp((Integer)newValue);
				return;
			case ModelrailwayPackage.LAMPE__MODUL:
				setModul((Beleuchtungsmodul)newValue);
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
			case ModelrailwayPackage.LAMPE__UNIT_NO:
				setUnit_no(UNIT_NO_EDEFAULT);
				return;
			case ModelrailwayPackage.LAMPE__SCHWELLWERT:
				setSchwellwert(SCHWELLWERT_EDEFAULT);
				return;
			case ModelrailwayPackage.LAMPE__TYP:
				setTyp(TYP_EDEFAULT);
				return;
			case ModelrailwayPackage.LAMPE__MODUL:
				setModul((Beleuchtungsmodul)null);
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
			case ModelrailwayPackage.LAMPE__UNIT_NO:
				return unit_no != UNIT_NO_EDEFAULT;
			case ModelrailwayPackage.LAMPE__SCHWELLWERT:
				return schwellwert != SCHWELLWERT_EDEFAULT;
			case ModelrailwayPackage.LAMPE__TYP:
				return typ != TYP_EDEFAULT;
			case ModelrailwayPackage.LAMPE__MODUL:
				return getModul() != null;
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
				case ModelrailwayPackage.LAMPE__UNIT_NO: return ModelrailwayPackage.UNIT__UNIT_NO;
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
				case ModelrailwayPackage.UNIT__UNIT_NO: return ModelrailwayPackage.LAMPE__UNIT_NO;
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
		result.append(", typ: ");
		result.append(typ);
		result.append(')');
		return result.toString();
	}

} //LampeImpl
