package org.osmtools.pbf.data;

import java.util.Collection;
import java.util.Date;

public class CommonData {

	private long id;
	private int version;
	private long changesetId;
	private Date timestamp;
	private String user;
	private Collection<Tag> tags;

	public CommonData(long id, int version, long changesetId, Date timestamp, String user, Collection<Tag> tags) {
		this.id = id;
		this.version = version;
		this.changesetId = changesetId;
		this.timestamp = timestamp;
		this.user = user;
		this.tags = tags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getChangesetId() {
		return changesetId;
	}

	public void setChangesetId(long changesetId) {
		this.changesetId = changesetId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

}
