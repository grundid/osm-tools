package org.osmtools.osmchange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osmtools.osc.OsmChange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Updater {

	private static final String HOUR_STATE_URL = "http://planet.openstreetmap.org/replication/{granularity}/state.txt";
	private RestTemplate restTemplate;

	public Updater(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public int getCurrentState() {
		String result = restTemplate.getForObject(HOUR_STATE_URL, String.class, "hour");
		Pattern pattern = Pattern.compile(".*sequenceNumber=(\\d*).*", Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(result);
		if (matcher.matches()) {
			String state = matcher.group(1);
			return Integer.parseInt(state);
		}
		throw new RuntimeException("Cannot find sequenceNumber in [" + result + "]");
	}

	public OsmChange getOsmChange(String url) {
		ResponseEntity<OsmChange> entity = restTemplate.getForEntity(url, OsmChange.class);
		return entity.getBody();
	}
}
