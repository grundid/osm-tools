package org.osmtools.geojson;

import java.util.ArrayList;
import java.util.Collection;

public class MultiLineString extends Geometry {

	private Collection<Collection<float[]>> coordinates = new ArrayList<Collection<float[]>>();
	private transient Collection<float[]> lineString;

	public MultiLineString() {
		super(MultiLineString.class.getSimpleName());
	}

	public void startNewLine() {
		lineString = new ArrayList<float[]>();
		coordinates.add(lineString);
	}

	public void addCoordinates(float lon, float lat) {
		lineString.add(new float[] { lon, lat });
	}

	public void addCoordinates(double lon, double lat) {
		lineString.add(new float[] { (float)lon, (float)lat });
	}

	public Collection<Collection<float[]>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Collection<Collection<float[]>> coordinates) {
		this.coordinates = coordinates;
	}

}
