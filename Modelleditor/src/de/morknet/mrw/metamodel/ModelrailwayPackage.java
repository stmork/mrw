/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.morknet.mrw.metamodel.ModelrailwayFactory
 * @model kind="package"
 * @generated
 */
public interface ModelrailwayPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "metamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.morknet.de/Modelrailway";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Modelrailway";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelrailwayPackage eINSTANCE = de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.AnschlussImpl <em>Anschluss</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.AnschlussImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getAnschluss()
	 * @generated
	 */
	int ANSCHLUSS = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANSCHLUSS__NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Lichtsignal</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANSCHLUSS__LICHTSIGNAL = 1;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANSCHLUSS__CONTROLLER = 2;

	/**
	 * The number of structural features of the '<em>Anschluss</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANSCHLUSS_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.ElementImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.BauelementImpl <em>Bauelement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.BauelementImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBauelement()
	 * @generated
	 */
	int BAUELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAUELEMENT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAUELEMENT__ABSCHNITT = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bauelement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAUELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.SignalImpl <em>Signal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.SignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__NAME = BAUELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__ABSCHNITT = BAUELEMENT__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__UNIT_NO = BAUELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__IN_ZAEHLRICHTUNG = BAUELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_FEATURE_COUNT = BAUELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.LichtsignalImpl <em>Lichtsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.LichtsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getLichtsignal()
	 * @generated
	 */
	int LICHTSIGNAL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL__NAME = SIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL__ABSCHNITT = SIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL__UNIT_NO = SIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL__IN_ZAEHLRICHTUNG = SIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL__ANSCHLUSS = SIGNAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lichtsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICHTSIGNAL_FEATURE_COUNT = SIGNAL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl <em>Gleisabschnitt</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GleisabschnittImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleisabschnitt()
	 * @generated
	 */
	int GLEISABSCHNITT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT__UNIT_NO = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Gruppe</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT__GRUPPE = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT__MODUL = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bauelement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT__BAUELEMENT = ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Gleisabschnitt</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISABSCHNITT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.UnitImpl <em>Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.UnitImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getUnit()
	 * @generated
	 */
	int UNIT = 6;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT__UNIT_NO = 0;

	/**
	 * The number of structural features of the '<em>Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GruppeImpl <em>Gruppe</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GruppeImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGruppe()
	 * @generated
	 */
	int GRUPPE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRUPPE__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Modell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRUPPE__MODELL = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRUPPE__ABSCHNITT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gruppe</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRUPPE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.ModellImpl <em>Modell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.ModellImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getModell()
	 * @generated
	 */
	int MODELL = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELL__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELL__CONTROLLER = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Gruppe</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELL__GRUPPE = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Modell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELL_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.ControllerImpl <em>Controller</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.ControllerImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getController()
	 * @generated
	 */
	int CONTROLLER = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROLLER__ID = 0;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROLLER__ANSCHLUSS = 1;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROLLER__MODULES = 2;

	/**
	 * The feature id for the '<em><b>Modell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROLLER__MODELL = 3;

	/**
	 * The number of structural features of the '<em>Controller</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROLLER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.ModulImpl <em>Modul</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.ModulImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getModul()
	 * @generated
	 */
	int MODUL = 10;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODUL__NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODUL__CONTROLLER = 1;

	/**
	 * The number of structural features of the '<em>Modul</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODUL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GleismodulImpl <em>Gleismodul</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GleismodulImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleismodul()
	 * @generated
	 */
	int GLEISMODUL = 11;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISMODUL__NUMBER = MODUL__NUMBER;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISMODUL__CONTROLLER = MODUL__CONTROLLER;

	/**
	 * The feature id for the '<em><b>Abschnitte</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISMODUL__ABSCHNITTE = MODUL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Gleismodul</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISMODUL_FEATURE_COUNT = MODUL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.HauptlichtsignalImpl <em>Hauptlichtsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.HauptlichtsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getHauptlichtsignal()
	 * @generated
	 */
	int HAUPTLICHTSIGNAL = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL__NAME = LICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL__ABSCHNITT = LICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL__UNIT_NO = LICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL__IN_ZAEHLRICHTUNG = LICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL__ANSCHLUSS = LICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Hauptlichtsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAUPTLICHTSIGNAL_FEATURE_COUNT = LICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.AusfahrsignalImpl <em>Ausfahrsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.AusfahrsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getAusfahrsignal()
	 * @generated
	 */
	int AUSFAHRSIGNAL = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL__NAME = HAUPTLICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL__ABSCHNITT = HAUPTLICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL__UNIT_NO = HAUPTLICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL__IN_ZAEHLRICHTUNG = HAUPTLICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL__ANSCHLUSS = HAUPTLICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Ausfahrsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUSFAHRSIGNAL_FEATURE_COUNT = HAUPTLICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.BahnhofImpl <em>Bahnhof</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.BahnhofImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBahnhof()
	 * @generated
	 */
	int BAHNHOF = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAHNHOF__NAME = GRUPPE__NAME;

	/**
	 * The feature id for the '<em><b>Modell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAHNHOF__MODELL = GRUPPE__MODELL;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAHNHOF__ABSCHNITT = GRUPPE__ABSCHNITT;

	/**
	 * The number of structural features of the '<em>Bahnhof</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAHNHOF_FEATURE_COUNT = GRUPPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GleisteilImpl <em>Gleisteil</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GleisteilImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleisteil()
	 * @generated
	 */
	int GLEISTEIL = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISTEIL__NAME = BAUELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISTEIL__ABSCHNITT = BAUELEMENT__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISTEIL__TEILE = BAUELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISTEIL__AIN_ZAEHLRICHTUNG = BAUELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gleisteil</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISTEIL_FEATURE_COUNT = BAUELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl <em>Verzweigung</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.VerzweigungImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getVerzweigung()
	 * @generated
	 */
	int VERZWEIGUNG = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__NAME = GLEISTEIL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__ABSCHNITT = GLEISTEIL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__TEILE = GLEISTEIL__TEILE;

	/**
	 * The feature id for the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__AIN_ZAEHLRICHTUNG = GLEISTEIL__AIN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__SPULEN = GLEISTEIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__MODUL = GLEISTEIL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__UNIT_NO = GLEISTEIL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Neu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG__NEU = GLEISTEIL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Verzweigung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERZWEIGUNG_FEATURE_COUNT = GLEISTEIL_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.MagnetartikelImpl <em>Magnetartikel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.MagnetartikelImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getMagnetartikel()
	 * @generated
	 */
	int MAGNETARTIKEL = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAGNETARTIKEL__NAME = BAUELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAGNETARTIKEL__ABSCHNITT = BAUELEMENT__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAGNETARTIKEL__SPULEN = BAUELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAGNETARTIKEL__MODUL = BAUELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Magnetartikel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAGNETARTIKEL_FEATURE_COUNT = BAUELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.ImpulsmodulImpl <em>Impulsmodul</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.ImpulsmodulImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getImpulsmodul()
	 * @generated
	 */
	int IMPULSMODUL = 17;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPULSMODUL__NUMBER = MODUL__NUMBER;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPULSMODUL__CONTROLLER = MODUL__CONTROLLER;

	/**
	 * The feature id for the '<em><b>Magnetartikel</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPULSMODUL__MAGNETARTIKEL = MODUL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Impulsmodul</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPULSMODUL_FEATURE_COUNT = MODUL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.EinfahrsignalImpl <em>Einfahrsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.EinfahrsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getEinfahrsignal()
	 * @generated
	 */
	int EINFAHRSIGNAL = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL__NAME = HAUPTLICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL__ABSCHNITT = HAUPTLICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL__UNIT_NO = HAUPTLICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL__IN_ZAEHLRICHTUNG = HAUPTLICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL__ANSCHLUSS = HAUPTLICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Einfahrsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINFAHRSIGNAL_FEATURE_COUNT = HAUPTLICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.WeicheImpl <em>Weiche</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.WeicheImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getWeiche()
	 * @generated
	 */
	int WEICHE = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__NAME = VERZWEIGUNG__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__ABSCHNITT = VERZWEIGUNG__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__TEILE = VERZWEIGUNG__TEILE;

	/**
	 * The feature id for the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__AIN_ZAEHLRICHTUNG = VERZWEIGUNG__AIN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__SPULEN = VERZWEIGUNG__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__MODUL = VERZWEIGUNG__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__UNIT_NO = VERZWEIGUNG__UNIT_NO;

	/**
	 * The feature id for the '<em><b>Neu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__NEU = VERZWEIGUNG__NEU;

	/**
	 * The feature id for the '<em><b>CIst Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__CIST_ABZWEIG = VERZWEIGUNG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>BIst Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__BIST_ABZWEIG = VERZWEIGUNG_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>BIst Bevorzugt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__BIST_BEVORZUGT = VERZWEIGUNG_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>CIst Bevorzugt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__CIST_BEVORZUGT = VERZWEIGUNG_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__B = VERZWEIGUNG_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>C</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__C = VERZWEIGUNG_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE__A = VERZWEIGUNG_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Weiche</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEICHE_FEATURE_COUNT = VERZWEIGUNG_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.FormsignalImpl <em>Formsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.FormsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormsignal()
	 * @generated
	 */
	int FORMSIGNAL = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__NAME = MAGNETARTIKEL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__ABSCHNITT = MAGNETARTIKEL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__SPULEN = MAGNETARTIKEL__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__MODUL = MAGNETARTIKEL__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__UNIT_NO = MAGNETARTIKEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL__IN_ZAEHLRICHTUNG = MAGNETARTIKEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Formsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMSIGNAL_FEATURE_COUNT = MAGNETARTIKEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.FormvorsignalImpl <em>Formvorsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.FormvorsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormvorsignal()
	 * @generated
	 */
	int FORMVORSIGNAL = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__NAME = FORMSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__ABSCHNITT = FORMSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__SPULEN = FORMSIGNAL__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__MODUL = FORMSIGNAL__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__UNIT_NO = FORMSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL__IN_ZAEHLRICHTUNG = FORMSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The number of structural features of the '<em>Formvorsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMVORSIGNAL_FEATURE_COUNT = FORMSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GleisImpl <em>Gleis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GleisImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleis()
	 * @generated
	 */
	int GLEIS = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__NAME = GLEISTEIL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__ABSCHNITT = GLEISTEIL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__TEILE = GLEISTEIL__TEILE;

	/**
	 * The feature id for the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__AIN_ZAEHLRICHTUNG = GLEISTEIL__AIN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__IST_ABZWEIG = GLEISTEIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ist Hauptgleis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__IST_HAUPTGLEIS = GLEISTEIL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__A = GLEISTEIL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS__B = GLEISTEIL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Gleis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEIS_FEATURE_COUNT = GLEISTEIL_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.BlocksignalImpl <em>Blocksignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.BlocksignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBlocksignal()
	 * @generated
	 */
	int BLOCKSIGNAL = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL__NAME = HAUPTLICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL__ABSCHNITT = HAUPTLICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL__UNIT_NO = HAUPTLICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL__IN_ZAEHLRICHTUNG = HAUPTLICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL__ANSCHLUSS = HAUPTLICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Blocksignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCKSIGNAL_FEATURE_COUNT = HAUPTLICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.VorsignalImpl <em>Vorsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.VorsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getVorsignal()
	 * @generated
	 */
	int VORSIGNAL = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL__NAME = LICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL__ABSCHNITT = LICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL__UNIT_NO = LICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL__IN_ZAEHLRICHTUNG = LICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL__ANSCHLUSS = LICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Vorsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSIGNAL_FEATURE_COUNT = LICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.DKWImpl <em>DKW</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.DKWImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getDKW()
	 * @generated
	 */
	int DKW = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__NAME = VERZWEIGUNG__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__ABSCHNITT = VERZWEIGUNG__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Teile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__TEILE = VERZWEIGUNG__TEILE;

	/**
	 * The feature id for the '<em><b>AIn Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__AIN_ZAEHLRICHTUNG = VERZWEIGUNG__AIN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__SPULEN = VERZWEIGUNG__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__MODUL = VERZWEIGUNG__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__UNIT_NO = VERZWEIGUNG__UNIT_NO;

	/**
	 * The feature id for the '<em><b>Neu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__NEU = VERZWEIGUNG__NEU;

	/**
	 * The feature id for the '<em><b>Ad Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__AD_IST_ABZWEIG = VERZWEIGUNG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bc Ist Abzweig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__BC_IST_ABZWEIG = VERZWEIGUNG_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__A = VERZWEIGUNG_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>C</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__C = VERZWEIGUNG_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__B = VERZWEIGUNG_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>D</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW__D = VERZWEIGUNG_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>DKW</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DKW_FEATURE_COUNT = VERZWEIGUNG_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.GleissperrsignalImpl <em>Gleissperrsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.GleissperrsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleissperrsignal()
	 * @generated
	 */
	int GLEISSPERRSIGNAL = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL__NAME = LICHTSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL__ABSCHNITT = LICHTSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL__UNIT_NO = LICHTSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL__IN_ZAEHLRICHTUNG = LICHTSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The feature id for the '<em><b>Anschluss</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL__ANSCHLUSS = LICHTSIGNAL__ANSCHLUSS;

	/**
	 * The number of structural features of the '<em>Gleissperrsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLEISSPERRSIGNAL_FEATURE_COUNT = LICHTSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.FormhauptsignalImpl <em>Formhauptsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.FormhauptsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormhauptsignal()
	 * @generated
	 */
	int FORMHAUPTSIGNAL = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__NAME = FORMSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__ABSCHNITT = FORMSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__SPULEN = FORMSIGNAL__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__MODUL = FORMSIGNAL__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__UNIT_NO = FORMSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL__IN_ZAEHLRICHTUNG = FORMSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The number of structural features of the '<em>Formhauptsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMHAUPTSIGNAL_FEATURE_COUNT = FORMSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.FormgleissperrsignalImpl <em>Formgleissperrsignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.FormgleissperrsignalImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormgleissperrsignal()
	 * @generated
	 */
	int FORMGLEISSPERRSIGNAL = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__NAME = FORMSIGNAL__NAME;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__ABSCHNITT = FORMSIGNAL__ABSCHNITT;

	/**
	 * The feature id for the '<em><b>Spulen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__SPULEN = FORMSIGNAL__SPULEN;

	/**
	 * The feature id for the '<em><b>Modul</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__MODUL = FORMSIGNAL__MODUL;

	/**
	 * The feature id for the '<em><b>Unit no</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__UNIT_NO = FORMSIGNAL__UNIT_NO;

	/**
	 * The feature id for the '<em><b>In Zaehlrichtung</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL__IN_ZAEHLRICHTUNG = FORMSIGNAL__IN_ZAEHLRICHTUNG;

	/**
	 * The number of structural features of the '<em>Formgleissperrsignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMGLEISSPERRSIGNAL_FEATURE_COUNT = FORMSIGNAL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.morknet.mrw.metamodel.impl.StreckeImpl <em>Strecke</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.morknet.mrw.metamodel.impl.StreckeImpl
	 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getStrecke()
	 * @generated
	 */
	int STRECKE = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRECKE__NAME = GRUPPE__NAME;

	/**
	 * The feature id for the '<em><b>Modell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRECKE__MODELL = GRUPPE__MODELL;

	/**
	 * The feature id for the '<em><b>Abschnitt</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRECKE__ABSCHNITT = GRUPPE__ABSCHNITT;

	/**
	 * The number of structural features of the '<em>Strecke</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRECKE_FEATURE_COUNT = GRUPPE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Anschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anschluss</em>'.
	 * @see de.morknet.mrw.metamodel.Anschluss
	 * @generated
	 */
	EClass getAnschluss();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Anschluss#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see de.morknet.mrw.metamodel.Anschluss#getNumber()
	 * @see #getAnschluss()
	 * @generated
	 */
	EAttribute getAnschluss_Number();

	/**
	 * Returns the meta object for the reference list '{@link de.morknet.mrw.metamodel.Anschluss#getLichtsignal <em>Lichtsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Lichtsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Anschluss#getLichtsignal()
	 * @see #getAnschluss()
	 * @generated
	 */
	EReference getAnschluss_Lichtsignal();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Anschluss#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Controller</em>'.
	 * @see de.morknet.mrw.metamodel.Anschluss#getController()
	 * @see #getAnschluss()
	 * @generated
	 */
	EReference getAnschluss_Controller();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Lichtsignal <em>Lichtsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lichtsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Lichtsignal
	 * @generated
	 */
	EClass getLichtsignal();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Lichtsignal#getAnschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Anschluss</em>'.
	 * @see de.morknet.mrw.metamodel.Lichtsignal#getAnschluss()
	 * @see #getLichtsignal()
	 * @generated
	 */
	EReference getLichtsignal_Anschluss();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see de.morknet.mrw.metamodel.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Signal#isInZaehlrichtung <em>In Zaehlrichtung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Zaehlrichtung</em>'.
	 * @see de.morknet.mrw.metamodel.Signal#isInZaehlrichtung()
	 * @see #getSignal()
	 * @generated
	 */
	EAttribute getSignal_InZaehlrichtung();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Bauelement <em>Bauelement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bauelement</em>'.
	 * @see de.morknet.mrw.metamodel.Bauelement
	 * @generated
	 */
	EClass getBauelement();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Bauelement#getAbschnitt <em>Abschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Abschnitt</em>'.
	 * @see de.morknet.mrw.metamodel.Bauelement#getAbschnitt()
	 * @see #getBauelement()
	 * @generated
	 */
	EReference getBauelement_Abschnitt();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see de.morknet.mrw.metamodel.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Element#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.morknet.mrw.metamodel.Element#getName()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Name();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gleisabschnitt <em>Gleisabschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gleisabschnitt</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt
	 * @generated
	 */
	EClass getGleisabschnitt();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getGruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Gruppe</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getGruppe()
	 * @see #getGleisabschnitt()
	 * @generated
	 */
	EReference getGleisabschnitt_Gruppe();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getModul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Modul</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getModul()
	 * @see #getGleisabschnitt()
	 * @generated
	 */
	EReference getGleisabschnitt_Modul();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Gleisabschnitt#getBauelement <em>Bauelement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bauelement</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt#getBauelement()
	 * @see #getGleisabschnitt()
	 * @generated
	 */
	EReference getGleisabschnitt_Bauelement();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unit</em>'.
	 * @see de.morknet.mrw.metamodel.Unit
	 * @generated
	 */
	EClass getUnit();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Unit#getUnit_no <em>Unit no</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit no</em>'.
	 * @see de.morknet.mrw.metamodel.Unit#getUnit_no()
	 * @see #getUnit()
	 * @generated
	 */
	EAttribute getUnit_Unit_no();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gruppe</em>'.
	 * @see de.morknet.mrw.metamodel.Gruppe
	 * @generated
	 */
	EClass getGruppe();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Gruppe#getModell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Modell</em>'.
	 * @see de.morknet.mrw.metamodel.Gruppe#getModell()
	 * @see #getGruppe()
	 * @generated
	 */
	EReference getGruppe_Modell();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Gruppe#getAbschnitt <em>Abschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Abschnitt</em>'.
	 * @see de.morknet.mrw.metamodel.Gruppe#getAbschnitt()
	 * @see #getGruppe()
	 * @generated
	 */
	EReference getGruppe_Abschnitt();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Modell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modell</em>'.
	 * @see de.morknet.mrw.metamodel.Modell
	 * @generated
	 */
	EClass getModell();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Modell#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Controller</em>'.
	 * @see de.morknet.mrw.metamodel.Modell#getController()
	 * @see #getModell()
	 * @generated
	 */
	EReference getModell_Controller();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Modell#getGruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gruppe</em>'.
	 * @see de.morknet.mrw.metamodel.Modell#getGruppe()
	 * @see #getModell()
	 * @generated
	 */
	EReference getModell_Gruppe();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Controller <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Controller</em>'.
	 * @see de.morknet.mrw.metamodel.Controller
	 * @generated
	 */
	EClass getController();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Controller#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.morknet.mrw.metamodel.Controller#getId()
	 * @see #getController()
	 * @generated
	 */
	EAttribute getController_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Controller#getAnschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Anschluss</em>'.
	 * @see de.morknet.mrw.metamodel.Controller#getAnschluss()
	 * @see #getController()
	 * @generated
	 */
	EReference getController_Anschluss();

	/**
	 * Returns the meta object for the containment reference list '{@link de.morknet.mrw.metamodel.Controller#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modules</em>'.
	 * @see de.morknet.mrw.metamodel.Controller#getModules()
	 * @see #getController()
	 * @generated
	 */
	EReference getController_Modules();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Controller#getModell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Modell</em>'.
	 * @see de.morknet.mrw.metamodel.Controller#getModell()
	 * @see #getController()
	 * @generated
	 */
	EReference getController_Modell();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Modul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modul</em>'.
	 * @see de.morknet.mrw.metamodel.Modul
	 * @generated
	 */
	EClass getModul();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Modul#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see de.morknet.mrw.metamodel.Modul#getNumber()
	 * @see #getModul()
	 * @generated
	 */
	EAttribute getModul_Number();

	/**
	 * Returns the meta object for the container reference '{@link de.morknet.mrw.metamodel.Modul#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Controller</em>'.
	 * @see de.morknet.mrw.metamodel.Modul#getController()
	 * @see #getModul()
	 * @generated
	 */
	EReference getModul_Controller();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gleismodul <em>Gleismodul</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gleismodul</em>'.
	 * @see de.morknet.mrw.metamodel.Gleismodul
	 * @generated
	 */
	EClass getGleismodul();

	/**
	 * Returns the meta object for the reference list '{@link de.morknet.mrw.metamodel.Gleismodul#getAbschnitte <em>Abschnitte</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Abschnitte</em>'.
	 * @see de.morknet.mrw.metamodel.Gleismodul#getAbschnitte()
	 * @see #getGleismodul()
	 * @generated
	 */
	EReference getGleismodul_Abschnitte();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Ausfahrsignal <em>Ausfahrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ausfahrsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Ausfahrsignal
	 * @generated
	 */
	EClass getAusfahrsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Hauptlichtsignal <em>Hauptlichtsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hauptlichtsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Hauptlichtsignal
	 * @generated
	 */
	EClass getHauptlichtsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Bahnhof <em>Bahnhof</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bahnhof</em>'.
	 * @see de.morknet.mrw.metamodel.Bahnhof
	 * @generated
	 */
	EClass getBahnhof();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Verzweigung <em>Verzweigung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Verzweigung</em>'.
	 * @see de.morknet.mrw.metamodel.Verzweigung
	 * @generated
	 */
	EClass getVerzweigung();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Verzweigung#isNeu <em>Neu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Neu</em>'.
	 * @see de.morknet.mrw.metamodel.Verzweigung#isNeu()
	 * @see #getVerzweigung()
	 * @generated
	 */
	EAttribute getVerzweigung_Neu();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Magnetartikel <em>Magnetartikel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Magnetartikel</em>'.
	 * @see de.morknet.mrw.metamodel.Magnetartikel
	 * @generated
	 */
	EClass getMagnetartikel();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Magnetartikel#getSpulen <em>Spulen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spulen</em>'.
	 * @see de.morknet.mrw.metamodel.Magnetartikel#getSpulen()
	 * @see #getMagnetartikel()
	 * @generated
	 */
	EAttribute getMagnetartikel_Spulen();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Magnetartikel#getModul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Modul</em>'.
	 * @see de.morknet.mrw.metamodel.Magnetartikel#getModul()
	 * @see #getMagnetartikel()
	 * @generated
	 */
	EReference getMagnetartikel_Modul();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Impulsmodul <em>Impulsmodul</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Impulsmodul</em>'.
	 * @see de.morknet.mrw.metamodel.Impulsmodul
	 * @generated
	 */
	EClass getImpulsmodul();

	/**
	 * Returns the meta object for the reference list '{@link de.morknet.mrw.metamodel.Impulsmodul#getMagnetartikel <em>Magnetartikel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Magnetartikel</em>'.
	 * @see de.morknet.mrw.metamodel.Impulsmodul#getMagnetartikel()
	 * @see #getImpulsmodul()
	 * @generated
	 */
	EReference getImpulsmodul_Magnetartikel();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gleisteil <em>Gleisteil</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gleisteil</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisteil
	 * @generated
	 */
	EClass getGleisteil();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Gleisteil#getTeile <em>Teile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Teile</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisteil#getTeile()
	 * @see #getGleisteil()
	 * @generated
	 */
	EAttribute getGleisteil_Teile();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Gleisteil#isAInZaehlrichtung <em>AIn Zaehlrichtung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>AIn Zaehlrichtung</em>'.
	 * @see de.morknet.mrw.metamodel.Gleisteil#isAInZaehlrichtung()
	 * @see #getGleisteil()
	 * @generated
	 */
	EAttribute getGleisteil_AInZaehlrichtung();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Einfahrsignal <em>Einfahrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Einfahrsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Einfahrsignal
	 * @generated
	 */
	EClass getEinfahrsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Weiche <em>Weiche</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weiche</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche
	 * @generated
	 */
	EClass getWeiche();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Weiche#isCIstAbzweig <em>CIst Abzweig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CIst Abzweig</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#isCIstAbzweig()
	 * @see #getWeiche()
	 * @generated
	 */
	EAttribute getWeiche_CIstAbzweig();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Weiche#isBIstAbzweig <em>BIst Abzweig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BIst Abzweig</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#isBIstAbzweig()
	 * @see #getWeiche()
	 * @generated
	 */
	EAttribute getWeiche_BIstAbzweig();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Weiche#isBIstBevorzugt <em>BIst Bevorzugt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BIst Bevorzugt</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#isBIstBevorzugt()
	 * @see #getWeiche()
	 * @generated
	 */
	EAttribute getWeiche_BIstBevorzugt();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Weiche#isCIstBevorzugt <em>CIst Bevorzugt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CIst Bevorzugt</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#isCIstBevorzugt()
	 * @see #getWeiche()
	 * @generated
	 */
	EAttribute getWeiche_CIstBevorzugt();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Weiche#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#getB()
	 * @see #getWeiche()
	 * @generated
	 */
	EReference getWeiche_B();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Weiche#getC <em>C</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>C</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#getC()
	 * @see #getWeiche()
	 * @generated
	 */
	EReference getWeiche_C();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Weiche#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see de.morknet.mrw.metamodel.Weiche#getA()
	 * @see #getWeiche()
	 * @generated
	 */
	EReference getWeiche_A();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Formvorsignal <em>Formvorsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formvorsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Formvorsignal
	 * @generated
	 */
	EClass getFormvorsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Formsignal <em>Formsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Formsignal
	 * @generated
	 */
	EClass getFormsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gleis <em>Gleis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gleis</em>'.
	 * @see de.morknet.mrw.metamodel.Gleis
	 * @generated
	 */
	EClass getGleis();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Gleis#isIstAbzweig <em>Ist Abzweig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ist Abzweig</em>'.
	 * @see de.morknet.mrw.metamodel.Gleis#isIstAbzweig()
	 * @see #getGleis()
	 * @generated
	 */
	EAttribute getGleis_IstAbzweig();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.Gleis#isIstHauptgleis <em>Ist Hauptgleis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ist Hauptgleis</em>'.
	 * @see de.morknet.mrw.metamodel.Gleis#isIstHauptgleis()
	 * @see #getGleis()
	 * @generated
	 */
	EAttribute getGleis_IstHauptgleis();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Gleis#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see de.morknet.mrw.metamodel.Gleis#getA()
	 * @see #getGleis()
	 * @generated
	 */
	EReference getGleis_A();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.Gleis#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see de.morknet.mrw.metamodel.Gleis#getB()
	 * @see #getGleis()
	 * @generated
	 */
	EReference getGleis_B();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Blocksignal <em>Blocksignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Blocksignal</em>'.
	 * @see de.morknet.mrw.metamodel.Blocksignal
	 * @generated
	 */
	EClass getBlocksignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Vorsignal <em>Vorsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vorsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Vorsignal
	 * @generated
	 */
	EClass getVorsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.DKW <em>DKW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DKW</em>'.
	 * @see de.morknet.mrw.metamodel.DKW
	 * @generated
	 */
	EClass getDKW();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.DKW#isAdIstAbzweig <em>Ad Ist Abzweig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ad Ist Abzweig</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#isAdIstAbzweig()
	 * @see #getDKW()
	 * @generated
	 */
	EAttribute getDKW_AdIstAbzweig();

	/**
	 * Returns the meta object for the attribute '{@link de.morknet.mrw.metamodel.DKW#isBcIstAbzweig <em>Bc Ist Abzweig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bc Ist Abzweig</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#isBcIstAbzweig()
	 * @see #getDKW()
	 * @generated
	 */
	EAttribute getDKW_BcIstAbzweig();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.DKW#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#getA()
	 * @see #getDKW()
	 * @generated
	 */
	EReference getDKW_A();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.DKW#getC <em>C</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>C</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#getC()
	 * @see #getDKW()
	 * @generated
	 */
	EReference getDKW_C();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.DKW#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#getB()
	 * @see #getDKW()
	 * @generated
	 */
	EReference getDKW_B();

	/**
	 * Returns the meta object for the reference '{@link de.morknet.mrw.metamodel.DKW#getD <em>D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>D</em>'.
	 * @see de.morknet.mrw.metamodel.DKW#getD()
	 * @see #getDKW()
	 * @generated
	 */
	EReference getDKW_D();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Gleissperrsignal <em>Gleissperrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gleissperrsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Gleissperrsignal
	 * @generated
	 */
	EClass getGleissperrsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Formhauptsignal <em>Formhauptsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formhauptsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Formhauptsignal
	 * @generated
	 */
	EClass getFormhauptsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Formgleissperrsignal <em>Formgleissperrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formgleissperrsignal</em>'.
	 * @see de.morknet.mrw.metamodel.Formgleissperrsignal
	 * @generated
	 */
	EClass getFormgleissperrsignal();

	/**
	 * Returns the meta object for class '{@link de.morknet.mrw.metamodel.Strecke <em>Strecke</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strecke</em>'.
	 * @see de.morknet.mrw.metamodel.Strecke
	 * @generated
	 */
	EClass getStrecke();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelrailwayFactory getModelrailwayFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.AnschlussImpl <em>Anschluss</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.AnschlussImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getAnschluss()
		 * @generated
		 */
		EClass ANSCHLUSS = eINSTANCE.getAnschluss();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANSCHLUSS__NUMBER = eINSTANCE.getAnschluss_Number();

		/**
		 * The meta object literal for the '<em><b>Lichtsignal</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANSCHLUSS__LICHTSIGNAL = eINSTANCE.getAnschluss_Lichtsignal();

		/**
		 * The meta object literal for the '<em><b>Controller</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANSCHLUSS__CONTROLLER = eINSTANCE.getAnschluss_Controller();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.LichtsignalImpl <em>Lichtsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.LichtsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getLichtsignal()
		 * @generated
		 */
		EClass LICHTSIGNAL = eINSTANCE.getLichtsignal();

		/**
		 * The meta object literal for the '<em><b>Anschluss</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LICHTSIGNAL__ANSCHLUSS = eINSTANCE.getLichtsignal_Anschluss();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.SignalImpl <em>Signal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.SignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '<em><b>In Zaehlrichtung</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNAL__IN_ZAEHLRICHTUNG = eINSTANCE.getSignal_InZaehlrichtung();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.BauelementImpl <em>Bauelement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.BauelementImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBauelement()
		 * @generated
		 */
		EClass BAUELEMENT = eINSTANCE.getBauelement();

		/**
		 * The meta object literal for the '<em><b>Abschnitt</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BAUELEMENT__ABSCHNITT = eINSTANCE.getBauelement_Abschnitt();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.ElementImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__NAME = eINSTANCE.getElement_Name();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GleisabschnittImpl <em>Gleisabschnitt</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GleisabschnittImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleisabschnitt()
		 * @generated
		 */
		EClass GLEISABSCHNITT = eINSTANCE.getGleisabschnitt();

		/**
		 * The meta object literal for the '<em><b>Gruppe</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEISABSCHNITT__GRUPPE = eINSTANCE.getGleisabschnitt_Gruppe();

		/**
		 * The meta object literal for the '<em><b>Modul</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEISABSCHNITT__MODUL = eINSTANCE.getGleisabschnitt_Modul();

		/**
		 * The meta object literal for the '<em><b>Bauelement</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEISABSCHNITT__BAUELEMENT = eINSTANCE.getGleisabschnitt_Bauelement();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.UnitImpl <em>Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.UnitImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getUnit()
		 * @generated
		 */
		EClass UNIT = eINSTANCE.getUnit();

		/**
		 * The meta object literal for the '<em><b>Unit no</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIT__UNIT_NO = eINSTANCE.getUnit_Unit_no();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GruppeImpl <em>Gruppe</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GruppeImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGruppe()
		 * @generated
		 */
		EClass GRUPPE = eINSTANCE.getGruppe();

		/**
		 * The meta object literal for the '<em><b>Modell</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRUPPE__MODELL = eINSTANCE.getGruppe_Modell();

		/**
		 * The meta object literal for the '<em><b>Abschnitt</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRUPPE__ABSCHNITT = eINSTANCE.getGruppe_Abschnitt();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.ModellImpl <em>Modell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.ModellImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getModell()
		 * @generated
		 */
		EClass MODELL = eINSTANCE.getModell();

		/**
		 * The meta object literal for the '<em><b>Controller</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELL__CONTROLLER = eINSTANCE.getModell_Controller();

		/**
		 * The meta object literal for the '<em><b>Gruppe</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELL__GRUPPE = eINSTANCE.getModell_Gruppe();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.ControllerImpl <em>Controller</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.ControllerImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getController()
		 * @generated
		 */
		EClass CONTROLLER = eINSTANCE.getController();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROLLER__ID = eINSTANCE.getController_Id();

		/**
		 * The meta object literal for the '<em><b>Anschluss</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROLLER__ANSCHLUSS = eINSTANCE.getController_Anschluss();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROLLER__MODULES = eINSTANCE.getController_Modules();

		/**
		 * The meta object literal for the '<em><b>Modell</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROLLER__MODELL = eINSTANCE.getController_Modell();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.ModulImpl <em>Modul</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.ModulImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getModul()
		 * @generated
		 */
		EClass MODUL = eINSTANCE.getModul();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODUL__NUMBER = eINSTANCE.getModul_Number();

		/**
		 * The meta object literal for the '<em><b>Controller</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODUL__CONTROLLER = eINSTANCE.getModul_Controller();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GleismodulImpl <em>Gleismodul</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GleismodulImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleismodul()
		 * @generated
		 */
		EClass GLEISMODUL = eINSTANCE.getGleismodul();

		/**
		 * The meta object literal for the '<em><b>Abschnitte</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEISMODUL__ABSCHNITTE = eINSTANCE.getGleismodul_Abschnitte();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.AusfahrsignalImpl <em>Ausfahrsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.AusfahrsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getAusfahrsignal()
		 * @generated
		 */
		EClass AUSFAHRSIGNAL = eINSTANCE.getAusfahrsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.HauptlichtsignalImpl <em>Hauptlichtsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.HauptlichtsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getHauptlichtsignal()
		 * @generated
		 */
		EClass HAUPTLICHTSIGNAL = eINSTANCE.getHauptlichtsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.BahnhofImpl <em>Bahnhof</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.BahnhofImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBahnhof()
		 * @generated
		 */
		EClass BAHNHOF = eINSTANCE.getBahnhof();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.VerzweigungImpl <em>Verzweigung</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.VerzweigungImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getVerzweigung()
		 * @generated
		 */
		EClass VERZWEIGUNG = eINSTANCE.getVerzweigung();

		/**
		 * The meta object literal for the '<em><b>Neu</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERZWEIGUNG__NEU = eINSTANCE.getVerzweigung_Neu();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.MagnetartikelImpl <em>Magnetartikel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.MagnetartikelImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getMagnetartikel()
		 * @generated
		 */
		EClass MAGNETARTIKEL = eINSTANCE.getMagnetartikel();

		/**
		 * The meta object literal for the '<em><b>Spulen</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAGNETARTIKEL__SPULEN = eINSTANCE.getMagnetartikel_Spulen();

		/**
		 * The meta object literal for the '<em><b>Modul</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAGNETARTIKEL__MODUL = eINSTANCE.getMagnetartikel_Modul();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.ImpulsmodulImpl <em>Impulsmodul</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.ImpulsmodulImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getImpulsmodul()
		 * @generated
		 */
		EClass IMPULSMODUL = eINSTANCE.getImpulsmodul();

		/**
		 * The meta object literal for the '<em><b>Magnetartikel</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPULSMODUL__MAGNETARTIKEL = eINSTANCE.getImpulsmodul_Magnetartikel();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GleisteilImpl <em>Gleisteil</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GleisteilImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleisteil()
		 * @generated
		 */
		EClass GLEISTEIL = eINSTANCE.getGleisteil();

		/**
		 * The meta object literal for the '<em><b>Teile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLEISTEIL__TEILE = eINSTANCE.getGleisteil_Teile();

		/**
		 * The meta object literal for the '<em><b>AIn Zaehlrichtung</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLEISTEIL__AIN_ZAEHLRICHTUNG = eINSTANCE.getGleisteil_AInZaehlrichtung();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.EinfahrsignalImpl <em>Einfahrsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.EinfahrsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getEinfahrsignal()
		 * @generated
		 */
		EClass EINFAHRSIGNAL = eINSTANCE.getEinfahrsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.WeicheImpl <em>Weiche</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.WeicheImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getWeiche()
		 * @generated
		 */
		EClass WEICHE = eINSTANCE.getWeiche();

		/**
		 * The meta object literal for the '<em><b>CIst Abzweig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEICHE__CIST_ABZWEIG = eINSTANCE.getWeiche_CIstAbzweig();

		/**
		 * The meta object literal for the '<em><b>BIst Abzweig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEICHE__BIST_ABZWEIG = eINSTANCE.getWeiche_BIstAbzweig();

		/**
		 * The meta object literal for the '<em><b>BIst Bevorzugt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEICHE__BIST_BEVORZUGT = eINSTANCE.getWeiche_BIstBevorzugt();

		/**
		 * The meta object literal for the '<em><b>CIst Bevorzugt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEICHE__CIST_BEVORZUGT = eINSTANCE.getWeiche_CIstBevorzugt();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEICHE__B = eINSTANCE.getWeiche_B();

		/**
		 * The meta object literal for the '<em><b>C</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEICHE__C = eINSTANCE.getWeiche_C();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEICHE__A = eINSTANCE.getWeiche_A();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.FormvorsignalImpl <em>Formvorsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.FormvorsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormvorsignal()
		 * @generated
		 */
		EClass FORMVORSIGNAL = eINSTANCE.getFormvorsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.FormsignalImpl <em>Formsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.FormsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormsignal()
		 * @generated
		 */
		EClass FORMSIGNAL = eINSTANCE.getFormsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GleisImpl <em>Gleis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GleisImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleis()
		 * @generated
		 */
		EClass GLEIS = eINSTANCE.getGleis();

		/**
		 * The meta object literal for the '<em><b>Ist Abzweig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLEIS__IST_ABZWEIG = eINSTANCE.getGleis_IstAbzweig();

		/**
		 * The meta object literal for the '<em><b>Ist Hauptgleis</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLEIS__IST_HAUPTGLEIS = eINSTANCE.getGleis_IstHauptgleis();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEIS__A = eINSTANCE.getGleis_A();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLEIS__B = eINSTANCE.getGleis_B();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.BlocksignalImpl <em>Blocksignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.BlocksignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getBlocksignal()
		 * @generated
		 */
		EClass BLOCKSIGNAL = eINSTANCE.getBlocksignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.VorsignalImpl <em>Vorsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.VorsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getVorsignal()
		 * @generated
		 */
		EClass VORSIGNAL = eINSTANCE.getVorsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.DKWImpl <em>DKW</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.DKWImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getDKW()
		 * @generated
		 */
		EClass DKW = eINSTANCE.getDKW();

		/**
		 * The meta object literal for the '<em><b>Ad Ist Abzweig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DKW__AD_IST_ABZWEIG = eINSTANCE.getDKW_AdIstAbzweig();

		/**
		 * The meta object literal for the '<em><b>Bc Ist Abzweig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DKW__BC_IST_ABZWEIG = eINSTANCE.getDKW_BcIstAbzweig();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DKW__A = eINSTANCE.getDKW_A();

		/**
		 * The meta object literal for the '<em><b>C</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DKW__C = eINSTANCE.getDKW_C();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DKW__B = eINSTANCE.getDKW_B();

		/**
		 * The meta object literal for the '<em><b>D</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DKW__D = eINSTANCE.getDKW_D();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.GleissperrsignalImpl <em>Gleissperrsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.GleissperrsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getGleissperrsignal()
		 * @generated
		 */
		EClass GLEISSPERRSIGNAL = eINSTANCE.getGleissperrsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.FormhauptsignalImpl <em>Formhauptsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.FormhauptsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormhauptsignal()
		 * @generated
		 */
		EClass FORMHAUPTSIGNAL = eINSTANCE.getFormhauptsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.FormgleissperrsignalImpl <em>Formgleissperrsignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.FormgleissperrsignalImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getFormgleissperrsignal()
		 * @generated
		 */
		EClass FORMGLEISSPERRSIGNAL = eINSTANCE.getFormgleissperrsignal();

		/**
		 * The meta object literal for the '{@link de.morknet.mrw.metamodel.impl.StreckeImpl <em>Strecke</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.morknet.mrw.metamodel.impl.StreckeImpl
		 * @see de.morknet.mrw.metamodel.impl.ModelrailwayPackageImpl#getStrecke()
		 * @generated
		 */
		EClass STRECKE = eINSTANCE.getStrecke();

	}

} //ModelrailwayPackage
