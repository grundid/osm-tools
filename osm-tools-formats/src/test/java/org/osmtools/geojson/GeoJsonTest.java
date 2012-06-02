package org.osmtools.geojson;

import static org.junit.Assert.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.osmtools.geojson.Feature;
import org.osmtools.geojson.FeatureCollection;
import org.osmtools.geojson.LineString;

public class GeoJsonTest {

	@Test
	public void testEncode() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		FeatureCollection geoJson = new FeatureCollection();

		Feature feature = new Feature();

		LineString lineString = new LineString();
		lineString.addCoordinates(1.0, 2.0);
		lineString.addCoordinates(2.0, 3.0);
		lineString.addCoordinates(4.0, 5.0);

		feature.setGeometry(lineString);
		feature.setProperty("name", "line");

		geoJson.addFeature(feature);

		String result = objectMapper.writeValueAsString(geoJson);
		assertEquals("{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":"
				+ "{\"type\":\"LineString\",\"coordinates\":[[1.0,2.0],[2.0,3.0],[4.0,5.0]]},"
				+ "\"properties\":{\"name\":\"line\"}}]}", result);
	}
}
