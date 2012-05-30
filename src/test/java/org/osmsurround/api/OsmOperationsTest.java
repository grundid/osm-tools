package org.osmsurround.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osm.schema.OsmWay;
import org.osmsurround.oauth.OauthTokens;
import org.osmsurround.test.TestConfiguration;
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
	public void testGetNode() throws Exception {
		osmOperations.getForNode(10);
	}

	@Test
	public void testGetForManyWays() throws Exception {

		Collection<Long> wayIds = new ArrayList<Long>();
		wayIds.add(new Long(25915581));
		wayIds.add(new Long(27404248));
		wayIds.add(new Long(28177727));

		List<OsmWay> ways = osmOperations.getForManyWays(wayIds);
		assertEquals(3, ways.size());
	}

	@Test
	public void testUserOperations() throws Exception {

		UserOperations userOperations = osmOperations.userOperations(new OauthTokens(
				"qd3ldbODivORsqmg9mYLiPALOYCaU5yXCYhQ8OzO", "rdlCqscibIAMkx3QVULSah3gUdkXsYSF1moPW5SF"));
		System.out.println(userOperations.getForUser());

	}

}
