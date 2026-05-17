/**
 * Copyright (C) 2007-2022 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Crossing;
import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Crossing</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.morknet.mrw.metamodel.impl.CrossingImpl#getAnschluss <em>Anschluss</em>}</li>
 *   <li>{@link de.morknet.mrw.metamodel.impl.CrossingImpl#getAbschnitte <em>Abschnitte</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CrossingImpl extends UnitImpl implements Crossing {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) 2007-2026 committers of this modelrailway project. All rights reserved.";
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
	protected CrossingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelrailwayPackage.Literals.CROSSING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Anschluss getAnschluss() {
		if (eContainerFeatureID() != ModelrailwayPackage.CROSSING__ANSCHLUSS) return null;
		return (Anschluss)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnschluss(Anschluss newAnschluss, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAnschluss, ModelrailwayPackage.CROSSING__ANSCHLUSS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnschluss(Anschluss newAnschluss) {
		if (newAnschluss != eInternalContainer() || (eContainerFeatureID() != ModelrailwayPackage.CROSSING__ANSCHLUSS && newAnschluss != null)) {
			if (EcoreUtil.isAncestor(this, newAnschluss))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAnschluss != null)
				msgs = ((InternalEObject)newAnschluss).eInverseAdd(this, ModelrailwayPackage.ANSCHLUSS__CROSSING, Anschluss.class, msgs);
			msgs = basicSetAnschluss(newAnschluss, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelrailwayPackage.CROSSING__ANSCHLUSS, newAnschluss, newAnschluss));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Gleisabschnitt> getAbschnitte() {
		if (abschnitte == null) {
			abschnitte = new EObjectWithInverseResolvingEList<Gleisabschnitt>(Gleisabschnitt.class, this, ModelrailwayPackage.CROSSING__ABSCHNITTE, ModelrailwayPackage.GLEISABSCHNITT__CROSSING);
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAnschluss((Anschluss)otherEnd, msgs);
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				return basicSetAnschluss(null, msgs);
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				return eInternalContainer().eInverseRemove(this, ModelrailwayPackage.ANSCHLUSS__CROSSING, Anschluss.class, msgs);
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				return getAnschluss();
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				setAnschluss((Anschluss)newValue);
				return;
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				setAnschluss((Anschluss)null);
				return;
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
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
			case ModelrailwayPackage.CROSSING__ANSCHLUSS:
				return getAnschluss() != null;
			case ModelrailwayPackage.CROSSING__ABSCHNITTE:
				return abschnitte != null && !abschnitte.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CrossingImpl
