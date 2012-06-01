package org.osmsurround.overpass;

import org.osm.schema.Osm;
import org.osmsurround.api.BoundingBox;
import org.springframework.web.client.RestOperations;

public class OverpassTemplate implements OverpassOperations {

	private RestOperations restOperations;

	public OverpassTemplate(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	public Osm getBBox(BoundingBox boundingBox) {
		String data = "(way[highway=track](" + boundingBox.getSouth() + "," + boundingBox.getWest() + ","
				+ boundingBox.getNorth() + "," + boundingBox.getEast() + ");node(w)->.x;);out meta;";

		return restOperations.getForObject("http://overpass-api.de/api/interpreter?data={data}", Osm.class, data);
	}

	public Osm getBuildings(BoundingBox boundingBox) {
		String data = "(way[building=yes](" + boundingBox.getSouth() + "," + boundingBox.getWest() + ","
				+ boundingBox.getNorth() + "," + boundingBox.getEast() + ");node(w)->.x;);out meta;";

		System.out.println("Data: " + data);

		return restOperations.getForObject("http://overpass-api.de/api/interpreter?data={data}", Osm.class, data);
	}

}
