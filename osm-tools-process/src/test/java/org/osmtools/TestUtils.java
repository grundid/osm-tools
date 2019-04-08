package org.osmtools;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.social.test.client.RequestMatchers;
import org.springframework.social.test.client.ResponseCreators;
import org.springframework.web.client.RestTemplate;

public class TestUtils {

	public static MockRestServiceServer prepareRestTemplateForState(RestTemplate restTemplate, String granularity) {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_XML);
		mockServer
				.expect(RequestMatchers.requestTo("https://planet.openstreetmap.org/replication/" + granularity
						+ "/state.txt"))
				.andExpect(RequestMatchers.method(HttpMethod.GET))
				.andRespond(
						ResponseCreators.withResponse("#Fri Sep 13 20:02:08 UTC 2013\r\n" + "sequenceNumber=8797\r\n"
								+ "timestamp=2013-09-13T20\\:00\\:00Z", responseHeaders));
		return mockServer;
	}

	public static MockRestServiceServer prepareRestTemplateForOsmChange(RestTemplate restTemplate, String url,
			String oscFile) {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_XML);
		mockServer
				.expect(RequestMatchers.requestTo(url))
				.andExpect(RequestMatchers.method(HttpMethod.GET))
				.andRespond(
						ResponseCreators.withResponse(
								new ClassPathResource(oscFile, ClassLoader.getSystemClassLoader()), responseHeaders));
		return mockServer;
	}
}
