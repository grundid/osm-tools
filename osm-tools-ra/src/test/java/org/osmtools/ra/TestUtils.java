package org.osmtools.ra;

import org.osmtools.ra.analyzer.ConnectableNode;
import org.osmtools.ra.data.Node;
import org.osmtools.ra.data.Way;
import org.osmtools.ra.graph.Graph;
import org.osmtools.ra.graph.GraphCreator;
import org.osmtools.ra.graph.IntersectionNode;
import org.osmtools.ra.segment.ConnectableSegment;
import org.osmtools.ra.segment.FixedWay;
import org.osmtools.ra.segment.FlexibleWay;
import org.osmtools.ra.segment.RoundaboutWay;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.social.test.client.RequestMatchers;
import org.springframework.social.test.client.ResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.*;

public abstract class TestUtils {

	public static final long RELATION_12320_NECKARTAL_WEG = 12320;
	public static final long RELATION_37415 = 37415;
	public static final long RELATION_959757_LINE_10 = 959757;
	public static final long RELATION_954995_LINE_11 = 954995;

	private static final Map<Long, Node> NODES = new HashMap<Long, Node>();

	static {
		for (int x = 0; x < 30; x++) {
			putNode(x, x, x);
		}
	}

	private static void putNode(long id, float lon, float lat) {
		NODES.put(id, new Node(id, lat, lon));
	}

	public static MockRestServiceServer prepareRestTemplateForRelation(RestTemplate restTemplate, long relationId) {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_XML);
		mockServer
				.expect(RequestMatchers.requestTo("https://api.openstreetmap.org/api/0.6/relation/" + relationId
						+ "/full"))
				.andExpect(RequestMatchers.method(HttpMethod.GET))
				.andRespond(
						ResponseCreators.withResponse(new ClassPathResource("/relations/" + relationId + ".xml",
								ClassLoader.getSystemClassLoader()), responseHeaders));
		return mockServer;
	}

	public static List<ConnectableSegment> asSegments(List<Node>... lists) {
		List<ConnectableSegment> segments = new ArrayList<ConnectableSegment>();
		for (List<Node> nodes : lists) {
			segments.add(new FlexibleWay(nodes));
		}
		return segments;
	}

	public static Way asWay(long... nodeIds) {
		return new Way(0, asNodes(nodeIds));
	}

	public static FlexibleWay asFixedOrderWay(long... nodeIds) {
		return new FixedWay(asNodes(nodeIds));
	}

	public static FlexibleWay asFlexibleOrderWay(long... nodeIds) {
		return new FlexibleWay(asNodes(nodeIds));
	}

	public static ConnectableSegment asRoundaboutWay(long... nodeIds) {
		return new RoundaboutWay(asNodes(nodeIds));
	}

	public static List<Node> asNodes(long... nodeIds) {
		List<Node> nodes = new ArrayList<Node>();
		for (long id : nodeIds)
			nodes.add(getNode(id));
		return nodes;
	}

	public static Node getNode(long id) {
		return NODES.get(Long.valueOf(id));
	}

	public static void assertContainsNodeId(Collection<IntersectionNode> nodes, long nodeId) {
		for (IntersectionNode node : nodes) {
			if (node.getNode().getId() == nodeId)
				return;
		}
		fail("Node " + nodeId + " not found");
	}

	public static void assertContainsOnlyNodeIds(Collection<IntersectionNode> nodes, long... nodeIds) {
		assertEquals(nodeIds.length, nodes.size());
		for (IntersectionNode node : nodes) {

			boolean found = false;
			for (long nodeId : nodeIds) {
				if (node.getNode().getId() == nodeId)
					found = true;
			}
			if (!found) {
				fail("Node " + node.getNode() + " was not expected");
			}
		}
	}

	public static void assertContainsNodeIds(Collection<ConnectableNode> connectableNodes, long... nodeIds) {

		Set<Long> idSet = new HashSet<Long>();
		for (long id : nodeIds)
			idSet.add(id);
		for (ConnectableNode connectableNode : connectableNodes) {

			for (Iterator<Node> it = connectableNode.getNodesIterator(); it.hasNext();) {
				assertTrue(idSet.contains(it.next().getId()));
			}
		}
	}

	public static void assertNodesInOrder(long[] expected, Collection<Node> actual) {
		Iterator<Node> it = actual.iterator();
		for (long id : expected)
			assertEquals(TestUtils.getNode(id), it.next());
	}

	public static Collection<IntersectionNode> executeAndGetLeaves(SegmentsBuilder segmentsBuilder) {
		return executeAndGetLeaves(segmentsBuilder.getSegments());
	}

	public static Collection<IntersectionNode> executeAndGetLeaves(List<ConnectableSegment> segments) {
		GraphCreator intersectionNodeWebCreator = new GraphCreator(segments);
		Graph intersectionWeb = intersectionNodeWebCreator.getGraph();
		return intersectionWeb.getLeaves();
	}

	public static List<ConnectableSegment> asList(Object... objects) {
		List<ConnectableSegment> list = new ArrayList<ConnectableSegment>();
		for (Object o : objects)
			list.add((ConnectableSegment)o);
		return list;
	}

	public static void assertNodesInOrder(ConnectableSegment segment, Node firstNode, Node lastNode, long... nodeIds) {
		List<Node> nodes = new ArrayList<Node>();
		segment.appendNodesBetween(nodes, firstNode, lastNode);
		for (int x = 0; x < nodeIds.length; x++) {
			assertEquals(nodeIds[x], nodes.get(x).getId());
		}
	}

}
