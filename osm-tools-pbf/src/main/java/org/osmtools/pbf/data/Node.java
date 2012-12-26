package org.osmtools.pbf.data;

import java.util.Collection;
import java.util.Date;

public class Node extends CommonData {

	private double lat;
	private double lon;

	public Node(long id, int version, long changesetId, Date timestamp, String user, Collection<Tag> tags, double lat,
			double lon) {
		super(id, version, changesetId, timestamp, user, tags);
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

}
