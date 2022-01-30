/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Impulsmodul;
import de.morknet.mrw.metamodel.Magnetartikel;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Impulsmodul</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.ImpulsmodulImpl#getMagnetartikel <em>Magnetartikel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImpulsmodulImpl extends ModulImpl implements Impulsmodul {
	/**
	 * The cached value of the '{@link #getMagnetartikel() <em>Magnetartikel</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMagnetartikel()
	 * @generated
	 * @ordered
	 */
	protected EList<Magnetartikel> magnetartikel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImpulsmodulImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.IMPULSMODUL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Magnetartikel> getMagnetartikel() {
		if (magnetartikel == null) {
			magnetartikel = new EObjectWithInverseResolvingEList<Magnetartikel>(Magnetartikel.class, this, ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL, ModelrailwayPackage.MAGNETARTIKEL__MODUL);
		}
		return magnetartikel;
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMagnetartikel()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				return ((InternalEList<?>)getMagnetartikel()).basicRemove(otherEnd, msgs);
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				return getMagnetartikel();
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				getMagnetartikel().clear();
				getMagnetartikel().addAll((Collection<? extends Magnetartikel>)newValue);
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				getMagnetartikel().clear();
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
			case ModelrailwayPackage.IMPULSMODUL__MAGNETARTIKEL:
				return magnetartikel != null && !magnetartikel.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ImpulsmodulImpl
