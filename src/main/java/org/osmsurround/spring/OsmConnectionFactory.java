package org.osmsurround.spring;

import org.osmsurround.api.OsmOperations;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.oauth1.OAuth1ServiceProvider;

public class OsmConnectionFactory extends OAuth1ConnectionFactory<OsmOperations> {

	public OsmConnectionFactory(String osmApiBaseUrl, String consumerKey, String consumerSecret) {
		super("osm", new OsmServiceProvider(osmApiBaseUrl, consumerKey, consumerSecret), new OsmAdapter());
	}

	public OsmOperations getOsmOperations(String accessToken, String secret) {
		return ((OAuth1ServiceProvider<OsmOperations>)getServiceProvider()).getApi(accessToken, secret);
	}

}
