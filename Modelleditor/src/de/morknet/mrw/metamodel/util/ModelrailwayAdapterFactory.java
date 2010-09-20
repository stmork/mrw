/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.util;

import de.morknet.mrw.metamodel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage
 * @generated
 */
public class ModelrailwayAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelrailwayPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelrailwayAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelrailwayPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelrailwaySwitch<Adapter> modelSwitch =
		new ModelrailwaySwitch<Adapter>() {
			@Override
			public Adapter caseAnschluss(Anschluss object) {
				return createAnschlussAdapter();
			}
			@Override
			public Adapter caseLichtsignal(Lichtsignal object) {
				return createLichtsignalAdapter();
			}
			@Override
			public Adapter caseSignal(Signal object) {
				return createSignalAdapter();
			}
			@Override
			public Adapter caseBauelement(Bauelement object) {
				return createBauelementAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseGleisabschnitt(Gleisabschnitt object) {
				return createGleisabschnittAdapter();
			}
			@Override
			public Adapter caseUnit(Unit object) {
				return createUnitAdapter();
			}
			@Override
			public Adapter caseGruppe(Gruppe object) {
				return createGruppeAdapter();
			}
			@Override
			public Adapter caseModell(Modell object) {
				return createModellAdapter();
			}
			@Override
			public Adapter caseController(Controller object) {
				return createControllerAdapter();
			}
			@Override
			public Adapter caseModul(Modul object) {
				return createModulAdapter();
			}
			@Override
			public Adapter caseGleismodul(Gleismodul object) {
				return createGleismodulAdapter();
			}
			@Override
			public Adapter caseAusfahrsignal(Ausfahrsignal object) {
				return createAusfahrsignalAdapter();
			}
			@Override
			public Adapter caseHauptlichtsignal(Hauptlichtsignal object) {
				return createHauptlichtsignalAdapter();
			}
			@Override
			public Adapter caseBahnhof(Bahnhof object) {
				return createBahnhofAdapter();
			}
			@Override
			public Adapter caseVerzweigung(Verzweigung object) {
				return createVerzweigungAdapter();
			}
			@Override
			public Adapter caseMagnetartikel(Magnetartikel object) {
				return createMagnetartikelAdapter();
			}
			@Override
			public Adapter caseImpulsmodul(Impulsmodul object) {
				return createImpulsmodulAdapter();
			}
			@Override
			public Adapter caseGleisteil(Gleisteil object) {
				return createGleisteilAdapter();
			}
			@Override
			public Adapter caseEinfahrsignal(Einfahrsignal object) {
				return createEinfahrsignalAdapter();
			}
			@Override
			public Adapter caseWeiche(Weiche object) {
				return createWeicheAdapter();
			}
			@Override
			public Adapter caseFormvorsignal(Formvorsignal object) {
				return createFormvorsignalAdapter();
			}
			@Override
			public Adapter caseFormsignal(Formsignal object) {
				return createFormsignalAdapter();
			}
			@Override
			public Adapter caseGleis(Gleis object) {
				return createGleisAdapter();
			}
			@Override
			public Adapter caseBlocksignal(Blocksignal object) {
				return createBlocksignalAdapter();
			}
			@Override
			public Adapter caseVorsignal(Vorsignal object) {
				return createVorsignalAdapter();
			}
			@Override
			public Adapter caseDKW(DKW object) {
				return createDKWAdapter();
			}
			@Override
			public Adapter caseGleissperrsignal(Gleissperrsignal object) {
				return createGleissperrsignalAdapter();
			}
			@Override
			public Adapter caseFormhauptsignal(Formhauptsignal object) {
				return createFormhauptsignalAdapter();
			}
			@Override
			public Adapter caseFormgleissperrsignal(Formgleissperrsignal object) {
				return createFormgleissperrsignalAdapter();
			}
			@Override
			public Adapter caseStrecke(Strecke object) {
				return createStreckeAdapter();
			}
			@Override
			public Adapter caseBeleuchtungsmodul(Beleuchtungsmodul object) {
				return createBeleuchtungsmodulAdapter();
			}
			@Override
			public Adapter caseLampe(Lampe object) {
				return createLampeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Anschluss <em>Anschluss</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Anschluss
	 * @generated
	 */
	public Adapter createAnschlussAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Lichtsignal <em>Lichtsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Lichtsignal
	 * @generated
	 */
	public Adapter createLichtsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Signal
	 * @generated
	 */
	public Adapter createSignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Bauelement <em>Bauelement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Bauelement
	 * @generated
	 */
	public Adapter createBauelementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gleisabschnitt <em>Gleisabschnitt</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gleisabschnitt
	 * @generated
	 */
	public Adapter createGleisabschnittAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Unit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Unit
	 * @generated
	 */
	public Adapter createUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gruppe <em>Gruppe</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gruppe
	 * @generated
	 */
	public Adapter createGruppeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Modell <em>Modell</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Modell
	 * @generated
	 */
	public Adapter createModellAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Controller <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Controller
	 * @generated
	 */
	public Adapter createControllerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Modul <em>Modul</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Modul
	 * @generated
	 */
	public Adapter createModulAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gleismodul <em>Gleismodul</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gleismodul
	 * @generated
	 */
	public Adapter createGleismodulAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Ausfahrsignal <em>Ausfahrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Ausfahrsignal
	 * @generated
	 */
	public Adapter createAusfahrsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Hauptlichtsignal <em>Hauptlichtsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Hauptlichtsignal
	 * @generated
	 */
	public Adapter createHauptlichtsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Bahnhof <em>Bahnhof</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Bahnhof
	 * @generated
	 */
	public Adapter createBahnhofAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Verzweigung <em>Verzweigung</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Verzweigung
	 * @generated
	 */
	public Adapter createVerzweigungAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Magnetartikel <em>Magnetartikel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Magnetartikel
	 * @generated
	 */
	public Adapter createMagnetartikelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Impulsmodul <em>Impulsmodul</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Impulsmodul
	 * @generated
	 */
	public Adapter createImpulsmodulAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gleisteil <em>Gleisteil</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gleisteil
	 * @generated
	 */
	public Adapter createGleisteilAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Einfahrsignal <em>Einfahrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Einfahrsignal
	 * @generated
	 */
	public Adapter createEinfahrsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Weiche <em>Weiche</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Weiche
	 * @generated
	 */
	public Adapter createWeicheAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Formvorsignal <em>Formvorsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Formvorsignal
	 * @generated
	 */
	public Adapter createFormvorsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Formsignal <em>Formsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Formsignal
	 * @generated
	 */
	public Adapter createFormsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gleis <em>Gleis</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gleis
	 * @generated
	 */
	public Adapter createGleisAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Blocksignal <em>Blocksignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Blocksignal
	 * @generated
	 */
	public Adapter createBlocksignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Vorsignal <em>Vorsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Vorsignal
	 * @generated
	 */
	public Adapter createVorsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.DKW <em>DKW</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.DKW
	 * @generated
	 */
	public Adapter createDKWAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Gleissperrsignal <em>Gleissperrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Gleissperrsignal
	 * @generated
	 */
	public Adapter createGleissperrsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Formhauptsignal <em>Formhauptsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Formhauptsignal
	 * @generated
	 */
	public Adapter createFormhauptsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Formgleissperrsignal <em>Formgleissperrsignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Formgleissperrsignal
	 * @generated
	 */
	public Adapter createFormgleissperrsignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Strecke <em>Strecke</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Strecke
	 * @generated
	 */
	public Adapter createStreckeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Beleuchtungsmodul <em>Beleuchtungsmodul</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Beleuchtungsmodul
	 * @generated
	 */
	public Adapter createBeleuchtungsmodulAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.morknet.mrw.metamodel.Lampe <em>Lampe</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.morknet.mrw.metamodel.Lampe
	 * @generated
	 */
	public Adapter createLampeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelrailwayAdapterFactory
