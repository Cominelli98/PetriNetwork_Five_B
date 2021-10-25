package it.unibs.ingesw;

public class Priority_network extends Petri_network{
	
	static int priorityNetID = 0;	//Variabile statica per assegnare un net diverso ad ogni rete di petri con priorit�
	private int priority_NetID;
	
	public Priority_network(Petri_network pn, String name) {
		super(pn, name);
		this.priority_NetID = ++priorityNetID;
	}
	
	public StringBuffer getTransitionsList() {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i<petriTransitions.size(); i++) {
			s.append(i + ")" + petriTransitions.get(i).getName() 
					+ " costo: " + petriTransitions.get(i).getValue() 
					+ " priorit�: " + petriTransitions.get(i).getPriority() + "\n");
		}
		return s;
	}
	
	public int getPetriNetID() {
		return this.petriNetId;
	}
	
	public int getPriorityNetID() {
		return this.priority_NetID;
	}
	
	public int getId() {
		return this.priority_NetID;
	}

}
