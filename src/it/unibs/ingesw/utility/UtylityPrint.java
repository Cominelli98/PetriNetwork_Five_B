package it.unibs.ingesw.utility;

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
			String priority = null;
			Integer temp = Integer.valueOf(nodes[i].getPriority());
			if(temp != -1)
				priority =  Integer.toString(nodes[i].getPriority());
			list.append(i+")"+nodes[i].getName()+"\t"+nodes[i].getValue()+"\n"+"   "+priority);
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
}