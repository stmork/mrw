/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.Gruppe;
import de.morknet.mrw.metamodel.Modell;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gruppe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GruppeImpl#getModell <em>Modell</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GruppeImpl#getAbschnitt <em>Abschnitt</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class GruppeImpl extends ElementImpl implements Gruppe {
	/**
	 * The cached value of the '{@link #getAbschnitt() <em>Abschnitt</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbschnitt()
	 * @generated
	 * @ordered
	 */
	protected EList<Gleisabschnitt> abschnitt;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GruppeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.GRUPPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Modell getModell() {
		if (eContainerFeatureID() != ModelrailwayPackage.GRUPPE__MODELL) return null;
		return (Modell)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Gleisabschnitt> getAbschnitt() {
		if (abschnitt == null) {
			abschnitt = new EObjectContainmentWithInverseEList<Gleisabschnitt>(Gleisabschnitt.class, this, ModelrailwayPackage.GRUPPE__ABSCHNITT, ModelrailwayPackage.GLEISABSCHNITT__GRUPPE);
		}
		return abschnitt;
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
			case ModelrailwayPackage.GRUPPE__MODELL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.GRUPPE__MODELL, msgs);
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAbschnitt()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.GRUPPE__MODELL:
				return eBasicSetContainer(null, ModelrailwayPackage.GRUPPE__MODELL, msgs);
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				return ((InternalEList<?>)getAbschnitt()).basicRemove(otherEnd, msgs);
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
			case ModelrailwayPackage.GRUPPE__MODELL:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.MODELL__GRUPPE, Modell.class, msgs);
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
			case ModelrailwayPackage.GRUPPE__MODELL:
				return getModell();
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				return getAbschnitt();
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
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				getAbschnitt().clear();
				getAbschnitt().addAll((Collection<? extends Gleisabschnitt>)newValue);
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
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				getAbschnitt().clear();
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
			case ModelrailwayPackage.GRUPPE__MODELL:
				return getModell() != null;
			case ModelrailwayPackage.GRUPPE__ABSCHNITT:
				return abschnitt != null && !abschnitt.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GruppeImpl
