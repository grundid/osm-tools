package org.osmtools.geojson;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public abstract class Geometry {

	private String type;
	private float[] bbox;

	public Geometry(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setBoundingBox(float west, float south, float east, float north) {
		bbox = new float[] { west, south, east, north };
	}

	public void setBoundingBox(double west, double south, double east, double north) {
		bbox = new float[] { (float)west, (float)south, (float)east, (float)north };
	}

	public float[] getBbox() {
		return bbox;
	}

	public void setBbox(float[] bbox) {
		this.bbox = bbox;
	}

}
