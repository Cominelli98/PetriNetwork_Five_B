package it.unibs.ingesw.tests;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import it.unibs.ingesw.*;

public class TestsProva {

	@Test
	public void testNetwork() {
		Network net1 = new Network("Paganel");
		Network net2 = new Network("Paganela");
		
		
//		AssertEquals(net1.getName() , net2.getName());
		assertNotEquals(net1.getName(), net2.getName());
//		assertEquals(net1.getName(), net2.getName(), "ciao");
	
	}
	
}
