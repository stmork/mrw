/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: BauelementImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Bauelement;
import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bauelement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.BauelementImpl#getAbschnitt <em>Abschnitt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BauelementImpl extends ElementImpl implements Bauelement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BauelementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.BAUELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gleisabschnitt getAbschnitt() {
		if (eContainerFeatureID() != ModelrailwayPackage.BAUELEMENT__ABSCHNITT) return null;
		return (Gleisabschnitt)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelrailwayPackage.BAUELEMENT__ABSCHNITT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.BAUELEMENT__ABSCHNITT, msgs);
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
			case ModelrailwayPackage.BAUELEMENT__ABSCHNITT:
				return eBasicSetContainer(null, ModelrailwayPackage.BAUELEMENT__ABSCHNITT, msgs);
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
			case ModelrailwayPackage.BAUELEMENT__ABSCHNITT:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT, Gleisabschnitt.class, msgs);
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
			case ModelrailwayPackage.BAUELEMENT__ABSCHNITT:
				return getAbschnitt();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelrailwayPackage.BAUELEMENT__ABSCHNITT:
				return getAbschnitt() != null;
		}
		return super.eIsSet(featureID);
	}

} //BauelementImpl
