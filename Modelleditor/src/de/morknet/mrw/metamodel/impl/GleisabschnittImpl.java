/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Bauelement;
import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.Gleismodul;
import de.morknet.mrw.metamodel.Gruppe;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Unit;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gleisabschnitt</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl#getUnit_no <em>Unit no</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl#getGruppe <em>Gruppe</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl#getModul <em>Modul</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl#getBauelement <em>Bauelement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GleisabschnittImpl extends ElementImpl implements Gleisabschnitt {
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
	 * The cached value of the '{@link #getModul() <em>Modul</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModul()
	 * @generated
	 * @ordered
	 */
	protected Gleismodul modul;

	/**
	 * The cached value of the '{@link #getBauelement() <em>Bauelement</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBauelement()
	 * @generated
	 * @ordered
	 */
	protected EList<Bauelement> bauelement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GleisabschnittImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.GLEISABSCHNITT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO, oldUnit_no, unit_no));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gruppe getGruppe() {
		if (eContainerFeatureID() != ModelrailwayPackage.GLEISABSCHNITT__GRUPPE) return null;
		return (Gruppe)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleismodul getModul() {
		if (modul != null && modul.eIsProxy()) {
			InternalEObject oldModul = (InternalEObject)modul;
			modul = (Gleismodul)eResolveProxy(oldModul);
			if (modul != oldModul) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelrailwayPackage.GLEISABSCHNITT__MODUL, oldModul, modul));
			}
		}
		return modul;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleismodul basicGetModul() {
		return modul;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModul(Gleismodul newModul, NotificationChain msgs) {
		Gleismodul oldModul = modul;
		modul = newModul;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEISABSCHNITT__MODUL, oldModul, newModul);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModul(Gleismodul newModul) {
		if (newModul != modul) {
			NotificationChain msgs = null;
			if (modul != null)
				msgs = ((InternalEObject)modul).eInverseRemove(this, ModelrailwayPackage.GLEISMODUL__ABSCHNITTE, Gleismodul.class, msgs);
			if (newModul != null)
				msgs = ((InternalEObject)newModul).eInverseAdd(this, ModelrailwayPackage.GLEISMODUL__ABSCHNITTE, Gleismodul.class, msgs);
			msgs = basicSetModul(newModul, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.GLEISABSCHNITT__MODUL, newModul, newModul));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bauelement> getBauelement() {
		if (bauelement == null) {
			bauelement = new EObjectContainmentWithInverseEList<Bauelement>(Bauelement.class, this, ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT, ModelrailwayPackage.BAUELEMENT__ABSCHNITT);
		}
		return bauelement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.GLEISABSCHNITT__GRUPPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.GLEISABSCHNITT__GRUPPE, msgs);
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				if (modul != null)
					msgs = ((InternalEObject)modul).eInverseRemove(this, ModelrailwayPackage.GLEISMODUL__ABSCHNITTE, Gleismodul.class, msgs);
				return basicSetModul((Gleismodul)otherEnd, msgs);
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBauelement()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.GLEISABSCHNITT__GRUPPE:
				return eBasicSetContainer(null, ModelrailwayPackage.GLEISABSCHNITT__GRUPPE, msgs);
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				return basicSetModul(null, msgs);
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				return ((InternalEList<?>)getBauelement()).basicRemove(otherEnd, msgs);
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
			case ModelrailwayPackage.GLEISABSCHNITT__GRUPPE:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.GRUPPE__ABSCHNITT, Gruppe.class, msgs);
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
			case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO:
				return getUnit_no();
			case ModelrailwayPackage.GLEISABSCHNITT__GRUPPE:
				return getGruppe();
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				if (resolve) return getModul();
				return basicGetModul();
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				return getBauelement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO:
				setUnit_no((Integer)newValue);
				return;
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				setModul((Gleismodul)newValue);
				return;
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				getBauelement().clear();
				getBauelement().addAll((Collection<? extends Bauelement>)newValue);
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
			case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO:
				setUnit_no(UNIT_NO_EDEFAULT);
				return;
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				setModul((Gleismodul)null);
				return;
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				getBauelement().clear();
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
			case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO:
				return unit_no != UNIT_NO_EDEFAULT;
			case ModelrailwayPackage.GLEISABSCHNITT__GRUPPE:
				return getGruppe() != null;
			case ModelrailwayPackage.GLEISABSCHNITT__MODUL:
				return modul != null;
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				return bauelement != null && !bauelement.isEmpty();
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
				case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO: return ModelrailwayPackage.UNIT__UNIT_NO;
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
				case ModelrailwayPackage.UNIT__UNIT_NO: return ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO;
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
		result.append(')');
		return result.toString();
	}

} //GleisabschnittImpl
