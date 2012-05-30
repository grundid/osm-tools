package org.osmsurround.api;

import java.math.BigInteger;

import org.osm.schema.Osm;
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
}
