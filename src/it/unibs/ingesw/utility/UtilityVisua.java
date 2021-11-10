package it.unibs.ingesw.utility;

import java.util.ArrayList;

import it.unibs.ingesw.GenericNetwork;
import it.unibs.ingesw.IDNameGiver;
import it.unibs.ingesw.Link;
import it.unibs.ingesw.Node;

public class UtilityVisua {
	public static StringBuffer linksList(GenericNetwork net) {
		StringBuffer list = new StringBuffer();
		ArrayList<Link> links = net.getLinks();
		for(int i = 0 ; i < links.size() ; i++) {
			list.append(net.getLinkOrigin(i)+" ----> "+net.getLinkDestination(i)+"\n");
		}
		return list;
	}
	
	public static StringBuffer nodesPrint(ArrayList<? extends Node> nodes) {
		StringBuffer s = new StringBuffer("");
		for (Node n : nodes)
			s.append(n.print());
		return s;
	}
	
	public static StringBuffer numberedNodesPrint(ArrayList<? extends Node> nodes) {
		StringBuffer s = new StringBuffer("");
		int i=0;
		for (Node n : nodes)
			s.append(i++ + ") " + n.print());
		return s;
	}
	
	public static StringBuffer numberedNamesPrint(ArrayList<? extends IDNameGiver> toList) {
		StringBuffer s = new StringBuffer();
		int i=0;
		for (IDNameGiver temp : toList)
			s.append(i++ + ") " + temp.getName());
		return s;
	}
	
	
	public static StringBuffer printMenu(String [] toPrint) {
		StringBuffer out = new StringBuffer();
		for(String s :toPrint)
			out.append(s+"\n");
		return out;
		}
}
