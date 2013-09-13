package org.osmtools.osmchange;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateSplitterTest {

	@Test
	public void itShouldSplitState() throws Exception {
		assertState(0, 0, 0, StateSplitter.split(0));
		assertState(0, 0, 1, StateSplitter.split(1));
		assertState(0, 0, 999, StateSplitter.split(999));
		assertState(0, 1, 0, StateSplitter.split(1000));
		assertState(0, 999, 0, StateSplitter.split(999000));
		assertState(1, 0, 0, StateSplitter.split(1000000));
		assertState(999, 0, 0, StateSplitter.split(999000000));
		assertState(999, 999, 999, StateSplitter.split(999999999));
		assertState(0, 0, 0, StateSplitter.split(1000000000));
	}

	private void assertState(int a, int b, int c, State state) {
		assertEquals(a, state.getA());
		assertEquals(b, state.getB());
		assertEquals(c, state.getC());
	}
}
