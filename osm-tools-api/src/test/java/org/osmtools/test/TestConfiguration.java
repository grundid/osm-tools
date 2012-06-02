package org.osmtools.test;

import org.osmtools.api.ChangesetOperations;
import org.osmtools.api.OsmOperations;
import org.osmtools.api.OsmTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("org.osmtools")
public class TestConfiguration {

	@Bean
	@Qualifier("osm")
	public OsmOperations getOsmOperations() {
		return new OsmTemplate("http://api.openstreetmap.org", "", "");
	}
	
	@Bean
	@Qualifier
	public ChangesetOperations getChangesetOperations() {
		return null;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
