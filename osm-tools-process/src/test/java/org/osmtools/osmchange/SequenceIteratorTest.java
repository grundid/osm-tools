package org.osmtools.osmchange;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SequenceIteratorTest {

	@Test
	public void itShouldIterateSequenceUrls() throws Exception {
		Iterator<Sequence> it = new SequenceIterator(Granularity.hour, 999, 1001);
		assertEquals("https://planet.openstreetmap.org/replication/hour/000/001/000.osc.gz", it.next().getUrl());
		assertEquals("https://planet.openstreetmap.org/replication/hour/000/001/001.osc.gz", it.next().getUrl());
		assertFalse(it.hasNext());
	}
}
