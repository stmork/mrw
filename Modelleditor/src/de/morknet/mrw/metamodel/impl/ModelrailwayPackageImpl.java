/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.impl;

import de.morknet.mrw.metamodel.Anschluss;
import de.morknet.mrw.metamodel.Ausfahrsignal;
import de.morknet.mrw.metamodel.Bahnhof;
import de.morknet.mrw.metamodel.Bauelement;
import de.morknet.mrw.metamodel.Beleuchtungsmodul;
import de.morknet.mrw.metamodel.Blocksignal;
import de.morknet.mrw.metamodel.Controller;
import de.morknet.mrw.metamodel.Einfahrsignal;
import de.morknet.mrw.metamodel.Element;
import de.morknet.mrw.metamodel.Formgleissperrsignal;
import de.morknet.mrw.metamodel.Formhauptsignal;
import de.morknet.mrw.metamodel.Formsignal;
import de.morknet.mrw.metamodel.Formvorsignal;
import de.morknet.mrw.metamodel.Gleis;
import de.morknet.mrw.metamodel.Gleisabschnitt;
import de.morknet.mrw.metamodel.Gleismodul;
import de.morknet.mrw.metamodel.Gleissperrsignal;
import de.morknet.mrw.metamodel.Gleisteil;
import de.morknet.mrw.metamodel.Gruppe;
import de.morknet.mrw.metamodel.Hauptlichtsignal;
import de.morknet.mrw.metamodel.Impulsmodul;
import de.morknet.mrw.metamodel.Lampe;
import de.morknet.mrw.metamodel.Lichtsignal;
import de.morknet.mrw.metamodel.Magnetartikel;
import de.morknet.mrw.metamodel.Modell;
import de.morknet.mrw.metamodel.ModelrailwayFactory;
import de.morknet.mrw.metamodel.ModelrailwayPackage;
import de.morknet.mrw.metamodel.Modul;
import de.morknet.mrw.metamodel.Signal;
import de.morknet.mrw.metamodel.Strecke;
import de.morknet.mrw.metamodel.Unit;
import de.morknet.mrw.metamodel.Verzweigung;
import de.morknet.mrw.metamodel.Vorsignal;
import de.morknet.mrw.metamodel.Weiche;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelrailwayPackageImpl extends EPackageImpl implements ModelrailwayPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anschlussEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lichtsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bauelementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gleisabschnittEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gruppeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controllerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modulEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gleismodulEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ausfahrsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hauptlichtsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bahnhofEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass verzweigungEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass magnetartikelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass impulsmodulEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gleisteilEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass einfahrsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weicheEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formvorsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gleisEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blocksignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vorsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dkwEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gleissperrsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formhauptsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formgleissperrsignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass streckeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beleuchtungsmodulEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lampeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.morknet.mrw.metamodel.ModelrailwayPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelrailwayPackageImpl() {
		super(eNS_URI, ModelrailwayFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelrailwayPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelrailwayPackage init() {
		if (isInited) return (ModelrailwayPackage)EPackage.Registry.INSTANCE.getEPackage(ModelrailwayPackage.eNS_URI);

		// Obtain or create and register package
		ModelrailwayPackageImpl theModelrailwayPackage = (ModelrailwayPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelrailwayPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelrailwayPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theModelrailwayPackage.createPackageContents();

		// Initialize created meta-data
		theModelrailwayPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelrailwayPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelrailwayPackage.eNS_URI, theModelrailwayPackage);
		return theModelrailwayPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnschluss() {
		return anschlussEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnschluss_Nummer() {
		return (EAttribute)anschlussEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnschluss_Lichtsignale() {
		return (EReference)anschlussEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnschluss_Controller() {
		return (EReference)anschlussEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLichtsignal() {
		return lichtsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLichtsignal_Anschluss() {
		return (EReference)lichtsignalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignal() {
		return signalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignal_InZaehlrichtung() {
		return (EAttribute)signalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBauelement() {
		return bauelementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBauelement_Abschnitt() {
		return (EReference)bauelementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElement_Name() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGleisabschnitt() {
		return gleisabschnittEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleisabschnitt_Gruppe() {
		return (EReference)gleisabschnittEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleisabschnitt_Modul() {
		return (EReference)gleisabschnittEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleisabschnitt_Bauelement() {
		return (EReference)gleisabschnittEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnit() {
		return unitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnit_Unit_no() {
		return (EAttribute)unitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGruppe() {
		return gruppeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGruppe_Modell() {
		return (EReference)gruppeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGruppe_Abschnitt() {
		return (EReference)gruppeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModell() {
		return modellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModell_Controller() {
		return (EReference)modellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModell_Gruppe() {
		return (EReference)modellEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getController() {
		return controllerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getController_Id() {
		return (EAttribute)controllerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getController_Anschluesse() {
		return (EReference)controllerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getController_Module() {
		return (EReference)controllerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getController_Modell() {
		return (EReference)controllerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModul() {
		return modulEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModul_Nummer() {
		return (EAttribute)modulEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModul_Controller() {
		return (EReference)modulEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGleismodul() {
		return gleismodulEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleismodul_Abschnitte() {
		return (EReference)gleismodulEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAusfahrsignal() {
		return ausfahrsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHauptlichtsignal() {
		return hauptlichtsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBahnhof() {
		return bahnhofEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVerzweigung() {
		return verzweigungEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVerzweigung_Neu() {
		return (EAttribute)verzweigungEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMagnetartikel() {
		return magnetartikelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMagnetartikel_Spulen() {
		return (EAttribute)magnetartikelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMagnetartikel_Modul() {
		return (EReference)magnetartikelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImpulsmodul() {
		return impulsmodulEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpulsmodul_Magnetartikel() {
		return (EReference)impulsmodulEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGleisteil() {
		return gleisteilEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGleisteil_Teile() {
		return (EAttribute)gleisteilEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGleisteil_AInZaehlrichtung() {
		return (EAttribute)gleisteilEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEinfahrsignal() {
		return einfahrsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeiche() {
		return weicheEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeiche_CIstAbzweig() {
		return (EAttribute)weicheEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeiche_BIstAbzweig() {
		return (EAttribute)weicheEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeiche_BIstBevorzugt() {
		return (EAttribute)weicheEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeiche_CIstBevorzugt() {
		return (EAttribute)weicheEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeiche_B() {
		return (EReference)weicheEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeiche_C() {
		return (EReference)weicheEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeiche_A() {
		return (EReference)weicheEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormvorsignal() {
		return formvorsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormsignal() {
		return formsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGleis() {
		return gleisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGleis_IstAbzweig() {
		return (EAttribute)gleisEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGleis_IstHauptgleis() {
		return (EAttribute)gleisEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleis_A() {
		return (EReference)gleisEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGleis_B() {
		return (EReference)gleisEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlocksignal() {
		return blocksignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVorsignal() {
		return vorsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDKW() {
		return dkwEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDKW_AdIstAbzweig() {
		return (EAttribute)dkwEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDKW_BcIstAbzweig() {
		return (EAttribute)dkwEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDKW_A() {
		return (EReference)dkwEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDKW_C() {
		return (EReference)dkwEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDKW_B() {
		return (EReference)dkwEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDKW_D() {
		return (EReference)dkwEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGleissperrsignal() {
		return gleissperrsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormhauptsignal() {
		return formhauptsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormgleissperrsignal() {
		return formgleissperrsignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStrecke() {
		return streckeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeleuchtungsmodul() {
		return beleuchtungsmodulEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBeleuchtungsmodul_Lampen() {
		return (EReference)beleuchtungsmodulEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLampe() {
		return lampeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLampe_Schwellwert() {
		return (EAttribute)lampeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLampe_Typ() {
		return (EAttribute)lampeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLampe_Modul() {
		return (EReference)lampeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelrailwayFactory getModelrailwayFactory() {
		return (ModelrailwayFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		anschlussEClass = createEClass(ANSCHLUSS);
		createEAttribute(anschlussEClass, ANSCHLUSS__NUMMER);
		createEReference(anschlussEClass, ANSCHLUSS__LICHTSIGNALE);
		createEReference(anschlussEClass, ANSCHLUSS__CONTROLLER);

		lichtsignalEClass = createEClass(LICHTSIGNAL);
		createEReference(lichtsignalEClass, LICHTSIGNAL__ANSCHLUSS);

		signalEClass = createEClass(SIGNAL);
		createEAttribute(signalEClass, SIGNAL__IN_ZAEHLRICHTUNG);

		bauelementEClass = createEClass(BAUELEMENT);
		createEReference(bauelementEClass, BAUELEMENT__ABSCHNITT);

		elementEClass = createEClass(ELEMENT);
		createEAttribute(elementEClass, ELEMENT__NAME);

		gleisabschnittEClass = createEClass(GLEISABSCHNITT);
		createEReference(gleisabschnittEClass, GLEISABSCHNITT__GRUPPE);
		createEReference(gleisabschnittEClass, GLEISABSCHNITT__MODUL);
		createEReference(gleisabschnittEClass, GLEISABSCHNITT__BAUELEMENT);

		unitEClass = createEClass(UNIT);
		createEAttribute(unitEClass, UNIT__UNIT_NO);

		gruppeEClass = createEClass(GRUPPE);
		createEReference(gruppeEClass, GRUPPE__MODELL);
		createEReference(gruppeEClass, GRUPPE__ABSCHNITT);

		modellEClass = createEClass(MODELL);
		createEReference(modellEClass, MODELL__CONTROLLER);
		createEReference(modellEClass, MODELL__GRUPPE);

		controllerEClass = createEClass(CONTROLLER);
		createEAttribute(controllerEClass, CONTROLLER__ID);
		createEReference(controllerEClass, CONTROLLER__ANSCHLUESSE);
		createEReference(controllerEClass, CONTROLLER__MODULE);
		createEReference(controllerEClass, CONTROLLER__MODELL);

		modulEClass = createEClass(MODUL);
		createEAttribute(modulEClass, MODUL__NUMMER);
		createEReference(modulEClass, MODUL__CONTROLLER);

		gleismodulEClass = createEClass(GLEISMODUL);
		createEReference(gleismodulEClass, GLEISMODUL__ABSCHNITTE);

		ausfahrsignalEClass = createEClass(AUSFAHRSIGNAL);

		hauptlichtsignalEClass = createEClass(HAUPTLICHTSIGNAL);

		bahnhofEClass = createEClass(BAHNHOF);

		verzweigungEClass = createEClass(VERZWEIGUNG);
		createEAttribute(verzweigungEClass, VERZWEIGUNG__NEU);

		magnetartikelEClass = createEClass(MAGNETARTIKEL);
		createEAttribute(magnetartikelEClass, MAGNETARTIKEL__SPULEN);
		createEReference(magnetartikelEClass, MAGNETARTIKEL__MODUL);

		impulsmodulEClass = createEClass(IMPULSMODUL);
		createEReference(impulsmodulEClass, IMPULSMODUL__MAGNETARTIKEL);

		gleisteilEClass = createEClass(GLEISTEIL);
		createEAttribute(gleisteilEClass, GLEISTEIL__TEILE);
		createEAttribute(gleisteilEClass, GLEISTEIL__AIN_ZAEHLRICHTUNG);

		einfahrsignalEClass = createEClass(EINFAHRSIGNAL);

		weicheEClass = createEClass(WEICHE);
		createEAttribute(weicheEClass, WEICHE__CIST_ABZWEIG);
		createEAttribute(weicheEClass, WEICHE__BIST_ABZWEIG);
		createEAttribute(weicheEClass, WEICHE__BIST_BEVORZUGT);
		createEAttribute(weicheEClass, WEICHE__CIST_BEVORZUGT);
		createEReference(weicheEClass, WEICHE__B);
		createEReference(weicheEClass, WEICHE__C);
		createEReference(weicheEClass, WEICHE__A);

		formvorsignalEClass = createEClass(FORMVORSIGNAL);

		formsignalEClass = createEClass(FORMSIGNAL);

		gleisEClass = createEClass(GLEIS);
		createEAttribute(gleisEClass, GLEIS__IST_ABZWEIG);
		createEAttribute(gleisEClass, GLEIS__IST_HAUPTGLEIS);
		createEReference(gleisEClass, GLEIS__A);
		createEReference(gleisEClass, GLEIS__B);

		blocksignalEClass = createEClass(BLOCKSIGNAL);

		vorsignalEClass = createEClass(VORSIGNAL);

		dkwEClass = createEClass(DKW);
		createEAttribute(dkwEClass, DKW__AD_IST_ABZWEIG);
		createEAttribute(dkwEClass, DKW__BC_IST_ABZWEIG);
		createEReference(dkwEClass, DKW__A);
		createEReference(dkwEClass, DKW__C);
		createEReference(dkwEClass, DKW__B);
		createEReference(dkwEClass, DKW__D);

		gleissperrsignalEClass = createEClass(GLEISSPERRSIGNAL);

		formhauptsignalEClass = createEClass(FORMHAUPTSIGNAL);

		formgleissperrsignalEClass = createEClass(FORMGLEISSPERRSIGNAL);

		streckeEClass = createEClass(STRECKE);

		beleuchtungsmodulEClass = createEClass(BELEUCHTUNGSMODUL);
		createEReference(beleuchtungsmodulEClass, BELEUCHTUNGSMODUL__LAMPEN);

		lampeEClass = createEClass(LAMPE);
		createEAttribute(lampeEClass, LAMPE__SCHWELLWERT);
		createEAttribute(lampeEClass, LAMPE__TYP);
		createEReference(lampeEClass, LAMPE__MODUL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		lichtsignalEClass.getESuperTypes().add(this.getSignal());
		signalEClass.getESuperTypes().add(this.getBauelement());
		signalEClass.getESuperTypes().add(this.getUnit());
		bauelementEClass.getESuperTypes().add(this.getElement());
		gleisabschnittEClass.getESuperTypes().add(this.getElement());
		gleisabschnittEClass.getESuperTypes().add(this.getUnit());
		gruppeEClass.getESuperTypes().add(this.getElement());
		modellEClass.getESuperTypes().add(this.getElement());
		gleismodulEClass.getESuperTypes().add(this.getModul());
		ausfahrsignalEClass.getESuperTypes().add(this.getHauptlichtsignal());
		hauptlichtsignalEClass.getESuperTypes().add(this.getLichtsignal());
		bahnhofEClass.getESuperTypes().add(this.getGruppe());
		verzweigungEClass.getESuperTypes().add(this.getGleisteil());
		verzweigungEClass.getESuperTypes().add(this.getMagnetartikel());
		verzweigungEClass.getESuperTypes().add(this.getUnit());
		magnetartikelEClass.getESuperTypes().add(this.getBauelement());
		impulsmodulEClass.getESuperTypes().add(this.getModul());
		gleisteilEClass.getESuperTypes().add(this.getBauelement());
		einfahrsignalEClass.getESuperTypes().add(this.getHauptlichtsignal());
		weicheEClass.getESuperTypes().add(this.getVerzweigung());
		formvorsignalEClass.getESuperTypes().add(this.getFormsignal());
		formsignalEClass.getESuperTypes().add(this.getMagnetartikel());
		formsignalEClass.getESuperTypes().add(this.getSignal());
		gleisEClass.getESuperTypes().add(this.getGleisteil());
		blocksignalEClass.getESuperTypes().add(this.getHauptlichtsignal());
		vorsignalEClass.getESuperTypes().add(this.getLichtsignal());
		dkwEClass.getESuperTypes().add(this.getVerzweigung());
		gleissperrsignalEClass.getESuperTypes().add(this.getLichtsignal());
		formhauptsignalEClass.getESuperTypes().add(this.getFormsignal());
		formgleissperrsignalEClass.getESuperTypes().add(this.getFormsignal());
		streckeEClass.getESuperTypes().add(this.getGruppe());
		beleuchtungsmodulEClass.getESuperTypes().add(this.getModul());
		lampeEClass.getESuperTypes().add(this.getElement());
		lampeEClass.getESuperTypes().add(this.getUnit());

		// Initialize classes and features; add operations and parameters
		initEClass(anschlussEClass, Anschluss.class, "Anschluss", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnschluss_Nummer(), ecorePackage.getEInt(), "nummer", null, 0, 1, Anschluss.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnschluss_Lichtsignale(), this.getLichtsignal(), this.getLichtsignal_Anschluss(), "lichtsignale", null, 0, -1, Anschluss.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnschluss_Controller(), this.getController(), this.getController_Anschluesse(), "controller", null, 1, 1, Anschluss.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lichtsignalEClass, Lichtsignal.class, "Lichtsignal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLichtsignal_Anschluss(), this.getAnschluss(), this.getAnschluss_Lichtsignale(), "anschluss", null, 1, 1, Lichtsignal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signalEClass, Signal.class, "Signal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignal_InZaehlrichtung(), ecorePackage.getEBoolean(), "inZaehlrichtung", null, 0, 1, Signal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bauelementEClass, Bauelement.class, "Bauelement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBauelement_Abschnitt(), this.getGleisabschnitt(), this.getGleisabschnitt_Bauelement(), "abschnitt", null, 1, 1, Bauelement.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementEClass, Element.class, "Element", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gleisabschnittEClass, Gleisabschnitt.class, "Gleisabschnitt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGleisabschnitt_Gruppe(), this.getGruppe(), this.getGruppe_Abschnitt(), "gruppe", null, 1, 1, Gleisabschnitt.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGleisabschnitt_Modul(), this.getGleismodul(), this.getGleismodul_Abschnitte(), "modul", null, 1, 1, Gleisabschnitt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGleisabschnitt_Bauelement(), this.getBauelement(), this.getBauelement_Abschnitt(), "bauelement", null, 1, -1, Gleisabschnitt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unitEClass, Unit.class, "Unit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnit_Unit_no(), ecorePackage.getEInt(), "unit_no", null, 0, 1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gruppeEClass, Gruppe.class, "Gruppe", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGruppe_Modell(), this.getModell(), this.getModell_Gruppe(), "modell", null, 1, 1, Gruppe.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGruppe_Abschnitt(), this.getGleisabschnitt(), this.getGleisabschnitt_Gruppe(), "abschnitt", null, 0, -1, Gruppe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modellEClass, Modell.class, "Modell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModell_Controller(), this.getController(), this.getController_Modell(), "controller", null, 0, -1, Modell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModell_Gruppe(), this.getGruppe(), this.getGruppe_Modell(), "gruppe", null, 0, -1, Modell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controllerEClass, Controller.class, "Controller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getController_Id(), ecorePackage.getEInt(), "id", null, 0, 1, Controller.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getController_Anschluesse(), this.getAnschluss(), this.getAnschluss_Controller(), "anschluesse", null, 0, -1, Controller.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getController_Module(), this.getModul(), this.getModul_Controller(), "module", null, 0, -1, Controller.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getController_Modell(), this.getModell(), this.getModell_Controller(), "modell", null, 1, 1, Controller.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modulEClass, Modul.class, "Modul", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModul_Nummer(), ecorePackage.getEInt(), "nummer", null, 0, 1, Modul.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModul_Controller(), this.getController(), this.getController_Module(), "controller", null, 1, 1, Modul.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gleismodulEClass, Gleismodul.class, "Gleismodul", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGleismodul_Abschnitte(), this.getGleisabschnitt(), this.getGleisabschnitt_Modul(), "abschnitte", null, 0, 4, Gleismodul.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ausfahrsignalEClass, Ausfahrsignal.class, "Ausfahrsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(hauptlichtsignalEClass, Hauptlichtsignal.class, "Hauptlichtsignal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bahnhofEClass, Bahnhof.class, "Bahnhof", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(verzweigungEClass, Verzweigung.class, "Verzweigung", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVerzweigung_Neu(), ecorePackage.getEBoolean(), "neu", null, 0, 1, Verzweigung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(magnetartikelEClass, Magnetartikel.class, "Magnetartikel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMagnetartikel_Spulen(), ecorePackage.getEInt(), "spulen", null, 0, 1, Magnetartikel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMagnetartikel_Modul(), this.getImpulsmodul(), this.getImpulsmodul_Magnetartikel(), "modul", null, 1, 1, Magnetartikel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(impulsmodulEClass, Impulsmodul.class, "Impulsmodul", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImpulsmodul_Magnetartikel(), this.getMagnetartikel(), this.getMagnetartikel_Modul(), "magnetartikel", null, 0, -1, Impulsmodul.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gleisteilEClass, Gleisteil.class, "Gleisteil", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGleisteil_Teile(), ecorePackage.getEInt(), "teile", null, 0, 1, Gleisteil.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGleisteil_AInZaehlrichtung(), ecorePackage.getEBoolean(), "aInZaehlrichtung", null, 0, 1, Gleisteil.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(einfahrsignalEClass, Einfahrsignal.class, "Einfahrsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weicheEClass, Weiche.class, "Weiche", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeiche_CIstAbzweig(), ecorePackage.getEBoolean(), "cIstAbzweig", null, 0, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeiche_BIstAbzweig(), ecorePackage.getEBoolean(), "bIstAbzweig", null, 0, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeiche_BIstBevorzugt(), ecorePackage.getEBoolean(), "bIstBevorzugt", null, 0, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeiche_CIstBevorzugt(), ecorePackage.getEBoolean(), "cIstBevorzugt", null, 0, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeiche_B(), this.getGleisteil(), null, "b", null, 1, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeiche_C(), this.getGleisteil(), null, "c", null, 1, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeiche_A(), this.getGleisteil(), null, "a", null, 1, 1, Weiche.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formvorsignalEClass, Formvorsignal.class, "Formvorsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(formsignalEClass, Formsignal.class, "Formsignal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gleisEClass, Gleis.class, "Gleis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGleis_IstAbzweig(), ecorePackage.getEBoolean(), "istAbzweig", null, 0, 1, Gleis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGleis_IstHauptgleis(), ecorePackage.getEBoolean(), "istHauptgleis", null, 0, 1, Gleis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGleis_A(), this.getGleisteil(), null, "a", null, 1, 1, Gleis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGleis_B(), this.getGleisteil(), null, "b", null, 1, 1, Gleis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blocksignalEClass, Blocksignal.class, "Blocksignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(vorsignalEClass, Vorsignal.class, "Vorsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dkwEClass, de.morknet.mrw.metamodel.DKW.class, "DKW", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDKW_AdIstAbzweig(), ecorePackage.getEBoolean(), "adIstAbzweig", null, 0, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDKW_BcIstAbzweig(), ecorePackage.getEBoolean(), "bcIstAbzweig", null, 0, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDKW_A(), this.getGleisteil(), null, "a", null, 1, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDKW_C(), this.getGleisteil(), null, "c", null, 1, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDKW_B(), this.getGleisteil(), null, "b", null, 1, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDKW_D(), this.getGleisteil(), null, "d", null, 1, 1, de.morknet.mrw.metamodel.DKW.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gleissperrsignalEClass, Gleissperrsignal.class, "Gleissperrsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(formhauptsignalEClass, Formhauptsignal.class, "Formhauptsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(formgleissperrsignalEClass, Formgleissperrsignal.class, "Formgleissperrsignal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(streckeEClass, Strecke.class, "Strecke", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(beleuchtungsmodulEClass, Beleuchtungsmodul.class, "Beleuchtungsmodul", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBeleuchtungsmodul_Lampen(), this.getLampe(), this.getLampe_Modul(), "lampen", null, 0, 8, Beleuchtungsmodul.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lampeEClass, Lampe.class, "Lampe", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLampe_Schwellwert(), ecorePackage.getEInt(), "schwellwert", null, 1, 1, Lampe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getLampe_Typ(), ecorePackage.getEInt(), "typ", null, 1, 1, Lampe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLampe_Modul(), this.getBeleuchtungsmodul(), this.getBeleuchtungsmodul_Lampen(), "modul", null, 1, 1, Lampe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelrailwayPackageImpl
