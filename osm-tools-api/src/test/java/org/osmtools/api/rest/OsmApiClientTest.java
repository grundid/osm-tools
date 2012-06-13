package org.osmtools.api.rest;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.rs.security.oauth2.client.OAuthClientUtils.Consumer;
import org.junit.Before;
import org.junit.Test;
import org.osm.schema.Osm;
import org.osm.schema.OsmNode;

public class OsmApiClientTest {
	
	private OsmApi osmApi;
	private String baseAdress = "http://api06.dev.openstreetmap.org";
	
	@Before
	public void setUp() {
	   osmApi = JAXRSClientFactory.create(baseAdress, OsmApi.class);
	}

	@Test
	public void testPutNode() {
		osmApi.getNode(100);
	}	

	private Osm getNode() {
		Osm osm = new Osm();

		OsmNode osmNode = new OsmNode();
		osm.getNode().add(osmNode);

		return osm;
	}
	
	private Consumer getConsumer() {
		String key = "45Fs0q6hhckynSQzNz9nFY2jg1I1L7PovqFf0kp3";
		String secretKey = "zVKM7AxhG6eUT9k4GrfSHGX6GIzdZf5nKNmHJ48o";

		return new Consumer(key, secretKey);
	}
}