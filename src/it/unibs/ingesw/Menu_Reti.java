package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.unibs.ingesw.ioGson.ReadN;
import it.unibs.ingesw.utility.Utility;
import it.unibs.ingesw.utility.UtilityVisua;

public final class Menu_Reti {
	
	final static String MENU_RETI[] = {
			"Scegli cosa fare:",
			"_________",
			"1:Crea location",
			"2:Crea transition",
			"3:Crea link",
			"0:Indietro",
			"_________"};
	final static String NOME_GIA_PRESENTE_RETE = "Esiste già una rete con questo nome, inserisci un altro nome:";
	final static String NOME_GIA_PRESENTE_LOCATION = "Esiste già una location con questo nome, inserisci un altro nome:";
	final static String NOME_GIA_PRESENTE_TRANSITION = "Esiste già una transition con questo nome, inserisci un altro nome:";
	final static String LINK_GIA_PRESENTE = "Link già presente";
	final static String ASKLINK = "A cosa vuoi collegarla? Inserisci il numero relativo";
	final static String INSERIMENTO_TR = "Inserisci il nome della nuova transition: ";
	final static String INSERIMENTO_RETE = "Inserisci il nome della nuova rete: ";
	final static String INSERIMENTO_LOC = "Inserisci il nome della nuova location: ";
	
	
	/**
	 * Metodo di gestione della creazione di reti:
	 * Crea una Network chiedendo all'utente un nome e imponendo la creazione di un posto, una transition
	 * e un link iniziale. Permette la creazione guidata di nuovi nodi e link garantendo correttezza sintattica
	 * Garantisce unicità previa check sul nome della rete.
	 * @throws FileNotFoundException 
	 */
	public static void createNetwork(ArrayList<Network> ns)  {
		Network network = networkInitializer(ns);
		createBase(network);
		int select = -1;
		do {
			System.out.println(UtilityVisua.printMenu(MENU_RETI));
			select = Utility.readLimitedInt(0, MENU_RETI.length-4);
			int num = -1;
			switch (select) {
				
				case 0:	//Indietro
					break;
				case 1:
					createLocation(network);
					System.out.println(ASKLINK);
					System.out.print(UtilityVisua.numberedNodesPrint(network.getTransitions()));
					num = Utility.readLimitedInt(0, network.getTransitions().size()-1);
					createLink(network.getTransition(num), network.getLastLocation(), network);
					num = -1;
					break;
				case 2:	//Creazione di una transizione
					createTransition(network);
					System.out.println(ASKLINK);
					System.out.print(UtilityVisua.numberedNodesPrint(network.getLocations()));
					num = Utility.readLimitedInt(0, network.getLocations().size()-1);
					createLink(network.getLastTransition(), network.getLocation(num), network);
					num = -1;
					break;
				case 3:	//Creazione di un link
					int loc;
					int trans;
					System.out.println("ELENCO LOCATIONS");
					System.out.print(UtilityVisua.numberedNodesPrint(network.getLocations()));
					loc = Utility.readLimitedInt(0, network.getLocations().size()-1);
					System.out.println(ASKLINK);
					System.out.println("ELENCO TRANSITIONS");
					System.out.print(UtilityVisua.numberedNodesPrint(network.getTransitions()));
					trans = Utility.readLimitedInt(0, network.getTransitions().size()-1);
					createLink(network.getTransition(trans), network.getLocation(loc), network);
					break;
			}
		}while(select != 0);
	}
	
	/**
	 * Crea una base per la rete imponendo la creazione di un posto, una transizione e creando un link tra esse
	 */
	private static void createBase(Network n) {
		createLocation(n);
		createTransition(n);
		createLink(n.getTransition(0), n.getLocation(0), n);
		
	}
	
	/**
	 * Metodo che crea un nuovo posto nella rete con l'inserimento di un "nome" univoco
	 */
	private static void createLocation(Network n) {
		
		String name = Utility.readCheckedName(n.getLocations(), INSERIMENTO_LOC, NOME_GIA_PRESENTE_LOCATION);
		n.addLocation(name);
	}
	
	/**
	 * Medoto che crea una nuova transizione nella rete con l'inserimento di un "nome" univoco
	 */
	private static void createTransition(Network n) {
			
		String name;
		name = Utility.readCheckedName(n.getTransitions(), INSERIMENTO_TR, NOME_GIA_PRESENTE_TRANSITION);
		n.addTransition(name);
		}
	
	/**
	 * Aggiunge un link alla rete corrente 
	 * @param t un oggetto transizione
	 * @param l un oggetto location
	 */
	public static void createLink(Transition t, Location l, Network n) {
		int scelta;
		System.out.println("Com'è orientato il collegamento? \n 0)" + 
				l.getName() + "---->" + t.getName() + "\n 1)" +
				t.getName() + "---->" + l.getName());
		scelta = Utility.readLimitedInt(0, 1);
		if (scelta == 0)
			if (checkLinkExistence(l, t, n, 1))
				System.out.println(LINK_GIA_PRESENTE);
			else
				n.addLink(new Link(l.getId(), t.getId(), n.getId(), 1));
		else 
			if (checkLinkExistence(l, t, n, -1))
				System.out.println(LINK_GIA_PRESENTE);
			else {
				n.addLink(new Link(l.getId(), t.getId(), n.getId(),-1));
			}
	}
	
	/**
	 * Metodo check sull'esistenza di un link in una rete
	 * @param t transition
	 * @param l location
	 * @param n network
	 * @param orientation int
	 * @return true se il link esiste già
	 */
	private static boolean checkLinkExistence(Location l, Transition t, Network n, int orientation) {
		
		for (int i = 0; i < n.getLinks().size(); i++) {
			if(n.getLinks().get(i).getLocation() == l.getId() &&
					n.getLinks().get(i).getTransition() == t.getId() &&
						n.getLinks().get(i).getOrientation() == orientation)
				return true;
		}
		return false;
	}
	
	
	private static Network networkInitializer(ArrayList<Network> ns) {
		String name;
		boolean exists = false;
		name = Utility.readCheckedName(ns, INSERIMENTO_RETE, NOME_GIA_PRESENTE_RETE);
		Network network = new Network(name);
		ns.add(network);
		return network;
	}
}