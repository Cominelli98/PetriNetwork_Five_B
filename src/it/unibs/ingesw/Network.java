package it.unibs.ingesw;

import java.util.ArrayList;

import it.unibs.ingesw.utility.UtilityVisua;


public class Network implements GenericNetwork{
	
	private ArrayList<Location> locations;
	private ArrayList<Transition> transitions;
	private ArrayList<Link> netLinks;
	private int netId; 
	private String name;
	static int network_id = 0;			//Variabile statica che fornisce un id diverso ogni qualvolta si crea una rete
	private final int OFFSET = 10000;	//Costante che viene sommata all'id di un node nel caso quest'ultimo sia una location
	
	public Network (String name) {
		locations = new ArrayList<Location>();
		transitions = new ArrayList<Transition>();
		netLinks = new ArrayList<Link>();
		this.name = name;
		this.netId = ++network_id;
	}

	public void addLocation (String name) {
		locations.add(new Location(netId, locations.size()+OFFSET, name));
	}


	public void addTransition (String name) {
		transitions.add(new Transition(netId, transitions.size(), name));
	}
	
	public void addLink (Link l) {
		netLinks.add(l);
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}
	
	public Location getLocation(int i) {
		return this.locations.get(i);
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	public Transition getTransition(int i) {
		return this.transitions.get(i);
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public ArrayList<Link> getNetLinks() {
		return netLinks;
	}

	public void setNetLinks(ArrayList<Link> netLinks) {
		this.netLinks = netLinks;
	}

	public int getId() {
		return netId;
	}

	public void setNetId(int netId) {
		this.netId = netId;
	}
	
	public Transition getLastTransition() {
		return transitions.get(transitions.size()-1);
	}
	
	public Location getLastLocation() {
		return locations.get(locations.size()-1);
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * produce in uscita il nome della location o transition associata all'ID fornito in ingresso al metodo 
	 * @param int id
	 * @return String t.getName()
	 */
	public String nodeNameFromID(int id) {
		for(Location l : locations) {
			if(l.getId() == id)
				return l.getName();
		}
		for(Transition t : transitions) {
			if(t.getId() == id)
				return t.getName();
		}
		return null;
	}

	@Override
	public ArrayList<Link> getLinks() {
		return netLinks;
	}

	@Override
	public Link getLink(int i) {
		return netLinks.get(i);
	}

	@Override
	public String getLinkOrigin(int i) {
		return nodeNameFromID(netLinks.get(i).getOrigin()); 
	}

	@Override
	public String getLinkDestination(int i) {
		return nodeNameFromID(netLinks.get(i).getDestination()); 
	}

	@Override
	public StringBuffer print() {
		StringBuffer s = new StringBuffer("");
		s.append("ELENCO LOCATIONS: \n");
		s.append(UtilityVisua.nodesPrint(locations));
		s.append("ELENCO TRANSITIONS: \n");
		s.append(UtilityVisua.nodesPrint(transitions));
		s.append("ELENCO LINKS: \n");
		s.append(UtilityVisua.linksList(this));
		return s;
	}

	@Override
	public int getLinkOriginID(int i) {
		return netLinks.get(i).getOrigin();
	}

	@Override
	public int getLinkDestinationID(int i) {
		return netLinks.get(i).getDestination();
	}
	
	
	
	
	

}
