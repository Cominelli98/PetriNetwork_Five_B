package it.unibs.ingesw;

import java.util.ArrayList;

public class Location implements NetworkNode{

	private int netId;
	private int nodeId;
	private String name;
	
	public Location(int netId, int nodeId, String name) {
		
		this.netId = netId;
		this.nodeId = nodeId;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return nodeId;
	}

	@Override
	public int getNetID() {
		return netId;
	}

	@Override
	public ArrayList<String> getInformation() {
		ArrayList<String> information = new ArrayList<>();
		information.add(this.getName());
		return information;
	}

}
