package org.osmsurround.test;

import org.osmsurround.api.OsmOperations;
import org.osmsurround.api.OsmTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("org.osmsurround")
public class TestConfiguration {

	@Bean
	@Qualifier("osm")
	public OsmOperations getOsmOperations() {
		return new OsmTemplate("http://api.openstreetmap.org", "Jjcvcttqs6vZVr98hQooEHdPWenNZF2Yv03m8ClE",
				"Uqzg4Q2ffEiM1zew87g9V0vPYcGkE9K5PdlRodNk");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
