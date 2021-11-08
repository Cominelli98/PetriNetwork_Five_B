package it.unibs.ingesw;

public class Petri_location implements PetriNode{
	
	private int token;
	private int netId;
	private int nodeId;
	private String nodeName;
	
	public Petri_location(int netId, int nodeId, String nodeName) {
		
		this.netId = netId;
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.token = 0;
	}
	
	public void setToken (int token) {
		this.token = token;
	}
	
	public int getValue () {
		return token;
	}
	
	public void reduceToken (int reduction) {
		token = token - reduction;
	}
	
	public void addToken (int addition) {
		token = token + addition;
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

	@Override
	public int getPriority() {
		return -1;
	}

	@Override
	public StringBuffer print() {
		StringBuffer s = new StringBuffer("");
		s.append(nodeName +"  "+ "token:" + token  + "\n");
		return s;
	}
	
}
