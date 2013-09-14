package org.osmtools.osmchange;

import static org.junit.Assert.*;

import org.junit.Test;
import org.osmtools.TestUtils;
import org.osmtools.osc.OsmChange;
import org.springframework.web.client.RestTemplate;

public class OsmChangeResourceTest {

	@Test
	public void itShouldReturnTheCurrentState() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		TestUtils.prepareRestTemplateForState(restTemplate, Granularity.hour.name());
		int state = new OsmChangeResource(restTemplate).getOsmState(Granularity.hour);
		assertEquals(8797, state);
	}

	@Test
	public void itShouldGetOsmChange() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://url";
		TestUtils.prepareRestTemplateForOsmChange(restTemplate, url, "test.osc");
		OsmChange osmChange = new OsmChangeResource(restTemplate).getOsmChange(url);
		assertNotNull(osmChange);
		assertEquals("0.6", osmChange.getVersion());
	}
}
