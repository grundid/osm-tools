package org.osmtools.pbf.data;

import java.util.Collection;
import java.util.Date;

public class Relation extends CommonData {

	private Collection<RelationMember> members;

	public Relation(long id, int version, long changesetId, Date timestamp, String user, Collection<Tag> tags,
			Collection<RelationMember> members) {
		super(id, version, changesetId, timestamp, user, tags);
		this.members = members;
	}

	public Collection<RelationMember> getMembers() {
		return members;
	}

	public void setMembers(Collection<RelationMember> members) {
		this.members = members;
	}

}
