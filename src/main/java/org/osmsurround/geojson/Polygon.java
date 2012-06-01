package org.osmsurround.geojson;

import java.util.ArrayList;
import java.util.Collection;

public class Polygon extends Geometry {

	public Polygon() {
		super(Polygon.class.getSimpleName());
		startNewPolygon();
	}

	private Collection<Collection<float[]>> coordinates = new ArrayList<Collection<float[]>>();
	private transient Collection<float[]> subPolygon;

	public void startNewPolygon() {
		subPolygon = new ArrayList<float[]>();
		coordinates.add(subPolygon);
	}

	public void addCoordinates(float lon, float lat) {
		subPolygon.add(new float[] { lon, lat });
	}

	public void addCoordinates(double lon, double lat) {
		subPolygon.add(new float[] { (float)lon, (float)lat });
	}

	public Collection<Collection<float[]>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Collection<Collection<float[]>> coordinates) {
		this.coordinates = coordinates;
	}
}
