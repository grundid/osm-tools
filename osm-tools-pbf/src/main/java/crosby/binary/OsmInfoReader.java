package crosby.binary;

import java.util.Date;

import crosby.binary.Osmformat.Info;

public class OsmInfoReader {

	private Osmformat.Info info;
	private int dateGranularity;

	static final Date NODATE = new Date(-1);

	/** The magic number used to indicate no version number metadata for this entity. */
	static final int NOVERSION = -1;
	/** The magic number used to indicate no changeset metadata for this entity. */
	static final int NOCHANGESET = -1;

	public OsmInfoReader() {
	}

	public OsmInfoReader(Info info, int dateGranularity) {
		this.info = info;
		this.dateGranularity = dateGranularity;
	}

	public boolean hasValidUserId() {
		return (info != null && info.hasUid() && info.hasUserSid() && info.getUid() >= 0);
	}

	public int getUserSid() {
		return info.getUserSid();
	}

	/** Take a Info protocol buffer containing a date and convert it into a java Date object */
	public Date getDate() {
		if (info != null && info.hasTimestamp()) {
			return new Date(dateGranularity * (long)info.getTimestamp());
		}
		else
			return NODATE;
	}

	public int getVersion() {
		if (info != null)
			return info.getVersion();
		else
			return NOVERSION;
	}

	public long getChangeset() {
		if (info != null)
			return info.getChangeset();
		else
			return NOCHANGESET;

	}

}
