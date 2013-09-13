package org.osmtools;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.osmtools.formats.FormatUtils;
import org.osmtools.osc.OsmChange;

public class OsmChangeTest {

	@Test
	public void itShouldParseADemoFile() throws Exception {
		InputStream in = OsmChangeTest.class.getClassLoader().getResourceAsStream("test.osc");
		Unmarshaller unmarshaller = FormatUtils.createOscUnmarshaller();
		JAXBElement<OsmChange> unmarshal = (JAXBElement<OsmChange>)unmarshaller.unmarshal(in);
		OsmChange osmChange = unmarshal.getValue();
		assertEquals(3, osmChange.getCreate().size());
		assertEquals(3, osmChange.getDelete().size());
		assertEquals(3, osmChange.getModify().size());
		assertEquals("0.6", osmChange.getVersion());
	}
}
