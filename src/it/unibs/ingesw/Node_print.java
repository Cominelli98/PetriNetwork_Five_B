package it.unibs.ingesw;

import java.util.ArrayList;

public final class Node_print {
	
	
	public static StringBuffer getTransitionsList(Network n) {
		return createNList(n.getTransitions());
	}
	
	public static StringBuffer getTransitionsList(Petri_network pn) {
		return createPNList(pn.getTransitions());
	}

	private static StringBuffer createPNList(ArrayList<Petri_transition> t) {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i<t.size(); i++) {
			s.append(i + ")" + t.get(i).getName() + "\n");
		}
		return s;
	}
	
	private static StringBuffer createNList(ArrayList<Transition> t) {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i<t.size(); i++) {
			s.append(i + ")" + t.get(i).getName() + "\n");
		}
		return s;
	}
}
