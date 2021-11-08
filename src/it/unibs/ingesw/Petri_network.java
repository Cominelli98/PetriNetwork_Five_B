package it.unibs.ingesw;

import java.util.ArrayList;

import it.unibs.ingesw.utility.UtilityVisua;

public class Petri_network implements GenericNetwork{
	protected ArrayList<Petri_location> petriLocations;
	protected ArrayList<Petri_transition> petriTransitions;
	protected ArrayList<Link> petriNetLinks;
	protected int petriNetId; 
	protected int fatherNetId;
	private String name;
	static int petriNetworkId = 0;
	
	public Petri_network(Network n, String name) {
		this.petriNetId = ++petriNetworkId;
		petriLocations = Converter.toPetriLocations(n.getLocations(), petriNetId);
		petriTransitions = Converter.toPetriTransitions(n.getTransitions(), petriNetId);
		petriNetLinks = Converter.toPetriLinks(n.getNetLinks(), petriNetId);
		this.fatherNetId = n.getId();
		this.name = name;
	}
	
	public Petri_network(Petri_network pt, String name) {
		this.fatherNetId = pt.getFatherNetId();
		this.name = name;
		this.petriLocations = pt.getLocations();
		this.petriTransitions = pt.getTransitions();
		this.petriNetLinks = pt.getLinks();
		this.petriNetId = pt.getId();
	}
	
	public int getFatherNetId() {
		return fatherNetId;
	};

	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Produce un elenco numerato delle transition di una rete
	 * @return StringBuffer s
	 */
	public StringBuffer getTransitionsList() {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i<petriTransitions.size(); i++) {
			s.append(i + ")" + petriTransitions.get(i).getName() + " costo: " + petriTransitions.get(i).getValue() + "\n");
		}
		return s;
	}
	
	/**
	 * Produce un elenco numerato di location di una rete
	 * @return StringBuffer s
	 */
	public StringBuffer getLocationsList() {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i<petriLocations.size(); i++) {
			s.append(i + ")" + petriLocations.get(i).getName() + " marcatura: " + petriLocations.get(i).getValue() + "\n");
		}
		return s;
	}
	
	/**
	 * Produce un elenco numerato dei link di una rete
	 * @return StringBuffer s
	 */
	public StringBuffer getLinksList() {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i < petriNetLinks.size(); i++) {
			s.append(i + ")" + nodeNameFromID(petriNetLinks.get(i).getOrigin()) + "---->" + nodeNameFromID(petriNetLinks.get(i).getDestination())+ "\n");
		}
		return s;
	}
	
	@Override
	public ArrayList<Petri_location> getLocations(){
		return petriLocations;
	}
	
	@Override
	public ArrayList<Petri_transition> getTransitions(){
		return petriTransitions;
	}
	
	@Override
	public Petri_transition getTransition(int i) {
		return petriTransitions.get(i);
	}

	@Override
	public Petri_location getLocation(int i) {
		return petriLocations.get(i);
	}
	
	@Override
	public ArrayList<Link> getLinks(){
		return this.petriNetLinks;
	}
	
	@Override
	public Link getLink(int i) {
		return petriNetLinks.get(i);
	}

	@Override
	public int getId() {
		return petriNetId;
	}
	
	/**
	 * Metodo utilizzato nella simulazione per ridurre di una quantit� il numero di token presenti in tutti i precedenti di una transizione
	 * @param idTransition
	 * @param quantity
	 */
	public void reduceToken(int idTransition, int quantity) {
		for(Link l : petriNetLinks) {
			if(l.getTransition() == idTransition && l.getOrientation() == 1) {
				reduceLocationToken(l.getLocation(), quantity);
			}
		}
	}
	
	/**
	 * Metodo utilizzato nella simulazione per aumentare di una quantit� il numero di token in tutti i successori di una transizione
	 * @param idTransition
	 * @param quantity
	 */
	public void addToken(int idTransition, int quantity) {
		for(Link l : petriNetLinks) {
			if(l.getTransition() == idTransition && l.getOrientation() == -1) {
				addLocationToken(l.getLocation(), quantity);
				}
		}
	}
	
	public String nodeNameFromID(int id) {
		for(Petri_location pl : petriLocations) {
			if(pl.getId() == id)
				return pl.getName();
		}
		for(Petri_transition pt : petriTransitions) {
			if(pt.getId() == id)
				return pt.getName();
		}
		return null;
	}
	
	public void reduceLocationToken(int id, int quantity) {
		for(Petri_location pl : petriLocations) {
			if(pl.getId() == id)
				pl.reduceToken(quantity);
		}
	}
	
	public void addLocationToken(int id, int quantity) {
		for(Petri_location pl : petriLocations) {
			if(pl.getId() == id)
				pl.addToken(quantity);
		}
	}

	@Override
	public String getLinkOrigin(int i) {
		return nodeNameFromID(petriNetLinks.get(i).getOrigin());
	}

	@Override
	public String getLinkDestination(int i) {
		// TODO Auto-generated method stub
		return nodeNameFromID(petriNetLinks.get(i).getDestination());
	}
	
	public StringBuffer print() {
		StringBuffer s = new StringBuffer("");
		s.append("ELENCO LOCATIONS: \n");
		s.append(UtilityVisua.nodesPrint(petriLocations));
		s.append("ELENCO TRANSITIONS: \n");
		s.append(UtilityVisua.nodesPrint(petriTransitions));
		s.append("ELENCO LINKS: \n");
		s.append(UtilityVisua.linksList(this));
		return s;
	}

}
