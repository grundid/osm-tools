package org.osmsurround.api;

import java.util.Collection;
import java.util.List;

import org.osm.schema.Osm;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmRelation;
import org.osm.schema.OsmWay;
import org.osmsurround.oauth.OauthCredentials;
import org.osmsurround.oauth.OauthTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

public class OsmTemplate implements OsmOperations {

	@Autowired
	private OsmSchemaService osmSchemaService;
	@Autowired
	private RestTemplate restTemplate;

	private String osmApiBaseUrl;
	private OauthCredentials oauthCredentials;

	public OsmTemplate(String osmApiBaseUrl) {
		this.osmApiBaseUrl = osmApiBaseUrl;
	}

	public OsmTemplate(String osmApiBaseUrl, String consumerKey, String consumerSecret) {
		this.osmApiBaseUrl = osmApiBaseUrl;
		oauthCredentials = new OauthCredentials(consumerKey, consumerSecret);
	}

	@Override
	public Osm getBBox(BoundingBox boundingBox) {
		String url = osmApiBaseUrl + "/api/0.6/map?bbox=" + boundingBox.getWest() + "," + boundingBox.getSouth() + ","
				+ boundingBox.getEast() + "," + boundingBox.getNorth();
		return restTemplate.getForObject(url, Osm.class);
	}

	@Override
	public ChangesetOperations openChangeset(String comment, OauthTokens oauthTokens) {
		Osm osm = osmSchemaService.createChangeset(comment);
		return new ChangesetTemplate(osmSchemaService, osmApiBaseUrl, osm, oauthCredentials.newInstance(
				oauthTokens.getToken(), oauthTokens.getTokenSecret()));
	}

	@Override
	public OsmNode getForNode(long nodeId) {
		String url = osmApiBaseUrl + "/api/0.6/node/" + nodeId;
		Osm osm = restTemplate.getForObject(url, Osm.class);
		return osm.getNode().get(0);
	}

	@Override
	public OsmWay getForWay(long wayId) {
		String url = osmApiBaseUrl + "/api/0.6/way/" + wayId;
		Osm osm = restTemplate.getForObject(url, Osm.class);
		return osm.getWay().get(0);
	}

	@Override
	public List<OsmWay> getForManyWays(Collection<Long> wayIds) {
		String url = osmApiBaseUrl + "/api/0.6/ways?ways=" + StringUtils.collectionToCommaDelimitedString(wayIds);
		Osm osm = restTemplate.getForObject(url, Osm.class);
		return osm.getWay();
	}
	
	@Override
	public List<OsmRelation> getForRelation(long relationId) {
		String url = osmApiBaseUrl + "/api/0.6/relation/" + relationId;
		Osm osm = restTemplate.getForObject(url, Osm.class);
		return osm.getRelation();
	}

	@Override
	public UserOperations userOperations(OauthTokens oauthTokens) {
		return new UserTemplate(osmApiBaseUrl, oauthCredentials.newInstance(oauthTokens.getToken(),
				oauthTokens.getTokenSecret()));
	}
}