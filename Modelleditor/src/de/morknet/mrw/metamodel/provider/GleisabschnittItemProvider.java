/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.provider;


import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.ModelrailwayFactory;
import de.morknet.mrw.metamodel.ModelrailwayPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.morknet.mrw.metamodel.Gleisabschnitt} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GleisabschnittItemProvider
	extends ElementItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GleisabschnittItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addUnit_noPropertyDescriptor(object);
			addModulPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Unit no feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnit_noPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Unit_unit_no_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Unit_unit_no_feature", "_UI_Unit_type"),
				 ModelrailwayPackage.Literals.UNIT__UNIT_NO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Modul feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModulPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Gleisabschnitt_modul_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Gleisabschnitt_modul_feature", "_UI_Gleisabschnitt_type"),
				 ModelrailwayPackage.Literals.GLEISABSCHNITT__MODUL,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Gleisabschnitt.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Gleisabschnitt"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Gleisabschnitt)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Gleisabschnitt_type") :
			getString("_UI_Gleisabschnitt_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Gleisabschnitt.class)) {
			case ModelrailwayPackage.GLEISABSCHNITT__UNIT_NO:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ModelrailwayPackage.GLEISABSCHNITT__BAUELEMENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createAusfahrsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createEinfahrsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createWeiche()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createFormvorsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createGleis()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createBlocksignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createVorsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createDKW()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createGleissperrsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createFormhauptsignal()));

		newChildDescriptors.add
			(createChildParameter
				(ModelrailwayPackage.Literals.GLEISABSCHNITT__BAUELEMENT,
				 ModelrailwayFactory.eINSTANCE.createFormgleissperrsignal()));
	}

}
