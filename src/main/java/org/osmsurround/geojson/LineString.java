package org.osmsurround.geojson;

import java.util.ArrayList;
import java.util.Collection;

public class LineString extends Geometry {

	private Collection<float[]> coordinates = new ArrayList<float[]>();

	public LineString() {
		super(LineString.class.getSimpleName());
	}

	public void addCoordinates(float lon, float lat) {
		coordinates.add(new float[] { lon, lat });
	}

	public void addCoordinates(double lon, double lat) {
		coordinates.add(new float[] { (float)lon, (float)lat });
	}

	public Collection<float[]> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Collection<float[]> coordinates) {
		this.coordinates = coordinates;
	}
}
