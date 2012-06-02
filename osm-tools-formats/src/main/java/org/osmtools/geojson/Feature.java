package org.osmtools.geojson;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Feature extends Geometry {

	private String id;
	private Geometry geometry;
	private Map<String, Object> properties = new HashMap<String, Object>();

	public Feature() {
		super(Feature.class.getSimpleName());
	}

	public Feature(Geometry geometry) {
		this();
		this.geometry = geometry;
	}

	public Feature(String id, Geometry geometry) {
		this(geometry);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
}
