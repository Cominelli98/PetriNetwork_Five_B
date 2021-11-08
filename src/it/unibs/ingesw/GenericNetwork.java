package it.unibs.ingesw;

import java.util.ArrayList;

public interface GenericNetwork extends IDNameGiver, Printable{
	
	public ArrayList<? extends Node> getTransitions();
	
	public ArrayList<? extends Node> getLocations();
	
	public ArrayList<Link> getLinks();
	
	public Node getTransition(int i);
	
	public Node getLocation(int i);
	
	public Link getLink(int i);
	
	public String getLinkOrigin(int i);
	
	public String getLinkDestination(int i);

}
