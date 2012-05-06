package org.osmsurround.geojson;

public abstract class Geometry {

	private String type;

	public Geometry(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
