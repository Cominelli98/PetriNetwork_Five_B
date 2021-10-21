package it.unibs.ingesw;

import java.util.ArrayList;

public final class Converter {
	
	/**
	 * Utilizza toPetri in un ciclo for per trasformare ogni Location in una Petri_location
	 * @param l
	 * @param petriNetId
	 * @return ArrayList<Petri_location> temp
	 */
	public static ArrayList<Petri_location> toPetriLocations (ArrayList<Location> l, int petriNetId){
		
		ArrayList<Petri_location> temp = new ArrayList<>();
		for (Location location : l) {
			temp.add(toPetri(location, petriNetId));
		}
		return temp;
	}
	
	/**
	 * Utilizza toPetri in un ciclo for per trasformare ogni Transition in una Petri_transition
	 * @param t
	 * @param petriNetId
	 * @return ArrayList<Petri_location> temp
	 */
	
	public static ArrayList<Petri_transition> toPetriTransitions (ArrayList<Transition> t, int petriNetId){
		
		ArrayList<Petri_transition> temp = new ArrayList<>();
		for (Transition transition : t) {
			temp.add(toPetri(transition, petriNetId));
		}
		return temp;
	}
	
	/**
	 * Utilizza toPetri in un ciclo for per ritornare un elenco aggiornato di links colleganti Petri_location e Petri_transition
	 * @param links
	 * @param petriNetId
	 * @return ArrayList<Petri_location> temp
	 */
	public static ArrayList<Link> toPetriLinks (ArrayList<Link> links, int petriNetId){
		ArrayList<Link> temp = new ArrayList<>();
		for(Link link : links) {
			temp.add(toPetri(link, petriNetId));
		}
		return temp;
	}
	
	public static Link toPetri (Link l, int petriNetId) {
		Link newLink = new Link(l.getLocation(), l.getTransition(), petriNetId, l.getOrientation());
		return newLink;
	}
	
	public static Petri_location toPetri (Location l, int petriNetId) {
		return new Petri_location(l, petriNetId);
	}
	
	public static Petri_transition toPetri (Transition t, int petriNetId) {
		return new Petri_transition(t, petriNetId);
	}
}
