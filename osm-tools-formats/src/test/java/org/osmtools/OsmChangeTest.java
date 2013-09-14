package org.osmtools;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.osmtools.formats.FormatUtils;
import org.osmtools.osc.OsmChange;

public class OsmChangeTest {

	@Test
	public void itShouldParseADemoFile() throws Exception {
		InputStream in = OsmChangeTest.class.getClassLoader().getResourceAsStream("test.osc");
		Unmarshaller unmarshaller = FormatUtils.createOscUnmarshaller();
		JAXBElement<OsmChange> unmarshal = (JAXBElement<OsmChange>)unmarshaller.unmarshal(new StreamSource(in),
				OsmChange.class);
		OsmChange osmChange = unmarshal.getValue();
		assertEquals("0.6", osmChange.getVersion());
		assertEquals(3, osmChange.getCreate().size());
		assertEquals(3, osmChange.getDelete().size());
		assertEquals(3, osmChange.getModify().size());
	}
}
