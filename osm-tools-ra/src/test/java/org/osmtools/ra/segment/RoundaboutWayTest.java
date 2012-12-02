package org.osmtools.ra.segment;

import static org.osmtools.ra.TestUtils.*;

import org.junit.Before;
import org.junit.Test;
import org.osmtools.ra.segment.RoundaboutWay;

public class RoundaboutWayTest {

	private RoundaboutWay roundaboutWay;

	@Before
	public void setup() {
		roundaboutWay = (RoundaboutWay)asRoundaboutWay(1, 2, 3, 4, 5, 1);
	}

	@Test
	public void testAppendNodesBetween() throws Exception {
		assertNodesInOrder(roundaboutWay, getNode(1), getNode(1), 2, 3, 4, 5, 1);
		assertNodesInOrder(roundaboutWay, getNode(1), getNode(5), 2, 3, 4, 5);
		assertNodesInOrder(roundaboutWay, getNode(4), getNode(3), 5, 1, 2, 3);
	}
}
