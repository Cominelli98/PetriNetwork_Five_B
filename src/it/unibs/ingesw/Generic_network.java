package it.unibs.ingesw;

import java.util.ArrayList;

public interface Generic_network extends IDNameGiver {
	public <T extends GenericNode> ArrayList<T> getTransitions();
	
	public <T extends GenericNode> ArrayList<T> getLocations();
	
	public ArrayList<Link> getLinks();
		
	
}
