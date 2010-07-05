/**
 * Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
 *
 * $Id$
 */
package de.morknet.mrw.metamodel.util;

import de.morknet.mrw.metamodel.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.morknet.mrw.metamodel.ModelrailwayPackage
 * @generated
 */
public class ModelrailwaySwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelrailwayPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelrailwaySwitch() {
		if (modelPackage == null) {
			modelPackage = ModelrailwayPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelrailwayPackage.ANSCHLUSS: {
				Anschluss anschluss = (Anschluss)theEObject;
				T result = caseAnschluss(anschluss);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.LICHTSIGNAL: {
				Lichtsignal lichtsignal = (Lichtsignal)theEObject;
				T result = caseLichtsignal(lichtsignal);
				if (result == null) result = caseSignal(lichtsignal);
				if (result == null) result = caseBauelement(lichtsignal);
				if (result == null) result = caseUnit(lichtsignal);
				if (result == null) result = caseElement(lichtsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.SIGNAL: {
				Signal signal = (Signal)theEObject;
				T result = caseSignal(signal);
				if (result == null) result = caseBauelement(signal);
				if (result == null) result = caseUnit(signal);
				if (result == null) result = caseElement(signal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.BAUELEMENT: {
				Bauelement bauelement = (Bauelement)theEObject;
				T result = caseBauelement(bauelement);
				if (result == null) result = caseElement(bauelement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.ELEMENT: {
				Element element = (Element)theEObject;
				T result = caseElement(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GLEISABSCHNITT: {
				Gleisabschnitt gleisabschnitt = (Gleisabschnitt)theEObject;
				T result = caseGleisabschnitt(gleisabschnitt);
				if (result == null) result = caseElement(gleisabschnitt);
				if (result == null) result = caseUnit(gleisabschnitt);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.UNIT: {
				Unit unit = (Unit)theEObject;
				T result = caseUnit(unit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GRUPPE: {
				Gruppe gruppe = (Gruppe)theEObject;
				T result = caseGruppe(gruppe);
				if (result == null) result = caseElement(gruppe);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.MODELL: {
				Modell modell = (Modell)theEObject;
				T result = caseModell(modell);
				if (result == null) result = caseElement(modell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.CONTROLLER: {
				Controller controller = (Controller)theEObject;
				T result = caseController(controller);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.MODUL: {
				Modul modul = (Modul)theEObject;
				T result = caseModul(modul);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GLEISMODUL: {
				Gleismodul gleismodul = (Gleismodul)theEObject;
				T result = caseGleismodul(gleismodul);
				if (result == null) result = caseModul(gleismodul);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.AUSFAHRSIGNAL: {
				Ausfahrsignal ausfahrsignal = (Ausfahrsignal)theEObject;
				T result = caseAusfahrsignal(ausfahrsignal);
				if (result == null) result = caseHauptlichtsignal(ausfahrsignal);
				if (result == null) result = caseLichtsignal(ausfahrsignal);
				if (result == null) result = caseSignal(ausfahrsignal);
				if (result == null) result = caseBauelement(ausfahrsignal);
				if (result == null) result = caseUnit(ausfahrsignal);
				if (result == null) result = caseElement(ausfahrsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.HAUPTLICHTSIGNAL: {
				Hauptlichtsignal hauptlichtsignal = (Hauptlichtsignal)theEObject;
				T result = caseHauptlichtsignal(hauptlichtsignal);
				if (result == null) result = caseLichtsignal(hauptlichtsignal);
				if (result == null) result = caseSignal(hauptlichtsignal);
				if (result == null) result = caseBauelement(hauptlichtsignal);
				if (result == null) result = caseUnit(hauptlichtsignal);
				if (result == null) result = caseElement(hauptlichtsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.BAHNHOF: {
				Bahnhof bahnhof = (Bahnhof)theEObject;
				T result = caseBahnhof(bahnhof);
				if (result == null) result = caseGruppe(bahnhof);
				if (result == null) result = caseElement(bahnhof);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.VERZWEIGUNG: {
				Verzweigung verzweigung = (Verzweigung)theEObject;
				T result = caseVerzweigung(verzweigung);
				if (result == null) result = caseGleisteil(verzweigung);
				if (result == null) result = caseMagnetartikel(verzweigung);
				if (result == null) result = caseUnit(verzweigung);
				if (result == null) result = caseBauelement(verzweigung);
				if (result == null) result = caseElement(verzweigung);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.MAGNETARTIKEL: {
				Magnetartikel magnetartikel = (Magnetartikel)theEObject;
				T result = caseMagnetartikel(magnetartikel);
				if (result == null) result = caseBauelement(magnetartikel);
				if (result == null) result = caseElement(magnetartikel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.IMPULSMODUL: {
				Impulsmodul impulsmodul = (Impulsmodul)theEObject;
				T result = caseImpulsmodul(impulsmodul);
				if (result == null) result = caseModul(impulsmodul);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GLEISTEIL: {
				Gleisteil gleisteil = (Gleisteil)theEObject;
				T result = caseGleisteil(gleisteil);
				if (result == null) result = caseBauelement(gleisteil);
				if (result == null) result = caseElement(gleisteil);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.EINFAHRSIGNAL: {
				Einfahrsignal einfahrsignal = (Einfahrsignal)theEObject;
				T result = caseEinfahrsignal(einfahrsignal);
				if (result == null) result = caseHauptlichtsignal(einfahrsignal);
				if (result == null) result = caseLichtsignal(einfahrsignal);
				if (result == null) result = caseSignal(einfahrsignal);
				if (result == null) result = caseBauelement(einfahrsignal);
				if (result == null) result = caseUnit(einfahrsignal);
				if (result == null) result = caseElement(einfahrsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.WEICHE: {
				Weiche weiche = (Weiche)theEObject;
				T result = caseWeiche(weiche);
				if (result == null) result = caseVerzweigung(weiche);
				if (result == null) result = caseGleisteil(weiche);
				if (result == null) result = caseMagnetartikel(weiche);
				if (result == null) result = caseUnit(weiche);
				if (result == null) result = caseBauelement(weiche);
				if (result == null) result = caseElement(weiche);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.FORMVORSIGNAL: {
				Formvorsignal formvorsignal = (Formvorsignal)theEObject;
				T result = caseFormvorsignal(formvorsignal);
				if (result == null) result = caseFormsignal(formvorsignal);
				if (result == null) result = caseMagnetartikel(formvorsignal);
				if (result == null) result = caseSignal(formvorsignal);
				if (result == null) result = caseBauelement(formvorsignal);
				if (result == null) result = caseUnit(formvorsignal);
				if (result == null) result = caseElement(formvorsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.FORMSIGNAL: {
				Formsignal formsignal = (Formsignal)theEObject;
				T result = caseFormsignal(formsignal);
				if (result == null) result = caseMagnetartikel(formsignal);
				if (result == null) result = caseSignal(formsignal);
				if (result == null) result = caseBauelement(formsignal);
				if (result == null) result = caseUnit(formsignal);
				if (result == null) result = caseElement(formsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GLEIS: {
				Gleis gleis = (Gleis)theEObject;
				T result = caseGleis(gleis);
				if (result == null) result = caseGleisteil(gleis);
				if (result == null) result = caseBauelement(gleis);
				if (result == null) result = caseElement(gleis);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.BLOCKSIGNAL: {
				Blocksignal blocksignal = (Blocksignal)theEObject;
				T result = caseBlocksignal(blocksignal);
				if (result == null) result = caseHauptlichtsignal(blocksignal);
				if (result == null) result = caseLichtsignal(blocksignal);
				if (result == null) result = caseSignal(blocksignal);
				if (result == null) result = caseBauelement(blocksignal);
				if (result == null) result = caseUnit(blocksignal);
				if (result == null) result = caseElement(blocksignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.VORSIGNAL: {
				Vorsignal vorsignal = (Vorsignal)theEObject;
				T result = caseVorsignal(vorsignal);
				if (result == null) result = caseLichtsignal(vorsignal);
				if (result == null) result = caseSignal(vorsignal);
				if (result == null) result = caseBauelement(vorsignal);
				if (result == null) result = caseUnit(vorsignal);
				if (result == null) result = caseElement(vorsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.DKW: {
				DKW dkw = (DKW)theEObject;
				T result = caseDKW(dkw);
				if (result == null) result = caseVerzweigung(dkw);
				if (result == null) result = caseGleisteil(dkw);
				if (result == null) result = caseMagnetartikel(dkw);
				if (result == null) result = caseUnit(dkw);
				if (result == null) result = caseBauelement(dkw);
				if (result == null) result = caseElement(dkw);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.GLEISSPERRSIGNAL: {
				Gleissperrsignal gleissperrsignal = (Gleissperrsignal)theEObject;
				T result = caseGleissperrsignal(gleissperrsignal);
				if (result == null) result = caseLichtsignal(gleissperrsignal);
				if (result == null) result = caseSignal(gleissperrsignal);
				if (result == null) result = caseBauelement(gleissperrsignal);
				if (result == null) result = caseUnit(gleissperrsignal);
				if (result == null) result = caseElement(gleissperrsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.FORMHAUPTSIGNAL: {
				Formhauptsignal formhauptsignal = (Formhauptsignal)theEObject;
				T result = caseFormhauptsignal(formhauptsignal);
				if (result == null) result = caseFormsignal(formhauptsignal);
				if (result == null) result = caseMagnetartikel(formhauptsignal);
				if (result == null) result = caseSignal(formhauptsignal);
				if (result == null) result = caseBauelement(formhauptsignal);
				if (result == null) result = caseUnit(formhauptsignal);
				if (result == null) result = caseElement(formhauptsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.FORMGLEISSPERRSIGNAL: {
				Formgleissperrsignal formgleissperrsignal = (Formgleissperrsignal)theEObject;
				T result = caseFormgleissperrsignal(formgleissperrsignal);
				if (result == null) result = caseFormsignal(formgleissperrsignal);
				if (result == null) result = caseMagnetartikel(formgleissperrsignal);
				if (result == null) result = caseSignal(formgleissperrsignal);
				if (result == null) result = caseBauelement(formgleissperrsignal);
				if (result == null) result = caseUnit(formgleissperrsignal);
				if (result == null) result = caseElement(formgleissperrsignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelrailwayPackage.STRECKE: {
				Strecke strecke = (Strecke)theEObject;
				T result = caseStrecke(strecke);
				if (result == null) result = caseGruppe(strecke);
				if (result == null) result = caseElement(strecke);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anschluss</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anschluss</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnschluss(Anschluss object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lichtsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lichtsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLichtsignal(Lichtsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSignal(Signal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bauelement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bauelement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBauelement(Bauelement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gleisabschnitt</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gleisabschnitt</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGleisabschnitt(Gleisabschnitt object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnit(Unit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gruppe</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gruppe</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGruppe(Gruppe object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModell(Modell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Controller</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Controller</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseController(Controller object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modul</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modul</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModul(Modul object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gleismodul</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gleismodul</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGleismodul(Gleismodul object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ausfahrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ausfahrsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAusfahrsignal(Ausfahrsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hauptlichtsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hauptlichtsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHauptlichtsignal(Hauptlichtsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bahnhof</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bahnhof</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBahnhof(Bahnhof object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Verzweigung</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Verzweigung</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerzweigung(Verzweigung object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Magnetartikel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Magnetartikel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMagnetartikel(Magnetartikel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Impulsmodul</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Impulsmodul</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImpulsmodul(Impulsmodul object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gleisteil</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gleisteil</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGleisteil(Gleisteil object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Einfahrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Einfahrsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEinfahrsignal(Einfahrsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weiche</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weiche</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeiche(Weiche object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formvorsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formvorsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormvorsignal(Formvorsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormsignal(Formsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gleis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gleis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGleis(Gleis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blocksignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blocksignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlocksignal(Blocksignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vorsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vorsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVorsignal(Vorsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DKW</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DKW</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDKW(DKW object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gleissperrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gleissperrsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGleissperrsignal(Gleissperrsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formhauptsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formhauptsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormhauptsignal(Formhauptsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formgleissperrsignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formgleissperrsignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormgleissperrsignal(Formgleissperrsignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Strecke</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Strecke</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStrecke(Strecke object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelrailwaySwitch
