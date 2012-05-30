package org.osmsurround.api;

import java.util.Collection;
import java.util.List;

import org.osm.schema.Osm;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmWay;
import org.osmsurround.oauth.OauthTokens;
import org.springframework.web.client.RestOperations;

public class OverpassTemplate implements OsmOperations {

	private RestOperations restOperations;

	public OverpassTemplate(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	@Override
	public Osm getBBox(BoundingBox boundingBox) {
		String data = "(way[highway=track](" + boundingBox.getSouth() + "," + boundingBox.getWest() + ","
				+ boundingBox.getNorth() + "," + boundingBox.getEast() + ");node(w)->.x;);out meta;";

		return restOperations.getForObject("http://overpass-api.de/api/interpreter?data={data}", Osm.class, data);
	}

	@Override
	public ChangesetOperations openChangeset(String comment, OauthTokens oauthTokens) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OsmNode getForNode(long nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OsmWay getForWay(long wayId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserOperations userOperations(OauthTokens oauthTokens) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OsmWay> getForManyWays(Collection<Long> wayIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
