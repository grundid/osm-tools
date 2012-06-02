package org.osmtools.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.osm.schema.Osm;
import org.osm.schema.OsmUser;
import org.osmtools.oauth.OauthCredentials;

public class UserTemplate extends OsmApiBinding implements UserOperations {

	public UserTemplate(String osmApiBaseUrl, OauthCredentials oauthCredentials) {
		super(osmApiBaseUrl, oauthCredentials);
	}

	private URI getUri(String uri) {
		try {
			return new URI(uri);
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public OsmUser getForUser() {
		String url = osmApiBaseUrl + "/api/0.6/user/details";
		return getRestTemplate().getForObject(getUri(url), Osm.class).getUser();
	}

	@Override
	public Set<OsmPermission> getPermissions() {
		String url = osmApiBaseUrl + "/api/0.6/permissions";
		return null;
	}

}
