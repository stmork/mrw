/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage
 * @generated
 */
public interface ModelrailwayFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelrailwayFactory eINSTANCE = de.morknet.mrw.metamodel.impl.ModelrailwayFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Anschluss</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Anschluss</em>'.
	 * @generated
	 */
	Anschluss createAnschluss();

	/**
	 * Returns a new object of class '<em>Gleisabschnitt</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gleisabschnitt</em>'.
	 * @generated
	 */
	Gleisabschnitt createGleisabschnitt();

	/**
	 * Returns a new object of class '<em>Modell</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Modell</em>'.
	 * @generated
	 */
	Modell createModell();

	/**
	 * Returns a new object of class '<em>Controller</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Controller</em>'.
	 * @generated
	 */
	Controller createController();

	/**
	 * Returns a new object of class '<em>Gleismodul</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gleismodul</em>'.
	 * @generated
	 */
	Gleismodul createGleismodul();

	/**
	 * Returns a new object of class '<em>Ausfahrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ausfahrsignal</em>'.
	 * @generated
	 */
	Ausfahrsignal createAusfahrsignal();

	/**
	 * Returns a new object of class '<em>Bahnhof</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bahnhof</em>'.
	 * @generated
	 */
	Bahnhof createBahnhof();

	/**
	 * Returns a new object of class '<em>Impulsmodul</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Impulsmodul</em>'.
	 * @generated
	 */
	Impulsmodul createImpulsmodul();

	/**
	 * Returns a new object of class '<em>Einfahrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Einfahrsignal</em>'.
	 * @generated
	 */
	Einfahrsignal createEinfahrsignal();

	/**
	 * Returns a new object of class '<em>Weiche</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weiche</em>'.
	 * @generated
	 */
	Weiche createWeiche();

	/**
	 * Returns a new object of class '<em>Formvorsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formvorsignal</em>'.
	 * @generated
	 */
	Formvorsignal createFormvorsignal();

	/**
	 * Returns a new object of class '<em>Gleis</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gleis</em>'.
	 * @generated
	 */
	Gleis createGleis();

	/**
	 * Returns a new object of class '<em>Blocksignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Blocksignal</em>'.
	 * @generated
	 */
	Blocksignal createBlocksignal();

	/**
	 * Returns a new object of class '<em>Vorsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vorsignal</em>'.
	 * @generated
	 */
	Vorsignal createVorsignal();

	/**
	 * Returns a new object of class '<em>DKW</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DKW</em>'.
	 * @generated
	 */
	DKW createDKW();

	/**
	 * Returns a new object of class '<em>Gleissperrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gleissperrsignal</em>'.
	 * @generated
	 */
	Gleissperrsignal createGleissperrsignal();

	/**
	 * Returns a new object of class '<em>Formhauptsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formhauptsignal</em>'.
	 * @generated
	 */
	Formhauptsignal createFormhauptsignal();

	/**
	 * Returns a new object of class '<em>Formgleissperrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formgleissperrsignal</em>'.
	 * @generated
	 */
	Formgleissperrsignal createFormgleissperrsignal();

	/**
	 * Returns a new object of class '<em>Strecke</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strecke</em>'.
	 * @generated
	 */
	Strecke createStrecke();

	/**
	 * Returns a new object of class '<em>Beleuchtungsmodul</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Beleuchtungsmodul</em>'.
	 * @generated
	 */
	Beleuchtungsmodul createBeleuchtungsmodul();

	/**
	 * Returns a new object of class '<em>Lampe</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lampe</em>'.
	 * @generated
	 */
	Lampe createLampe();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelrailwayPackage getModelrailwayPackage();

} //ModelrailwayFactory
