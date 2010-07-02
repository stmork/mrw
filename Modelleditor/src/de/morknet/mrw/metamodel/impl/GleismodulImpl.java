/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: GleismodulImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.Gleismodul;
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
 * An implementation of the model object '<em><b>Gleismodul</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.GleismodulImpl#getAbschnitte <em>Abschnitte</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GleismodulImpl extends ModulImpl implements Gleismodul {
	/**
	 * The cached value of the '{@link #getAbschnitte() <em>Abschnitte</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbschnitte()
	 * @generated
	 * @ordered
	 */
	protected EList<Gleisabschnitt> abschnitte;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GleismodulImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.GLEISMODUL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Gleisabschnitt> getAbschnitte() {
		if (abschnitte == null) {
			abschnitte = new EObjectWithInverseResolvingEList<Gleisabschnitt>(Gleisabschnitt.class, this, ModelrailwayPackage.GLEISMODUL__ABSCHNITTE, ModelrailwayPackage.GLEISABSCHNITT__MODUL);
		}
		return abschnitte;
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAbschnitte()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				return ((InternalEList<?>)getAbschnitte()).basicRemove(otherEnd, msgs);
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				return getAbschnitte();
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				getAbschnitte().clear();
				getAbschnitte().addAll((Collection<? extends Gleisabschnitt>)newValue);
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				getAbschnitte().clear();
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
			case ModelrailwayPackage.GLEISMODUL__ABSCHNITTE:
				return abschnitte != null && !abschnitte.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GleismodulImpl
