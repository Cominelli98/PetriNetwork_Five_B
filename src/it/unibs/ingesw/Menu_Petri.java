package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.unibs.ingesw.ioGson.FromJson;
import it.unibs.ingesw.ioGson.ReadN;
import it.unibs.ingesw.utility.Utility;
import it.unibs.ingesw.utility.UtilityPrint;
import it.unibs.ingesw.utility.UtilityRead;
import it.unibs.ingesw.utility.UtilityVisua;

public final class Menu_Petri {

	private final static String MESSAGGI_MENU[] = {
			"da quale rete vuoi partire?",
			"Come vuoi chiamare questa rete di Petri?",
			"Esiste già una rete di petri con questo nome",
			"Questa rete di petri esiste già",
	};
	private final static String NO_RETI="Non ci sono reti in memoria";
	
	
	/**
	 * Metodo di interazione con il Configuratore per costruire una rete di petri da una rete
	 * @param pn
	 * @param ns
	 */
	public static void createPetri(ArrayList<Petri_network> pn, ArrayList<Network> ns) {
		if(ns.size() == 0) {
			System.out.println(NO_RETI);
			return;
		}
		System.out.println(MESSAGGI_MENU[0]);
		System.out.println(UtilityVisua.numberedNamesPrint(ns));
		int select = -1;
		select = Utility.readLimitedInt(0, ns.size()-1);
		String name;
		name = Utility.readCheckedName(pn, MESSAGGI_MENU[1], MESSAGGI_MENU[2]);
		Petri_network toAdd = new Petri_network(ns.get(select), name);
		setCosts(toAdd);
		setTokens(toAdd);
		if (!petriExistence(toAdd, pn))
			pn.add(toAdd);
		else
			System.out.println(MESSAGGI_MENU[3]);
	}
	
	
	/**
	 * Controlla che una rete di petri non sia presente in un array
	 * @param toAdd
	 * @param pn
	 * @return true se la rete è già presente, false se non c'è
	 */
	private static boolean petriExistence(Petri_network toAdd, ArrayList<Petri_network> pn) {
		if(pn.size() == 0) {
			return false;
		}
		for (Petri_network pns : pn) {
			if(petriSingleCheck(pns, toAdd))
				return true;
		}
		return false;
	}
	
	/**
	 * Controlla che due reti di petri non siano uguali per padre, location/transition + valori
	 * @param pn
	 * @param toCheck
	 * @return true se uguali, false se c'è almeno una differenza
	 */
	private static boolean petriSingleCheck(Petri_network pn, Petri_network toCheck) {
		if (toCheck.getFatherNetId() == pn.getFatherNetId()){
			for(int i=0; i<toCheck.getLocations().size(); i++) {
				if(toCheck.getLocation(i) != pn.getLocation(i))
					return false;
			}
			
			for (int j = 0; j<toCheck.getTransitions().size(); j++) {
				if(toCheck.getTransitionValue(j) != pn.getTransitionValue(j))
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Setta il valore dei costi delle transizioni della rete in ingresso
	 * @param toSet
	 */
	private static void setCosts(Petri_network toSet) {
		for (Petri_transition pt : toSet.getTransitions()) {
			System.out.println("Inserisci il costo della transizione "+pt.getName() + " (1 per default)");
			pt.setCost(Utility.readLowLimitInt(1));
		}
	}
	
	/**
	 * Setta il valore dei token di tutte le locations della rete in ingresso
	 * @param toSet
	 */
	private static void setTokens(Petri_network toSet) {
		for (Petri_location pl : toSet.getLocations()) {
			System.out.println("Inserisci la marcatura iniziale della posizione "+pl.getName() + " (0 per default)");
			pl.setToken(Utility.readLowLimitInt(0));
		}
	}
	
	
	
	/**
	 * Metodo di interazione con il fruitore per simulare una rete di petri
	 */
	public static void simulaPetri() {
		ArrayList<String> s = new ArrayList<String>();
		Simulatore daSimulare;
		Petri_network rete;
		int scelta;
		int selezione;
		try {
		s = ReadN.readFile(Petri_network.class);
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
		System.out.println("Scegli di quale rete di Petri vuoi simulare l'evoluzione");
		System.out.println(UtilityRead.getNetNamesList(Petri_network.class));
		scelta = Utility.readLimitedInt(0, s.size()-1);
		rete = (Petri_network) FromJson.convert(s.get(scelta), Petri_network.class);
		daSimulare = new Simulatore(rete);
		System.out.println("STATO DI PARTENZA:");
		System.out.println(UtilityPrint.PrintObjct(rete));;
		do {
			System.out.println("MARCATURA SUCCESSIVA:");
			daSimulare.nextStep();
			System.out.println("Vuoi proseguire con la simulazione? \n 0)Esci \n 1)Prosegui");
			selezione = Utility.readLimitedInt(0, 1);
		}while(selezione!=0);
	}
	

}
