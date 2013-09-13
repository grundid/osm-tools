package org.osmtools.osmchange;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class SequenceIteratorTest {

	@Test
	public void itShouldIterateSequenceUrls() throws Exception {
		Iterator<String> it = new SequenceIterator("hour", 999, 1001);
		assertEquals("http://planet.openstreetmap.org/replication/hour/000/001/000.osc.gz", it.next());
		assertEquals("http://planet.openstreetmap.org/replication/hour/000/001/001.osc.gz", it.next());
		assertFalse(it.hasNext());
	}
}
