package it.unibs.ingesw;

public class Priority_network extends Petri_network implements IDNameGiver{
	
	static int priorityNetID = 0;	//Variabile statica per assegnare un net diverso ad ogni rete di petri con priorità
	private int priority_NetID;
	
	public Priority_network(Petri_network pn, String name) {
		super(pn, name);
		this.priority_NetID = ++priorityNetID;
	}
	
	public int getPetriNetID() {
		return this.petriNetId;
	}
	
	public int getId() {
		return this.priority_NetID;
	}
	
	public int getTransitionPriority(int i) {
		return petriTransitions.get(i).getPriority();
	}

}
