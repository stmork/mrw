/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.morknet.mrw.metamodel.Beleuchtungsmodul;
import de.morknet.mrw.metamodel.Lampe;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lampe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getTyp <em>Typ</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.LampeImpl#getModul <em>Modul</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LampeImpl extends BeleuchtungsmittelImpl implements Lampe {
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
				msgs = ((InternalEObject)newModul).eInverseAdd(this, ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPEN, Beleuchtungsmodul.class, msgs);
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
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.BELEUCHTUNGSMODUL__LAMPEN, Beleuchtungsmodul.class, msgs);
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (typ: ");
		result.append(typ);
		result.append(')');
		return result.toString();
	}

} //LampeImpl
