package it.unibs.ingesw.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;

import it.unibs.ingesw.GenericNetwork;
import it.unibs.ingesw.Link;
import it.unibs.ingesw.NetworkNode;
import it.unibs.ingesw.Node;
import it.unibs.ingesw.PetriNode;


public final class UtylityPrint {
	
	public static StringBuffer getList(NetworkNode[] nodes) {
		StringBuffer list = new StringBuffer();
		for(int i = 0 ; i < nodes.length ; i++)
			list.append(i+")"+nodes[i].getName()+"\n");
		return null;
	}
	
	public static StringBuffer getList(PetriNode[] nodes) {
		StringBuffer list = new StringBuffer();
		for(int i = 0 ; i < nodes.length ; i++) {
			list.append(i+")"+stringValue(nodes[i])+stringPriority(nodes[i])+"\n");
		}
		return list;
	}
	
	public static StringBuffer getList(GenericNetwork net) {
		StringBuffer list = new StringBuffer();
		ArrayList<Link> links = net.getLinks();
		for(int i = 0 ; i < links.size() ; i++) {
			list.append(net.getLinkOrigin(i)+" ----> "+net.getLinkDestination(i));
		}
		return list;
	}
	/*CANTIERE
	public static StringBuffer printNet(GenericNetwork n) {
		StringBuffer list = new StringBuffer();
		list.append("LOCATION:"+"\n"+getList(n.getLocations()));
		return null;
	}
	*/
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

