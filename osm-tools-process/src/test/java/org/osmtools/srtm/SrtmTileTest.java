package org.osmtools.srtm;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SrtmTileTest {

	@Test
	public void testHasData() throws Exception {

		SrtmTile srtmTile = new SrtmTile(new File("N49E009.hgt"));

		assertTrue(srtmTile.hasData(9.1, 49.9));
		assertFalse(srtmTile.hasData(8.9, 49.9));
		assertFalse(srtmTile.hasData(8.9, 50));

	}

	@Test
	public void testElevation() throws Exception {
		SrtmTile srtmTile = new SrtmTile(SrtmTileTest.class.getResourceAsStream("/N49E009.hgt"), 9, 49);
		assertEquals(167, srtmTile.getElevation(9.16594, 49.27741)); // known height, don't change
		assertEquals(359, srtmTile.getElevation(9, 49));
		assertEquals(289, srtmTile.getElevation(9.999999, 49.999999));
	}
}
