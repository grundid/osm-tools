package org.osmtools.utils;

import java.util.List;

public class Centroid {

	private List<GeoPoint> points;
	private int uniquePoints;

	public Centroid(List<GeoPoint> points) {
		this.points = points;
		this.uniquePoints = points.size() - 1;
	}

	public double area() {
		return Math.abs(signedArea());
	}

	public double signedArea() {
		double sum = 0.0;
		for (int i = 0; i < uniquePoints; i++) {
			sum = sum + (points.get(i).x * points.get(i + 1).y) - (points.get(i).y * points.get(i + 1).x);
		}
		return 0.5 * sum;
	}

	public GeoPoint centroid() {
		double cx = 0.0, cy = 0.0;
		for (int i = 0; i < uniquePoints; i++) {
			cx = cx + (points.get(i).x + points.get(i + 1).x)
					* (points.get(i).y * points.get(i + 1).x - points.get(i).x * points.get(i + 1).y);
			cy = cy + (points.get(i).y + points.get(i + 1).y)
					* (points.get(i).y * points.get(i + 1).x - points.get(i).x * points.get(i + 1).y);
		}
		cx /= (6 * area());
		cy /= (6 * area());
		return new GeoPoint(Math.abs(cx), Math.abs(cy));
	}
}
