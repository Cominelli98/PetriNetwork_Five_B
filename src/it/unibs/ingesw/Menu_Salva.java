package it.unibs.ingesw;

import java.util.ArrayList;

import it.unibs.ingesw.ioGson.ReadN;
import it.unibs.ingesw.ioGson.WriteN;
import it.unibs.ingesw.utility.Utility;
import it.unibs.ingesw.utility.UtilityRead;
import it.unibs.ingesw.utility.UtilityVisua;

public final class Menu_Salva {

	private static final String MENUSALVA[] = {
			"Scegli cosa fare:",
			"___________________________",
			"1:Salva una rete singola",
			"2:Salva tutte le reti",
			"0:Indietro",
			"___________________________"};
	
	private static final String SALVATAGGIO = "Salvataggio eseguito";
	private static final String RICHIESTA_SALVATAGGIO = "Quale rete vuoi salvare?";
	private static final String NO_RETI = "non ci sono reti da salvare";
	
	
	
	public static void saveOption(ArrayList<? extends GenericNetwork> net) {
		if(net.size() == 0) {
			System.out.println(NO_RETI);
			return;
		}
		int select = -1;
		System.out.println(UtilityVisua.printMenu(MENUSALVA));
		select = Utility.readLimitedInt(0, MENUSALVA.length-4);
		switch (select) {
		case 0: //Indietro
			break;
		case 1:	//Salva su file una rete 
			System.out.println(RICHIESTA_SALVATAGGIO);
			System.out.println(Menu_Visua.getNetsList(net));
			int i = Utility.readLimitedInt(0, net.size());
			saveNetOnFile(net.get(i));
			break;
		case 2:	//salva su file tutte le reti 
			for (GenericNetwork n : net)
				saveNetOnFile(n);
			break;
		}
	}
	
	/**
	 * Metodo che richiama dalla classe statica WriteN il salvataggio su file delle reti create
	 */
	private static void saveNetOnFile(IDNameGiver n){
		if (!UtilityRead.checkIdExistence(n.getId(), n.getClass()))
			WriteN.save(n);
		System.out.println(SALVATAGGIO + " rete " + n.getName());
	}
}
