package org.osmsurround.api;

import java.math.BigInteger;

import org.osm.schema.Osm;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmRelation;
import org.osm.schema.OsmWay;
import org.osmsurround.oauth.OauthCredentials;

public class ChangesetTemplate extends OsmApiBinding implements ChangesetOperations {

	private OsmSchemaService osmSchemaService;

	private long changesetId;
	private long created = System.currentTimeMillis();

	public ChangesetTemplate(OsmSchemaService osmSchemaService, String osmApiBaseUrl, Osm osmWithComment,
			OauthCredentials oauthCredentials) {
		super(osmApiBaseUrl, oauthCredentials);
		this.osmSchemaService = osmSchemaService;
		openChangeset(osmWithComment);
	}

	private void openChangeset(Osm osmWithComment) {
		String url = osmApiBaseUrl + "/api/0.6/changeset/create";
		changesetId = putForId(url, osmWithComment);
	}

	@Override
	public long getChangesetId() {
		return changesetId;
	}

	@Override
	public long getCreated() {
		return created;
	}

	@Override
	public void closeChangeset() {
		String url = osmApiBaseUrl + "/api/0.6/changeset/" + changesetId + "/close";
		getRestTemplate().put(url, null);
	}

	@Override
	public long putWay(OsmWay osmWay) {
		Osm osm = osmSchemaService.createOsmNode();
		osmWay.setChangeset(BigInteger.valueOf(changesetId));
		osm.getWay().add(osmWay);
		String url = osmApiBaseUrl + "/api/0.6/way/" + osmWay.getId().longValue();
		return putForId(url, osm);
	}

	@Override
	public long putNode(OsmNode osmNode) {
		Osm osm = osmSchemaService.createOsmNode();
		osmNode.setChangeset(BigInteger.valueOf(changesetId));
		String url = osmApiBaseUrl + "/api/0.6/node/" + osmNode.getId().longValue();
		osm.getNode().add(osmNode);
		return putForId(url, osm);
	}

	@Override
	public long putRelation(OsmRelation relation) {
		String url = osmApiBaseUrl + "/api/0.6/relation/" + relation.getId().longValue();

		Osm osm = osmSchemaService.createOsmNode();
		relation.setChangeset(BigInteger.valueOf(changesetId));
		osm.getRelation().add(relation);
		return putForId(url, osm);
	}

	@Override
	public long putNewWay(OsmWay osmWay) {
		String url = osmApiBaseUrl + "/api/0.6/way/create";

		Osm osm = osmSchemaService.createOsmNode();
		osmWay.setChangeset(BigInteger.valueOf(changesetId));
		osm.getWay().add(osmWay);
		return putForId(url, osm);
	}

	@Override
	public long putNewNode(OsmNode osmNode) {
		String url = osmApiBaseUrl + "/api/0.6/node/create";
		
		Osm osm = osmSchemaService.createOsmNode();
		osmNode.setChangeset(BigInteger.valueOf(changesetId));
		osm.getNode().add(osmNode);
		return putForId(url, osm);
	}

	@Override
	public long putNewRelation(OsmRelation osmRelation) {
		String url = osmApiBaseUrl + "/api/0.6/relation/create";

		Osm osm = osmSchemaService.createOsmNode();
		osmRelation.setChangeset(BigInteger.valueOf(changesetId));
		osm.getRelation().add(osmRelation);
		return putForId(url, osm);
	}

	@Override
	public void deleteNode(long nodeId) {
		String url = osmApiBaseUrl + "/api/0.6/node/" + nodeId;

		deleteForId(url);
	}

	@Override
	public void deleteRelation(long relationId) {
		String url = osmApiBaseUrl + "/api/0.6/relation/" + relationId;

		deleteForId(url);
	}

	@Override
	public void deleteWay(long wayId) {
		String url = osmApiBaseUrl + "/api/0.6/way/" + wayId;

		deleteForId(url);
	}
}