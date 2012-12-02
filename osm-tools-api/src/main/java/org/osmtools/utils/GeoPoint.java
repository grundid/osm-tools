package org.osmtools.utils;

import org.osm.schema.OsmNode;
import org.osmtools.api.LonLat;

public class GeoPoint {

	public double x;
	public double y;

	public GeoPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public GeoPoint(OsmNode osmNode) {
		this.x = osmNode.getLon();
		this.y = osmNode.getLat();
	}

	public GeoPoint(LonLat lonLat) {
		this.x = lonLat.getLon();
		this.y = lonLat.getLat();
	}

}
