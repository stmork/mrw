/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Controller;
import de.morknet.mrw.metamodel.Modell;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Modul;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Controller</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.ControllerImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.ControllerImpl#getAnschluesse <em>Anschluesse</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.ControllerImpl#getModule <em>Module</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.ControllerImpl#getModell <em>Modell</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerImpl extends EObjectImpl implements Controller {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnschluesse() <em>Anschluesse</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnschluesse()
	 * @generated
	 * @ordered
	 */
	protected EList<Anschluss> anschluesse;

	/**
	 * The cached value of the '{@link #getModule() <em>Module</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModule()
	 * @generated
	 * @ordered
	 */
	protected EList<Modul> module;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControllerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.CONTROLLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.CONTROLLER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Anschluss> getAnschluesse() {
		if (anschluesse == null) {
			anschluesse = new EObjectContainmentWithInverseEList<Anschluss>(Anschluss.class, this, ModelrailwayPackage.CONTROLLER__ANSCHLUESSE, ModelrailwayPackage.ANSCHLUSS__CONTROLLER);
		}
		return anschluesse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Modul> getModule() {
		if (module == null) {
			module = new EObjectContainmentWithInverseEList<Modul>(Modul.class, this, ModelrailwayPackage.CONTROLLER__MODULE, ModelrailwayPackage.MODUL__CONTROLLER);
		}
		return module;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Modell getModell() {
		if (eContainerFeatureID() != ModelrailwayPackage.CONTROLLER__MODELL) return null;
		return (Modell)eInternalContainer();
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
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnschluesse()).basicAdd(otherEnd, msgs);
			case ModelrailwayPackage.CONTROLLER__MODULE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModule()).basicAdd(otherEnd, msgs);
			case ModelrailwayPackage.CONTROLLER__MODELL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.CONTROLLER__MODELL, msgs);
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
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				return ((InternalEList<?>)getAnschluesse()).basicRemove(otherEnd, msgs);
			case ModelrailwayPackage.CONTROLLER__MODULE:
				return ((InternalEList<?>)getModule()).basicRemove(otherEnd, msgs);
			case ModelrailwayPackage.CONTROLLER__MODELL:
				return eBasicSetContainer(null, ModelrailwayPackage.CONTROLLER__MODELL, msgs);
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
			case ModelrailwayPackage.CONTROLLER__MODELL:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.MODELL__CONTROLLER, Modell.class, msgs);
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
			case ModelrailwayPackage.CONTROLLER__ID:
				return getId();
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				return getAnschluesse();
			case ModelrailwayPackage.CONTROLLER__MODULE:
				return getModule();
			case ModelrailwayPackage.CONTROLLER__MODELL:
				return getModell();
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
			case ModelrailwayPackage.CONTROLLER__ID:
				setId((Integer)newValue);
				return;
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				getAnschluesse().clear();
				getAnschluesse().addAll((Collection<? extends Anschluss>)newValue);
				return;
			case ModelrailwayPackage.CONTROLLER__MODULE:
				getModule().clear();
				getModule().addAll((Collection<? extends Modul>)newValue);
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
			case ModelrailwayPackage.CONTROLLER__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				getAnschluesse().clear();
				return;
			case ModelrailwayPackage.CONTROLLER__MODULE:
				getModule().clear();
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
			case ModelrailwayPackage.CONTROLLER__ID:
				return id != ID_EDEFAULT;
			case ModelrailwayPackage.CONTROLLER__ANSCHLUESSE:
				return anschluesse != null && !anschluesse.isEmpty();
			case ModelrailwayPackage.CONTROLLER__MODULE:
				return module != null && !module.isEmpty();
			case ModelrailwayPackage.CONTROLLER__MODELL:
				return getModell() != null;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ControllerImpl
