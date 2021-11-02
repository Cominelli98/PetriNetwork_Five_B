package it.unibs.ingesw;

public class Petri_transition implements PetriNode{
	private int cost;
	private int priority;
	private int netId;
	private int nodeId;
	private String nodeName;
	
	public Petri_transition(int netId, int nodeId, String nodeName) {
		
		this.netId = netId;
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.cost = 1;
		this.priority = -1;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getValue() {
		return this.cost;
	}
	
	@Override
	public int getNetID() {
		return netId;
	}

	@Override
	public String getName() {
		return nodeName;
	}

	@Override
	public int getId() {
		return nodeId;
	}
}
