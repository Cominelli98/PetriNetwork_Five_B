package it.unibs.ingesw.utility;

import java.util.ArrayList;

import it.unibs.ingesw.GenericNetwork;
import it.unibs.ingesw.Node;
import it.unibs.ingesw.Printable;


public class UtilityPrint {
	
	public static StringBuffer printObjct(Printable item) {
		StringBuffer print = new StringBuffer();
		for(String s : item.print())
			print.append(s+ "  ");
		print.append("\n");
		return print;
	}
	
	public static StringBuffer printObjct(ArrayList<? extends Printable> items) {
		StringBuffer print = new StringBuffer();
		for(Printable item : items)
			print.append(printObjct(item));
		return print;

	}
	
	public static StringBuffer PrintObjct(GenericNetwork n) {
		StringBuffer print = new StringBuffer();
		print.append("ELENCO LOCATIONS: \n");
		for(Node node : n.getLocations()) {
			for(String s : node.print())
				print.append(s + "  ");
			print.append("\n");
		}
		print.append("ELENCO TRANSITION: \n");
		for(Node node : n.getTransitions()) {
			for(String s : node.print())
				print.append(s + "  ");
			print.append("\n");
		}
		print.append("ELENCO LINK: \n");
		print.append(UtilityVisua.linksList(n));
		return print;
	}
	
}		
