/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Beleuchtungsmodul;
import de.morknet.mrw.metamodel.Lampe;
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
 * An implementation of the model object '<em><b>Beleuchtungsmodul</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.BeleuchtungsmodulImpl#getLampe <em>Lampe</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeleuchtungsmodulImpl extends ModulImpl implements Beleuchtungsmodul {
	/**
	 * The cached value of the '{@link #getLampe() <em>Lampe</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLampe()
	 * @generated
	 * @ordered
	 */
	protected EList<Lampe> lampe;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BeleuchtungsmodulImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.BELEUCHTUNGSMODUL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Lampe> getLampe() {
		if (lampe == null) {
			lampe = new EObjectContainmentWithInverseEList<Lampe>(Lampe.class, this, ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE, ModelrailwayPackage.LAMPE__MODUL);
		}
		return lampe;
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
			case ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLampe()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE:
				return ((InternalEList<?>)getLampe()).basicRemove(otherEnd, msgs);
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
			case ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE:
				return getLampe();
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
			case ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPE:
				return lampe != null && !lampe.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BeleuchtungsmodulImpl
