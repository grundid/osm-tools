package org.osmtools.pbf.data;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Way extends CommonData {

	private List<Long> nodes;

	public Way(long id, int version, long changesetId, Date timestamp, String user, Collection<Tag> tags,
			List<Long> nodes) {
		super(id, version, changesetId, timestamp, user, tags);
		this.nodes = nodes;
	}

	public List<Long> getNodes() {
		return nodes;
	}

	public void setNodes(List<Long> nodes) {
		this.nodes = nodes;
	}

}
