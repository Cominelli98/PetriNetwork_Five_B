package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		System.out.println(Menu_Visua.getPNetworksList(pn));
		int select = -1;
		select = Utility.readLimitedInt(0, pn.size()-1);
		String name;
		do {
		System.out.println(MESSAGGI_MENU[1]);
		name = Utility.readString();
		if(checkPNpExistence(name, pnp))
			System.out.println(MESSAGGI_MENU[2]);
		}while(checkPNpExistence(name, pnp));
		int i;
		for(i = 0; i<ns.size(); i++) {
			if (ns.get(i).getId() == pn.get(select).getFatherNetId())
				break;
		}
		Priority_network toAdd = new Priority_network(pn.get(select), name);
		setPriorities(toAdd);
		if (!checkPNpExistence(toAdd.getName(), pnp))
			pnp.add(toAdd);
		else
			System.out.println(MESSAGGI_MENU[3]);
	}	
	
	private static boolean checkPNpExistence (String name, ArrayList<Priority_network> pnp) {
		
		if(pnp.size()>0) {
			for (Priority_network pn : pnp) {
				if(Utility.nameCheck(pn, name)) {
					return true;
				}
			}
		}
		return false;
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
		System.out.println(ReadN.getNetNamesList(Priority_network.class));
		scelta = Utility.readLimitedInt(0, s.size()-1);
		rete = (Priority_network) ReadN.jsonToObject(s.get(scelta), Priority_network.class);
		daSimulare = new Simulatore(rete);
		System.out.println("STATO DI PARTENZA:");
		Menu_Visua.printPetriNet(rete);
		do {
			System.out.println("MARCATURA SUCCESSIVA:");
			daSimulare.nextStep();
			System.out.println("Vuoi proseguire con la simulazione? \n 0)Esci \n 1)Prosegui");
			selezione = Utility.readLimitedInt(0, 1);
		}while(selezione!=0);
	}
}