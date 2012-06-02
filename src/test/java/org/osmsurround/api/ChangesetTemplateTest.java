package org.osmsurround.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osmsurround.test.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class ChangesetTemplateTest {

	@Autowired
	private ChangesetOperations changesetOperation;
	
	@Test
	public void testPutWay() {
		System.out.println(changesetOperation.getChangesetId());
		
	}

	@Test
	public void testPutNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutRelation() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutNewWay() {
		
	}

	@Test
	public void testPutNewNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutNewRelation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRelation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteWay() {
		fail("Not yet implemented");
	}

}
