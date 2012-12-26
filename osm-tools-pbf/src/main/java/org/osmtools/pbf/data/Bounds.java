package org.osmtools.pbf.data;

public class Bounds {

	private double right;
	private double left;
	private double top;
	private double bottom;
	private String origin;

	public Bounds(double right, double left, double top, double bottom, String origin) {
		this.right = right;
		this.left = left;
		this.top = top;
		this.bottom = bottom;
		this.origin = origin;
	}

	public double getRight() {
		return right;
	}

	public double getLeft() {
		return left;
	}

	public double getTop() {
		return top;
	}

	public double getBottom() {
		return bottom;
	}

	public String getOrigin() {
		return origin;
	}

}
