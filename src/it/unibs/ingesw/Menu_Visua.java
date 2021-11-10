package it.unibs.ingesw;

import java.util.ArrayList;

import it.unibs.ingesw.utility.Utility;
import it.unibs.ingesw.utility.UtilityVisua;
import it.unibs.ingesw.utility.UtilityPrint;

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
	
	public static void gnetViewer(ArrayList<? extends GenericNetwork> ns) {
		if(ns.size() == 0) {
			System.out.println(NO_RETI);
			return;
		}
		System.out.println("Quale rete vuoi visualizzare?");
		System.out.println(UtilityVisua.numberedNamesPrint(ns));
		int i = Utility.readLimitedInt(0, ns.size());
		int select = -1;
		do {
			System.out.println(UtilityVisua.printMenu(MENUVISUALIZZA));
			select = Utility.readLimitedInt(0, MENUVISUALIZZA.length-4);
			
			switch(select) {
			case 1:	//Visualizza l'elenco delle locatione di una rete
				System.out.println("ELENCO LOCATIONS:");
				System.out.println(UtilityVisua.nodesPrint(ns.get(i).getLocations()));
				break;
			case 2:	//Visualizza l'elenco delle transition di una rete
				System.out.println("ELENCO TRANSITIONS:");
				System.out.println(UtilityVisua.nodesPrint(ns.get(i).getTransitions()));
				break;
			case 3:	//Visualizza l'elenco dei link di una rete
				System.out.println("ELENCO LINKS:");
				UtilityVisua.linksList(ns.get(i));
				break;
			case 4:	//Visualizza la rete complessiva
				System.out.println(ns.get(i).print());
				break;
			case 0:	//indietro
				break;
			}
		}while (select != 0);
	}
}
