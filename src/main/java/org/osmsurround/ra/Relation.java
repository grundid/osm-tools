package org.osmsurround.ra;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

public class Relation implements Serializable {

	private long relationId;
	private Calendar timestamp;
	private String user;
	private Map<String, String> tags;

	public long getRelationId() {
		return relationId;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public String getUser() {
		return user;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setRelationId(long relationId) {
		this.relationId = relationId;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

}
