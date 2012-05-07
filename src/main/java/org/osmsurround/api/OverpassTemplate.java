package org.osmsurround.api;

import org.osm.schema.OsmRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class OverpassTemplate implements OsmOperations {

	@Autowired
	private RestOperations restOperations;

	@Override
	public OsmRoot getBBox(BoundingBox boundingBox) {
		String data = "(way[highway=track](" + boundingBox.getSouth() + "," + boundingBox.getWest() + ","
				+ boundingBox.getNorth() + "," + boundingBox.getEast() + ");node(w)->.x;);out meta;";

		return restOperations.getForObject("http://overpass-api.de/api/interpreter?data={data}", OsmRoot.class, data);
	}

	@Override
	public OsmChangeset openChangeset(String comment) {
		return null;
	}

}
