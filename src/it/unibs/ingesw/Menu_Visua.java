package it.unibs.ingesw;

import java.util.ArrayList;

public final class Menu_Visua {
	
	final static String MENUVISUALIZZA[] = {
			"Cosa vuoi visualizzare?:",
			"___________________________",
			"1:Visualizza elenco locations",
			"2:Visualizza elenco transitions",
			"3:Visualizza elenco link ",
			"4:Visualizza rete complessiva",
			"0:Indietro",
			"___________________________"};
	private static final String NO_RETI = "Non ci sono reti da visualizzare";
	private static final String NO_RETI_P = "Non ci sono reti di petri da visualizzare";
	private static final String NO_RETI_PNP = "Non ci sono reti pnp da visualizzare";
	
	/**
	 * Menu di visualizzazione di una rete
	 */
	public static void netViewer(ArrayList<Network> ns) {
		if(ns.size() == 0) {
			System.out.println(NO_RETI);
			return;
		}
		System.out.println("Quale rete vuoi visualizzare?");
		System.out.println(getNetworksList(ns));
		int i = Utility.readLimitedInt(0, ns.size());
		int select = -1;
		do {
			for (String s : MENUVISUALIZZA)
				System.out.println(s);
			select = Utility.readLimitedInt(0, MENUVISUALIZZA.length-4);
			
			switch(select) {
			case 1:	//Visualizza l'elenco delle locatione di una rete
				System.out.println("ELENCO LOCATIONS:");
				System.out.println(ns.get(i).getLocationsList());
				break;
			case 2:	//Visualizza l'elenco delle transition di una rete
				System.out.println("ELENCO TRANSITIONS:");
				System.out.println(ns.get(i).getTransitionsList());
				break;
			case 3:	//Visualizza l'elenco dei link di una rete
				System.out.println("ELENCO LINKS:");
				System.out.println(ns.get(i).getLinksList());
				break;
			case 4:	//Visualizza la rete complessiva
				System.out.println("ELENCO LOCATIONS:");
				System.out.println(ns.get(i).getLocationsList());
				System.out.println("ELENCO TRANSITIONS:");
				System.out.println(ns.get(i).getTransitionsList());
				System.out.println("ELENCO LINKS:");
				System.out.println(ns.get(i).getLinksList());
				break;
			case 0:	//indietro
				break;
			}
		}while (select != 0);
	}
	
	/**
	 * Crea un elenco numerato di tutte reti a seconda che sia una rete, PN o PNp
	 * @param ns
	 * @return
	 */
	public static StringBuffer getNetworksList(ArrayList<Network> ns ){
		StringBuffer s = new StringBuffer("");
		int i = 0;
		for (IDNameGiver n : ns) {
			s.append(i++ + ")" + n.getName() + "\n");
		}
		return s;
	}
	
	/**
	 * Menu di visualizzazione di una rete di petri 
	 * @param pn
	 */
	public static void petriNetViewer(ArrayList<Petri_network> pn) {
		if(pn.size() == 0) {
			System.out.println(NO_RETI_P);
			return;
		}
		System.out.println("Quale rete di Petri vuoi visualizzare?");
		System.out.println(getPNetworksList(pn));
		int i = Utility.readLimitedInt(0, pn.size());
		int select = -1;
		do {
			for (String s : MENUVISUALIZZA)
				System.out.println(s);
			select = Utility.readLimitedInt(0, MENUVISUALIZZA.length-4);
			
			switch(select) {
			case 1:	//Visualizza l'elenco delle location di una rete di petri
				System.out.println("ELENCO LOCATIONS:");
				System.out.println(pn.get(i).getLocationsList());
				break;
			case 2:	//Visualizza l'elenco delle transition di una rete di petri
				System.out.println("ELENCO TRANSITIONS:");
				System.out.println(pn.get(i).getTransitionsList());
				break;
			case 3:	//Visualizza l'elenco dei link di una rete di petri
				System.out.println("ELENCO LINKS:");
				System.out.println(pn.get(i).getLinksList());
				break;
			case 4: //Visualizza la rete di petri complessiva
				printPetriNet(pn.get(i));
				break;
			case 0:	// indietro
				break;
			}
		}while (select != 0);
		
	}
	
	/**
	 * Menu di visualizzazione di una rete di petri con priorità
	 * @param pnp
	 */
	public static void pnpViewer(ArrayList<Priority_network> pnp) {
		if(pnp.size() == 0) {
			System.out.println(NO_RETI_PNP);
			return;
		}
		System.out.println("Quale pnp vuoi visualizzare?");
		System.out.println(getPnpList(pnp));
		int i = Utility.readLimitedInt(0, pnp.size());
		int select = -1;
		do {
			for (String s : MENUVISUALIZZA)
				System.out.println(s);
			select = Utility.readLimitedInt(0, MENUVISUALIZZA.length-4);
			
			switch(select) {	
			case 1:	//Visualizza l'elenco delle location di una rete di petri con priorità
				System.out.println("ELENCO LOCATIONS:");
				System.out.println(pnp.get(i).getLocationsList());
				break;
			case 2:	//Visualizza l'elenco delle transition di una rete di petri con priorità
				System.out.println("ELENCO TRANSITIONS:");
				System.out.println(pnp.get(i).getTransitionsList());
				break;
			case 3:	//Visualizza l'elenco dei link di una rete di petri con priorità
				System.out.println("ELENCO LINKS:");
				System.out.println(pnp.get(i).getLinksList());
				break;
			case 4:	//Visualizza la rete di petri con priorità complessiva
				printPetriNet(pnp.get(i));
				break;
			case 0:	//Indietro
				break;
			}
		}while (select != 0);
		
	}

	public static StringBuffer getPNetworksList(ArrayList<Petri_network> pn){
		StringBuffer f = new StringBuffer("");
		int i = 0;
		for (Petri_network n : pn) {
			f.append(i++ + ")" + n.getName() + "\n");
		}
		return f;
	}

	public static void printPetriNet(Petri_network pn) {
		System.out.println("ELENCO LOCATIONS:");
		System.out.println(pn.getLocationsList());
		System.out.println("ELENCO TRANSITIONS:");
		System.out.println(pn.getTransitionsList());
		System.out.println("ELENCO LINKS:");
		System.out.println(pn.getLinksList());
		}
	
	/**
	 * Crea un elenco numerato di tutte le pnp presenti
	 * @param pnp
	 * @return StringBuffer f
	 */
	public static StringBuffer getPnpList(ArrayList<Priority_network> pnp){
		StringBuffer f = new StringBuffer("");
		int i = 0;
		for (Priority_network n : pnp) {
			f.append(i++ + ")" + n.getName() + "\n");
		}
		return f;
	}
}
