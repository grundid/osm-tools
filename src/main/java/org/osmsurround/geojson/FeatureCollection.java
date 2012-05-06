package org.osmsurround.geojson;

import java.util.ArrayList;
import java.util.Collection;

public class FeatureCollection extends Geometry {

	private Collection<Feature> features = new ArrayList<Feature>();

	public FeatureCollection() {
		super(FeatureCollection.class.getSimpleName());
	}

	public void addFeature(Feature feature) {
		features.add(feature);
	}

	public Collection<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Collection<Feature> features) {
		this.features = features;
	}

}
