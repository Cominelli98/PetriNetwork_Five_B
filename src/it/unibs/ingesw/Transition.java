package it.unibs.ingesw;

import java.util.ArrayList;

public class Transition implements NetworkNode {

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
	
	public ArrayList<String> getInformation() {
		ArrayList<String> information = new ArrayList<>();
		information.add(this.getName());
		return information;
	}

}
