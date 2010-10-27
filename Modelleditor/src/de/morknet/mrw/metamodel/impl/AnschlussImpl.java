/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Controller;
import de.morknet.mrw.metamodel.Licht;
import de.morknet.mrw.metamodel.Lichtsignal;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anschluss</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getNummer <em>Nummer</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getLichtsignale <em>Lichtsignale</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getController <em>Controller</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.AnschlussImpl#getLichter <em>Lichter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnschlussImpl extends EObjectImpl implements Anschluss {
	/**
	 * The default value of the '{@link #getNummer() <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNummer()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMMER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNummer() <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNummer()
	 * @generated
	 * @ordered
	 */
	protected int nummer = NUMMER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLichtsignale() <em>Lichtsignale</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLichtsignale()
	 * @generated
	 * @ordered
	 */
	protected EList<Lichtsignal> lichtsignale;

	/**
	 * The cached value of the '{@link #getLichter() <em>Lichter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLichter()
	 * @generated
	 * @ordered
	 */
	protected EList<Licht> lichter;

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
	public int getNummer() {
		return nummer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNummer(int newNummer) {
		int oldNummer = nummer;
		nummer = newNummer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.ANSCHLUSS__NUMMER, oldNummer, nummer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Lichtsignal> getLichtsignale() {
		if (lichtsignale == null) {
			lichtsignale = new EObjectWithInverseResolvingEList<Lichtsignal>(Lichtsignal.class, this, ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE, ModelrailwayPackage.LICHTSIGNAL__ANSCHLUSS);
		}
		return lichtsignale;
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
	public EList<Licht> getLichter() {
		if (lichter == null) {
			lichter = new EObjectContainmentWithInverseEList<Licht>(Licht.class, this, ModelrailwayPackage.ANSCHLUSS__LICHTER, ModelrailwayPackage.LICHT__ANSCHLUSS);
		}
		return lichter;
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
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLichtsignale()).basicAdd(otherEnd, msgs);
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, ModelrailwayPackage.ANSCHLUSS__CONTROLLER, msgs);
			case ModelrailwayPackage.ANSCHLUSS__LICHTER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLichter()).basicAdd(otherEnd, msgs);
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
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				return ((InternalEList<?>)getLichtsignale()).basicRemove(otherEnd, msgs);
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return eBasicSetContainer(null, ModelrailwayPackage.ANSCHLUSS__CONTROLLER, msgs);
			case ModelrailwayPackage.ANSCHLUSS__LICHTER:
				return ((InternalEList<?>)getLichter()).basicRemove(otherEnd, msgs);
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
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.CONTROLLER__ANSCHLUESSE, Controller.class, msgs);
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
			case ModelrailwayPackage.ANSCHLUSS__NUMMER:
				return getNummer();
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				return getLichtsignale();
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return getController();
			case ModelrailwayPackage.ANSCHLUSS__LICHTER:
				return getLichter();
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
			case ModelrailwayPackage.ANSCHLUSS__NUMMER:
				setNummer((Integer)newValue);
				return;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				getLichtsignale().clear();
				getLichtsignale().addAll((Collection<? extends Lichtsignal>)newValue);
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
			case ModelrailwayPackage.ANSCHLUSS__NUMMER:
				setNummer(NUMMER_EDEFAULT);
				return;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				getLichtsignale().clear();
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
			case ModelrailwayPackage.ANSCHLUSS__NUMMER:
				return nummer != NUMMER_EDEFAULT;
			case ModelrailwayPackage.ANSCHLUSS__LICHTSIGNALE:
				return lichtsignale != null && !lichtsignale.isEmpty();
			case ModelrailwayPackage.ANSCHLUSS__CONTROLLER:
				return getController() != null;
			case ModelrailwayPackage.ANSCHLUSS__LICHTER:
				return lichter != null && !lichter.isEmpty();
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
		result.append(" (nummer: ");
		result.append(nummer);
		result.append(')');
		return result.toString();
	}

} //AnschlussImpl
