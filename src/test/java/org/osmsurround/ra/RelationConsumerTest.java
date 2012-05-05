package org.osmsurround.ra;

import java.util.HashMap;

import org.junit.Test;
import org.osmsurround.TestBase;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationConsumerTest extends TestBase {

	@Autowired
	private RelationConsumer relationConsumer;

	@Test
	public void testConsumer() throws Exception {

		Relation relation = new Relation();
		relation.setTags(new HashMap<String, String>());

		relationConsumer.consume(relation);
		relationConsumer.finishConsuming();
	}
}
