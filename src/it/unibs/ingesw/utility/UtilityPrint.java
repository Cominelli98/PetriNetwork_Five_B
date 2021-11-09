package it.unibs.ingesw.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;

import it.unibs.ingesw.GenericNetwork;
import it.unibs.ingesw.Link;
import it.unibs.ingesw.NetworkNode;
import it.unibs.ingesw.Node;
import it.unibs.ingesw.PetriNode;


public final class UtilityPrint {
	
	public static StringBuffer getList(GenericNetwork net) {
		StringBuffer list = new StringBuffer();
		ArrayList<Link> links = net.getLinks();
		for(int i = 0 ; i < links.size() ; i++) {
			list.append(net.getLinkOrigin(i)+" ----> "+net.getLinkDestination(i)+"\n");
		}
		return list;
	}
	
	public static String printNode(NetworkNode node) {
		return node.getName()+"\n";
	}
	
	public static String printNode(PetriNode node) {
		return node.getName()+"  "+stringValue(node)+"  "+stringPriority(node)+"\n";
	}
	
	private static String getValueName(PetriNode pn, int variable) {
		Field f[] = pn.getClass().getDeclaredFields();
		return f[variable].getName();
	}
	
	private static String stringValue(PetriNode pn) {
		return getValueName(pn, 0)+": "+pn.getValue();
	}
	
	private static String stringPriority(PetriNode pn) {
		if(pn.getPriority() == -1)
			return null;
		return "priorità: "+Integer.toString(pn.getPriority());
	}
}		

