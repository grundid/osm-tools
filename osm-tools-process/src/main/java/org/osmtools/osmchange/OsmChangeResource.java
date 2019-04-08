package org.osmtools.osmchange;

import org.osmtools.osc.OsmChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OsmChangeResource {

	private static final String STATE_URL = "https://planet.openstreetmap.org/replication/{granularity}/state.txt";
	private RestOperations restOperations;

	@Autowired
	public OsmChangeResource(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	public int getOsmState(Granularity granularity) {
		String result = restOperations.getForObject(STATE_URL, String.class, granularity.name());
		Pattern pattern = Pattern.compile(".*sequenceNumber=(\\d*).*", Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(result);
		if (matcher.matches()) {
			String state = matcher.group(1);
			return Integer.parseInt(state);
		}
		throw new RuntimeException("Cannot find sequenceNumber in [" + result + "]");
	}

	public OsmChange getOsmChange(String url) {
		ResponseEntity<OsmChange> entity = restOperations.getForEntity(url, OsmChange.class);
		return entity.getBody();
	}
}
