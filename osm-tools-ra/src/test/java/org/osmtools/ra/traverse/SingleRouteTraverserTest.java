package org.osmtools.ra.traverse;

import static org.junit.Assert.*;
import static org.osmtools.ra.TestUtils.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.osmtools.ra.SegmentsBuilder;
import org.osmtools.ra.TestBase;
import org.osmtools.ra.data.Node;
import org.osmtools.ra.graph.IntersectionNode;

public class SingleRouteTraverserTest extends TestBase {

	private Collection<IntersectionNode> leaves;
	private IntersectionNode nodeA;
	private IntersectionNode nodeB;

	@Before
	public void setup() {
		leaves = executeAndGetLeaves(SegmentsBuilder.create().appendFlexible(4, 5).appendFlexible(4, 3)
				.appendFlexible(3, 2).appendFlexible(2, 1));

		Iterator<IntersectionNode> it = leaves.iterator();

		nodeA = it.next();
		nodeB = it.next();

	}

	@Test
	public void testTraverseOrderAtoB() throws Exception {

		SingleRouteTraverser traverser = new SingleRouteTraverser(nodeA, nodeB);
		List<Node> nodes = traverser.getPath();

		assertEquals(getNode(1), nodes.get(0));
		assertEquals(getNode(2), nodes.get(1));
		assertEquals(getNode(3), nodes.get(2));
		assertEquals(getNode(4), nodes.get(3));
		assertEquals(getNode(5), nodes.get(4));
	}

	@Test
	public void testTraverseOrderBtoA() throws Exception {

		SingleRouteTraverser traverser = new SingleRouteTraverser(nodeB, nodeA);
		List<Node> nodes = traverser.getPath();

		assertEquals(getNode(5), nodes.get(0));
		assertEquals(getNode(4), nodes.get(1));
		assertEquals(getNode(3), nodes.get(2));
		assertEquals(getNode(2), nodes.get(3));
		assertEquals(getNode(1), nodes.get(4));
	}
}
