package org.osmtools.dataimport;

import java.util.HashMap;
import java.util.Map;

public class OsmObject {

	protected long id;
	protected Map<String, String> tags = new HashMap<String, String>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public String getTagValue(String tagName) {
		return tags.get(tagName);
	}
}
