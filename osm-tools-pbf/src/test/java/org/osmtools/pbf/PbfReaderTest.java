package org.osmtools.pbf;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

import crosby.binary.file.BlockInputStream;

public class PbfReaderTest {

	@Test
	public void testParse() throws Exception {

		DummyOsmProcessor osmProcessor = new DummyOsmProcessor();

		InputStream inputStream = PbfReaderTest.class.getClassLoader().getResourceAsStream("libya.osm.pbf");

		BlockInputStream bis = new BlockInputStream(inputStream, new OsmPbfParser(osmProcessor));
		bis.process();

		assertEquals(524781, osmProcessor.getNodes());
		assertEquals(51939, osmProcessor.getWays());
		assertEquals(81, osmProcessor.getRelations());
	}
}
