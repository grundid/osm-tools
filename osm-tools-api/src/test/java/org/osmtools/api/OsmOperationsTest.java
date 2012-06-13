package org.osmtools.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osm.schema.OsmWay;
import org.osmtools.api.OsmOperations;
import org.osmtools.test.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class OsmOperationsTest {

	@Autowired
	@Qualifier("osm")
	private OsmOperations osmOperations;

	@Test
	@Ignore
	public void testGetNode() throws Exception {
		osmOperations.getForNode(100);
	}
	
	@Test
	@Ignore
	public void testGetRelation() {
		osmOperations.getForRelation(new Long(142715));
	}

	@Test
	@Ignore
	public void testGetForManyWays() throws Exception {

		Collection<Long> wayIds = new ArrayList<Long>();
		wayIds.add(new Long(25915581));
		wayIds.add(new Long(27404248));
		wayIds.add(new Long(28177727));

		List<OsmWay> ways = osmOperations.getForManyWays(wayIds);
		assertEquals(3, ways.size());
	}
}