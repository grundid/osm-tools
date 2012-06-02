package org.osmtools.oauth;

public class OauthCredentials {

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;

	public OauthCredentials(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	public OauthCredentials newInstance(String accessToken, String accessTokenSecret) {
		OauthCredentials oauthCredentials = new OauthCredentials(consumerKey, consumerSecret);
		oauthCredentials.accessToken = accessToken;
		oauthCredentials.accessTokenSecret = accessTokenSecret;
		return oauthCredentials;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

}
