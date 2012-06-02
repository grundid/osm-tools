package org.osmtools.spring;

import org.osmtools.api.OsmOperations;
import org.osmtools.oauth.OauthCredentials;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;

public class OsmServiceProvider extends AbstractOAuth1ServiceProvider<OsmOperations> {

	private final static String requestTokenUrl = "http://www.openstreetmap.org/oauth/request_token";
	private final static String accessTokenUrl = "http://www.openstreetmap.org/oauth/access_token";
	private final static String authorizeUrl = "http://www.openstreetmap.org/oauth/authorize";

	private String osmApiBaseUrl;
	private OauthCredentials oauthCredentials;

	public OsmServiceProvider(String osmApiBaseUrl, String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret, requestTokenUrl,
				authorizeUrl, accessTokenUrl));
		this.osmApiBaseUrl = osmApiBaseUrl;
		oauthCredentials = new OauthCredentials(getConsumerKey(), getConsumerSecret());
	}

	@Override
	public OsmOperations getApi(String accessToken, String secret) {
		throw new UnsupportedOperationException();
	}

}
