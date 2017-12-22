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

public class SingleRouteTraverserRoundaboutTest extends TestBase {

    private Collection<IntersectionNode> leaves;
    private IntersectionNode node;

    @Before
    public void setup() {
        leaves = executeAndGetLeaves(
                SegmentsBuilder.create().appendFlexible(5, 4).appendFlexible(4, 3).appendFlexible(3, 2)
                        .appendFlexible(2, 1).appendFlexible(5, 1));
        Iterator<IntersectionNode> it = leaves.iterator();
        node = it.next();
    }

    @Test
    public void testTraverseRoundabout() throws Exception {
        SingleRouteTraverser traverser = new SingleRouteTraverser(node, node);
        List<Node> nodes = traverser.getPath();
        assertEquals(node.getNode(), nodes.get(0));
        assertEquals(node.getNode(), nodes.get(5));
    }
}
