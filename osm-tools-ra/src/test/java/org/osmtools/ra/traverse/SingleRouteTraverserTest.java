package org.osmtools.ra.traverse;

import org.junit.Before;
import org.junit.Test;
import org.osmtools.ra.SegmentsBuilder;
import org.osmtools.ra.TestBase;
import org.osmtools.ra.data.Node;
import org.osmtools.ra.graph.IntersectionNode;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.osmtools.ra.TestUtils.executeAndGetLeaves;

public class SingleRouteTraverserTest extends TestBase {

    private Collection<IntersectionNode> leaves;
    private IntersectionNode nodeA;
    private IntersectionNode nodeB;

    @Before
    public void setup() {
        leaves = executeAndGetLeaves(
                SegmentsBuilder.create().appendFlexible(4, 5).appendFlexible(4, 3).appendFlexible(3, 2)
                        .appendFlexible(2, 1));
        Iterator<IntersectionNode> it = leaves.iterator();
        nodeA = it.next();
        nodeB = it.next();
    }

    @Test
    public void testTraverseOrderAtoB() throws Exception {
        SingleRouteTraverser traverser = new SingleRouteTraverser(nodeA, nodeB);
        List<Node> nodes = traverser.getPath();
        assertEquals(nodeA.getNode(), nodes.get(0));
        assertEquals(nodeB.getNode(), nodes.get(4));
    }

    @Test
    public void testTraverseOrderBtoA() throws Exception {
        SingleRouteTraverser traverser = new SingleRouteTraverser(nodeB, nodeA);
        List<Node> nodes = traverser.getPath();
        assertEquals(nodeB.getNode(), nodes.get(0));
        assertEquals(nodeA.getNode(), nodes.get(4));
    }
}
