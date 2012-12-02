package org.osmtools.ra.traverse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.osmtools.api.Section;
import org.osmtools.ra.TestBase;
import org.osmtools.ra.TestUtils;
import org.osmtools.ra.context.AnalyzerContext;
import org.osmtools.ra.data.Node;
import org.osmtools.ra.export.SectionContainer;
import org.osmtools.ra.graph.Graph;
import org.osmtools.ra.graph.IntersectionNode;
import org.osmtools.ra.segment.ConnectableSegment;
import org.springframework.beans.factory.annotation.Autowired;

public class TraverseServiceTest extends TestBase {

	@Autowired
	private TraverseService traverseService;

	@Test
	public void testTraverse() throws Exception {

		long relationId = TestUtils.RELATION_12320_NECKARTAL_WEG;

		AnalyzerContext analyzerContext = helperService.createGraphContext(relationId);

		Graph intersectionWeb = analyzerContext.getGraphs().get(0);

		Iterator<IntersectionNode> it = intersectionWeb.getLeaves().iterator();

		IntersectionNode startNode = it.next();
		IntersectionNode endNode = it.next();

		List<Node> traverse = traverseService.traverse(intersectionWeb, startNode, endNode);

		List<Section> sections = new ArrayList<Section>();
		SectionContainer sectionContainer = new SectionContainer("test");
		sectionContainer.addCoordinates(traverse);
		sections.add(sectionContainer);
	}

	@Test
	public void testFillInNodes() throws Exception {
		List<Node> list = TestUtils.asNodes(1, 3, 1);
		List<ConnectableSegment> segments = TestUtils
				.asSegments(TestUtils.asNodes(1, 2, 3), TestUtils.asNodes(3, 4, 1));

		List<Node> nodes = traverseService.fillInNodes(list, segments);

		for (int x = 1; x <= 4; x++)
			assertTrue("should contain node id: " + x, nodes.contains(TestUtils.getNode(x)));

	}

}
