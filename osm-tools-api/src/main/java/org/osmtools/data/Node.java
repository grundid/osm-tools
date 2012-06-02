package org.osmtools.data;

import org.osmtools.api.LonLat;

public class Node implements LonLat {

	private float lon;
	private float lat;

	public Node(float lon, float lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	@Override
	public float getLon() {
		return lon;
	}

	@Override
	public float getLat() {
		return lat;
	}
}
