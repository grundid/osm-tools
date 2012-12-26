package org.osmtools.pbf.data;

public class RelationMember {

	private long id;
	private String role;
	private MemberType type;

	public RelationMember(long id, String role, MemberType type) {
		this.id = id;
		this.role = role;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public MemberType getType() {
		return type;
	}

}
