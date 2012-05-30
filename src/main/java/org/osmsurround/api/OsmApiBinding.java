package org.osmsurround.api;

import java.util.ArrayList;
import java.util.List;

import org.osm.schema.Osm;
import org.osmsurround.oauth.OauthCredentials;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public abstract class OsmApiBinding extends AbstractOAuth1ApiBinding {

	protected String osmApiBaseUrl;

	public OsmApiBinding(String osmApiBaseUrl, OauthCredentials oauthCredentials) {
		super(oauthCredentials.getConsumerKey(), oauthCredentials.getConsumerSecret(), oauthCredentials
				.getAccessToken(), oauthCredentials.getAccessTokenSecret());
		this.osmApiBaseUrl = osmApiBaseUrl;
	}

	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		return messageConverters;
	}

	protected long putForId(String url, Osm osm) {
		HttpEntity<Osm> entity = new HttpEntity<Osm>(osm);

		ResponseEntity<String> response = getRestTemplate().exchange(url, HttpMethod.PUT, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return Long.parseLong(response.getBody());
		}
		else {
			throw new OsmApiException(response.getStatusCode().getReasonPhrase());
		}
	}

}
