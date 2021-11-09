package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.unibs.ingesw.ioGson.ReadN;
import it.unibs.ingesw.utility.Utility;
import it.unibs.ingesw.utility.UtilityRead;

public final class Menu_Pnp {

	private final static String MESSAGGI_MENU[] = {
			"da quale rete vuoi partire?",
			"Come vuoi chiamare questa PNp?",
			"Esiste già una Pnp con questo nome",
			"Questa PNP esiste già",
	};
	private static final String NO_P_RETI ="Non ci sono reti di petri da cui partire";
	
	/**
	 * Metodo di interazione con il Configuratore per costruire una rete di petri da una rete
	 * @param pnp
	 * @param pn
	 * @param ns
	 */
	public static void createPnp(ArrayList<Priority_network> pnp, ArrayList<Petri_network> pn, ArrayList<Network> ns) {
		if(pn.size() == 0) {
			System.out.println(NO_P_RETI);
			return;
		}
		System.out.println(MESSAGGI_MENU[0]);
		System.out.println(Menu_Visua.getNetsList(pn));
		int select = -1;
		select = Utility.readLimitedInt(0, pn.size()-1);
		String name;
		name = Utility.readCheckedName(pnp, MESSAGGI_MENU[1], MESSAGGI_MENU[2]);
		Priority_network toAdd = new Priority_network(pn.get(select), name);
		setPriorities(toAdd);
		if (!pnpExistence(toAdd, pnp))
			pnp.add(toAdd);
		else
			System.out.println(MESSAGGI_MENU[3]);
	}	
		
	/**
	 * Setta tutte le priorità delle transizioni presenti in una rete di petri priorizzata
	 * @param toSet
	 */
	private static void setPriorities(Priority_network toSet) {
		
		for (Petri_transition pt : toSet.getTransitions()) {
			System.out.println("Inserisci la priorità della transizione "+pt.getName() + " (1 per default)");
			pt.setPriority(Utility.readLowLimitInt(1));
		}
	}
	
	/**
	 * Controlla che una pnp non esista già in un array
	 * @param toAdd
	 * @param pnp
	 * @return true se esiste già, false se manca
	 */
	private static boolean pnpExistence(Priority_network toAdd, ArrayList<Priority_network> pnp) {
		
		if(pnp.size() == 0) {
			return false;
		}
		for (Priority_network pns : pnp) {
			if(prioritySingleCheck(pns, toAdd))
				return true;
		}
		return false;
	}
	/**
	 * Controlla che due pnp abbiano padre e/o priorità diverse
	 * @param pnp
	 * @param toCheck
	 * @return true se sono uguali, false se diverse
	 */
	private static boolean prioritySingleCheck(Priority_network pnp, Priority_network toCheck) {
		if (toCheck.getFatherNetId() == pnp.getFatherNetId()){
			for (int i = 0; i<toCheck.getTransitions().size(); i++) {
				if(toCheck.getTransitions().get(i).getPriority() != pnp.getTransitions().get(i).getPriority())
					return false;
			}
			return true;
		}
		return false;
	}
	
		
	/**
	 * Metodo di interazione con il fruitore per simulare una rete di petri priorizzata
	 */
	public static void simulaPriorityNet() {
		
		ArrayList<String> s = new ArrayList<String>();
		Simulatore daSimulare;
		Priority_network rete;
		int scelta;
		int selezione;
		try {
			s = ReadN.readFile(Priority_network.class);
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
		System.out.println("Scegli di quale PNp vuoi simulare l'evoluzione");
		System.out.println(UtilityRead.getNetNamesList(Priority_network.class));
		scelta = Utility.readLimitedInt(0, s.size()-1);
		rete = (Priority_network) ReadN.jsonToObject(s.get(scelta), Priority_network.class);
		daSimulare = new Simulatore(rete);
		System.out.println("STATO DI PARTENZA:");
		System.out.println(rete.print());
		do {
			System.out.println("MARCATURA SUCCESSIVA:");
			daSimulare.nextStep();
			System.out.println("Vuoi proseguire con la simulazione? \n 0)Esci \n 1)Prosegui");
			selezione = Utility.readLimitedInt(0, 1);
		}while(selezione!=0);
	}
}