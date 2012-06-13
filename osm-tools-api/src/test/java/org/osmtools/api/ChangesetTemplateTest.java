package org.osmtools.api;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osm.schema.OsmNd;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmWay;
import org.osmtools.test.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class ChangesetTemplateTest {
	
	@Autowired
	@Qualifier("changeset")
	private ChangesetOperations changesetOperations;

	@Test
	public void testPutWay() {
		long nodeId = changesetOperations.putNewWay(this.getWay());
		
		System.out.println(nodeId);
	}

	@Test
	@Ignore
	public void testPutNode() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPutRelation() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPutNewWay() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPutNewNode() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPutNewRelation() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDeleteNode() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDeleteRelation() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDeleteWay() {
		fail("Not yet implemented");
	}
	
	private OsmWay getWay() {
		OsmWay osmWay = new OsmWay();

		OsmNd fromNode = new OsmNd();
		fromNode.setRef(new BigInteger("45345345"));
		
		OsmNd toNode = new OsmNd();
		toNode.setRef(new BigInteger("535345333"));
		
		osmWay.getNd().add(fromNode);
		osmWay.getNd().add(toNode);
		
		return osmWay;
	}
}