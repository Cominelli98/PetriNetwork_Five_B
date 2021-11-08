package it.unibs.ingesw.tests;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import it.unibs.ingesw.*;

public class TestsProva {

	@Test
	public void testNetwork() {
//		ArrayList<Network> netList = new ArrayList<Network>();
//		Menu_Reti.createNetwork(netList);
		Network net1 = new Network("Paganel");
		Network net2 = new Network("Paganela");
//		netList.add(net1);
//		netList.add(net2);
		net1.addLocation("loc1-0");
		net2.addLocation("loc2-0");
		net1.addLocation("loc1-1");
		net2.addLocation("loc2-1");
		net1.addTransition("tran1");
		net2.addTransition("tran2");
		Link l10 = new Link(net1.getLocation(0).getId(), net1.getTransition(0).getId(), net1.getId(), 1);
		Link l11 = new Link(net1.getTransition(0).getId(), net1.getLocation(1).getId(), net1.getId(), 1);
		Link l20 = new Link(net2.getLocation(0).getId(), net2.getTransition(0).getId(), net2.getId(), 1);
		Link l21 = new Link(net2.getTransition(0).getId(), net2.getLocation(1).getId(), net2.getId(), 1);

		
		
//		AssertEquals(net1, net2);
		assertNotEquals(net1.getName(), net2.getName());
//		assertEquals(net1.getLocations().size(), net2.getLocations().size());
//		assertEquals(net1.getName(), net2.getName(), "ciao");
		assertAll(
				() -> assertEquals(net1.getLocations().size(), net2.getLocations().size()),
				() -> assertEquals(net1.getTransitions().size(), net2.getTransitions().size()),
				() -> assertEquals(l10.getDestination(), l11.getOrigin())
				);
//		
	}
	
	@Test
	public void testNetwork1() {
		ArrayList<Network> netList = new ArrayList<Network>();
		Menu_Reti.createNetwork(netList);
		assert(true);
		
	}
	
}
