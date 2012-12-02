package org.osmtools.ra.analyzer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.osmtools.ra.TestBase;
import org.osmtools.ra.TestUtils;
import org.osmtools.ra.context.AnalyzerContext;
import org.osmtools.ra.context.AnalyzerContextService;
import org.osmtools.ra.segment.ConnectableSegment;
import org.osmtools.ra.segment.FlexibleWay;
import org.osmtools.ra.segment.RoundaboutWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RelationMemberServiceTest extends TestBase {

	@Autowired
	private RelationMemberService relationMemberService;
	@Autowired
	private AnalyzerContextService analyzerContextService;
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testSplitRelationByRole() throws Exception {
		TestUtils.prepareRestTemplateForRelation(restTemplate, TestUtils.RELATION_37415);
		AnalyzerContext analyzerContext = analyzerContextService.createAnalyzerContext(TestUtils.RELATION_37415, false);

		relationMemberService.initSegments(analyzerContext);
		assertNotNull(analyzerContext.getSegments());

		assertEquals(73, analyzerContext.getSegments().size());
	}

	@Test
	public void testSplitRelationByRoleEmpty() throws Exception {
		TestUtils.prepareRestTemplateForRelation(restTemplate, TestUtils.RELATION_12320_NECKARTAL_WEG);

		AnalyzerContext analyzerContext = analyzerContextService.createAnalyzerContext(
				TestUtils.RELATION_12320_NECKARTAL_WEG, false);

		relationMemberService.initSegments(analyzerContext);
		assertNotNull(analyzerContext.getSegments());

		assertEquals(931, analyzerContext.getSegments().size());
		assertEquals(926, countInstances(FlexibleWay.class, analyzerContext.getSegments()));
		assertEquals(5, countInstances(RoundaboutWay.class, analyzerContext.getSegments()));
	}

	private int countInstances(Class<?> classToCount, List<ConnectableSegment> segments) {
		int instances = 0;
		for (ConnectableSegment segment : segments) {
			if (segment.getClass().getName().equals(classToCount.getName()))
				instances++;
		}
		return instances;
	}
}
