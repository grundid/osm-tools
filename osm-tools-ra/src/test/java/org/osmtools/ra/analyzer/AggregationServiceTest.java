package org.osmtools.ra.analyzer;

import static org.junit.Assert.*;
import static org.osmtools.ra.TestUtils.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.osmtools.ra.HelperService;
import org.osmtools.ra.TestBase;
import org.osmtools.ra.TestUtils;
import org.osmtools.ra.context.AnalyzerContext;
import org.osmtools.ra.segment.ConnectableSegment;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class AggregationServiceTest extends TestBase {

	@Autowired
	private AggregationService aggregationService;
	@Autowired
	private HelperService helperService;

	@Test
	public void testAggregateSegmentsEmpty() throws Exception {
		List<AggregatedSegment> list = aggregationService.aggregateSegments(Collections.EMPTY_LIST);
		assertTrue(list.isEmpty());
	}

	@Test
	public void testAggregateSegmentsSingle() throws Exception {
		List<ConnectableSegment> segments = asList(asFixedOrderWay(1));

		List<AggregatedSegment> list = aggregationService.aggregateSegments(segments);
		assertEquals(1, list.size());
	}

	@Test
	public void testAggregateSegmentsTwoUnconnected() throws Exception {
		List<ConnectableSegment> segments = asList(asFixedOrderWay(1, 2), asFixedOrderWay(3, 4));

		List<AggregatedSegment> list = aggregationService.aggregateSegments(segments);
		assertEquals(2, list.size());
	}

	@Test
	public void testAggregateSegmentsTwoConnected() throws Exception {
		List<ConnectableSegment> segments = asList(asFixedOrderWay(1, 2), asFixedOrderWay(2, 3));

		List<AggregatedSegment> list = aggregationService.aggregateSegments(segments);
		assertEquals(1, list.size());
		assertEquals(AggregatedSegment.class, list.get(0).getClass());
	}

	private static void assertAggregatedSegments(List<AggregatedSegment> aggregatedSegments,
			int expectedAggregatedSegemnts, int... segmentsPerEntry) {
		assertEquals(expectedAggregatedSegemnts, aggregatedSegments.size());
		for (int x = 0; x < expectedAggregatedSegemnts; x++) {
			assertEquals(segmentsPerEntry[x], aggregatedSegments.get(x).getSegments().size());
		}
	}

	@Test
	public void testAggregate12320() throws Exception {
		AnalyzerContext analyzerContext = helperService
				.createInitializedContext(TestUtils.RELATION_12320_NECKARTAL_WEG);
		aggregationService.aggregate(analyzerContext);
		assertAggregatedSegments(analyzerContext.getAggregatedSegments(), 1, 931);
	}

	@Test
	public void testAggregate37415() throws Exception {
		AnalyzerContext analyzerContext = helperService.createInitializedContext(TestUtils.RELATION_37415);
		aggregationService.aggregate(analyzerContext);
		assertAggregatedSegments(analyzerContext.getAggregatedSegments(), 1, 73);
	}

	@Test
	public void testAggregate959757() throws Exception {
		AnalyzerContext analyzerContext = helperService.createInitializedContext(TestUtils.RELATION_959757_LINE_10);
		aggregationService.aggregate(analyzerContext);
		assertAggregatedSegments(analyzerContext.getAggregatedSegments(), 1, 111);
	}

	@Test
	public void testAggregate954995() throws Exception {
		AnalyzerContext analyzerContext = helperService.createInitializedContext(954995);
		aggregationService.aggregate(analyzerContext);
		assertAggregatedSegments(analyzerContext.getAggregatedSegments(), 1, 52);
	}
}
