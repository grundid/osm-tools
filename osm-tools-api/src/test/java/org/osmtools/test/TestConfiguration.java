package org.osmtools.test;

import org.osm.schema.Osm;
import org.osmtools.api.ChangesetOperations;
import org.osmtools.api.ChangesetTemplate;
import org.osmtools.api.OsmOperations;
import org.osmtools.api.OsmSchemaService;
import org.osmtools.api.OsmTemplate;
import org.osmtools.oauth.OauthCredentials;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("org.osmtools")
public class TestConfiguration {

	private String osmApiBaseUrl = "http://api06.dev.openstreetmap.org";
	private String oAuthConsumerKey = "poZCNRqdhCGVRVryki1muJ5FTa4Gny0jJEmFtRsu";
	private String oAuthConsumerSecret = "aHMuIX8DA6BH1HOdIsQgdjYyOvnIWX3aN3qLLRiH";

	@Bean
	@Qualifier("osm")
	public OsmOperations getOsmOperations() {
		return new OsmTemplate(osmApiBaseUrl, "", "");
	}
	
	@Bean
	@Qualifier("changeset")
	public ChangesetOperations getChangesetOperations() {	
		OsmSchemaService osmSchemaService = getOsmSchemaService();

		Osm osm = osmSchemaService.createChangeset("test-changeset");

		return new ChangesetTemplate(osmSchemaService, osmApiBaseUrl, osm , getOAuthCredentials());
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	private OauthCredentials getOAuthCredentials() {
		return new OauthCredentials(oAuthConsumerKey, oAuthConsumerSecret);
	}
	
	private OsmSchemaService getOsmSchemaService() {
		return new OsmSchemaService("osm-tool-test");
	}
}