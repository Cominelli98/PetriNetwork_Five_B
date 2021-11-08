package it.unibs.ingesw.utility;

import java.util.ArrayList;

import it.unibs.ingesw.GenericNetwork;
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
}
