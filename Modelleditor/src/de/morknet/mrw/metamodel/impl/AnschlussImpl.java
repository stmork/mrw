/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id: AnschlussImpl.java 937 2010-04-20 09:06:22Z smork $
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Controller;
import de.morknet.mrw.metamodel.Lichtsignal;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anschluss</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getLichtsignal <em>Lichtsignal</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getController <em>Controller</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnschlussImpl extends EObjectImpl implements Anschluss {
	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLichtsignal() <em>Lichtsignal</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLichtsignal()
	 * @generated
	 * @ordered
	 */
	protected EList<Lichtsignal> lichtsignal;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnschlussImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.ANSCHLUSS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.ANSCHLUSS__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Lichtsignal> getLichtsignal() {
		if (lichtsignal == null) {
			lichtsignal = new EObjectWithInverseResolvingEList<Lichtsignal>(Lichtsignal.class, this, ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL, ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS);
		}
		return lichtsignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Controller getController() {
		if (eContainerFeatureID() != ModelrailwayPackage.ANSCHLUSS__CONTROLLER) return null;
		return (Controller)eContainer();
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
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLichtsignal()).basicAdd(otherEnd, msgs);
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.ANSCHLUSS__CONTROLLER, msgs);
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
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				return ((InternalEList<?>)getLichtsignal()).basicRemove(otherEnd, msgs);
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return eBasicSetContainer(null, ModelrailwayPackage.ANSCHLUSS__CONTROLLER, msgs);
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
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.CONTROLLER__ANSCHLUSS, Controller.class, msgs);
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
			case ModelrailwayPackage.ANSCHLUSS__NUMBER:
				return getNumber();
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				return getLichtsignal();
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return getController();
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
			case ModelrailwayPackage.ANSCHLUSS__NUMBER:
				setNumber((Integer)newValue);
				return;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				getLichtsignal().clear();
				getLichtsignal().addAll((Collection<? extends Lichtsignal>)newValue);
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
			case ModelrailwayPackage.ANSCHLUSS__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				getLichtsignal().clear();
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
			case ModelrailwayPackage.ANSCHLUSS__NUMBER:
				return number != NUMBER_EDEFAULT;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNAL:
				return lichtsignal != null && !lichtsignal.isEmpty();
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return getController() != null;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (number: ");
		result.append(number);
		result.append(')');
		return result.toString();
	}

} //AnschlussImpl
