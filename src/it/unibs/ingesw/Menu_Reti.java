package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.unibs.ingesw.ioGson.ReadN;

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
			for (String s : MENU_RETI) {
				System.out.println(s);
			}
			select = Utility.readLimitedInt(0, MENU_RETI.length-4);
			int num = -1;
			switch (select) {
				
				case 0:	//Indietro
					break;
				case 1:
					createLocation(network);
					System.out.println(ASKLINK);
					System.out.print(network.getTransitionsList());
					num = Utility.readLimitedInt(0, network.getTransitions().size()-1);
					createLink(network.getTransition(num), network.getLastLocation(), network);
					num = -1;
					break;
				case 2:	//Creazione di una transizione
					createTransition(network);
					System.out.println(ASKLINK);
					System.out.print(network.getLocationsList());
					num = Utility.readLimitedInt(0, network.getLocations().size()-1);
					createLink(network.getLastTransition(), network.getLocation(num), network);
					num = -1;
					break;
				case 3:	//Creazione di un link
					int loc;
					int trans;
					System.out.println("ELENCO LOCATIONS");
					System.out.print(network.getLocationsList());
					loc = Utility.readLimitedInt(0, network.getLocations().size()-1);
					System.out.println(ASKLINK);
					System.out.println("ELENCO TRANSITIONS");
					System.out.print(network.getTransitionsList());
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
		
		System.out.println("Inserisci il nome della nuova location: ");
		boolean isEqual;
		String name;
		do {
			isEqual = false;
			name = Utility.readString();
			for (Location l : n.getLocations()) {
				if(Utility.nameCheck(l, name)) {
					isEqual = true;
					System.out.println(NOME_GIA_PRESENTE_LOCATION);
					break;
				}
			}
		}while(isEqual);
		n.addLocation(name);
	}
	
	/**
	 * Medoto che crea una nuova transizione nella rete con l'inserimento di un "nome" univoco
	 */
	private static void createTransition(Network n) {
			
			System.out.println("Inserisci il nome della nuova transition: ");
			boolean isEqual;
			String name;
			do {
				isEqual = false;
				name = Utility.readString();
				for (Transition l : n.getTransitions()) {
					if(Utility.nameCheck(l, name)) {
						isEqual = true;
						System.out.println(NOME_GIA_PRESENTE_TRANSITION);
					}
				}
			}while(isEqual);
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
	 * Metodo check sull'esistenza di un link
	 * @param t transition
	 * @param l location
	 * @return boolean
	 */
	private static boolean checkLinkExistence(Location l, Transition t, Network n, int orientation) {
		
		for (int i = 0; i < n.getNetLinks().size(); i++) {
			if(n.getNetLinks().get(i).getLocation() == l.getId() &&
					n.getNetLinks().get(i).getTransition() == t.getId() &&
						n.getNetLinks().get(i).getOrientation() == orientation)
				return true;
		}
		return false;
	}
	
	
	private static Network networkInitializer(ArrayList<Network> ns) {
		String name;
		boolean exists = false;
		do {
			name = Utility.readCheckedName(ns);
			try {
				exists = ReadN.checkNetNameExistence(name, Network.class);
				}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			if (exists)
				System.out.println(NOME_GIA_PRESENTE_RETE);
		}while(exists);
		Network network = new Network(name);
		ns.add(network);
		return network;
	}
}