package org.osmtools.geojson;

public class Point extends Geometry {

	private float[] coordinates;

	public Point(double lon, double lat) {
		super(Point.class.getSimpleName());
		this.coordinates = new float[] { (float)lon, (float)lat };
	}

	public Point(float lon, float lat) {
		super(Point.class.getSimpleName());
		this.coordinates = new float[] { lon, lat };
	}

	public float[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(float[] coordinates) {
		this.coordinates = coordinates;
	}

}
