package it.unibs.ingesw;

public class Transition implements Node {

	private int netId;
	private int nodeId;
	private String nodeName;
	
	public Transition(int netId, int nodeId, String nodeName) {
		
		this.netId = netId;
		this.nodeId = nodeId;
		this.nodeName = nodeName;
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
	public int getNetID() {
		return netId;
	}

}
