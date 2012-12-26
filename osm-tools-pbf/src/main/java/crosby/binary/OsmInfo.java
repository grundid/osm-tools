package crosby.binary;

import java.util.Date;

public class OsmInfo {

	private int version;
	private long changesetId;
	private String username;
	private Date timestamp;

	public OsmInfo(int version, long changesetId, String username, Date timestamp) {
		this.version = version;
		this.changesetId = changesetId;
		this.username = username;
		this.timestamp = timestamp;
	}

	public int getVersion() {
		return version;
	}

	public long getChangesetId() {
		return changesetId;
	}

	public String getUsername() {
		return username;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
