package org.osmsurround.api;

import org.osm.schema.OsmRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class OsmTemplate implements OsmOperations {

	@Autowired
	private RestOperations restOperations;
	@Value("${osmApiBaseUrl}")
	private String osmApiBaseUrl;

	@Override
	public OsmRoot getBBox(BoundingBox boundingBox) {
		String url = osmApiBaseUrl + "/api/0.6/map?bbox=" + boundingBox.getWest() + "," + boundingBox.getSouth() + ","
				+ boundingBox.getEast() + "," + boundingBox.getNorth();
		return restOperations.getForObject(url, OsmRoot.class);
	}

	@Override
	public OsmChangeset openChangeset(String comment) {
		return null;
	}

}
