package crosby.binary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osmtools.pbf.data.MemberType;
import org.osmtools.pbf.data.Node;
import org.osmtools.pbf.data.Relation;
import org.osmtools.pbf.data.RelationMember;
import org.osmtools.pbf.data.Tag;
import org.osmtools.pbf.data.Way;

import crosby.binary.Osmformat.DenseInfo;

public class OsmObjectFactory {

	private int granularity;
	private long latOffset;
	private long lonOffset;
	private int dateGranularity;
	private String strings[];

	public OsmObjectFactory(int granularity, long latOffset, long lonOffset, int dateGranularity, String[] strings) {
		this.granularity = granularity;
		this.latOffset = latOffset;
		this.lonOffset = lonOffset;
		this.dateGranularity = dateGranularity;
		this.strings = strings;
	}

	/**
	 * Get a string based on the index used.
	 * 
	 * Index 0 is reserved to use as a delimiter, therefore, index 1 corresponds to the first string in the table
	 * 
	 * @param id
	 * @return
	 */
	private String getStringById(int id) {
		return strings[id];
	}

	/**
	 * Convert a latitude value stored in a protobuf into a double, compensating for granularity and latitude offset
	 */
	private double parseLat(long degree) {
		// Support non-zero offsets. (We don't currently generate them)
		return (granularity * degree + latOffset) * .000000001;
	}

	/**
	 * Convert a longitude value stored in a protobuf into a double, compensating for granularity and longitude offset
	 */
	private double parseLon(long degree) {
		// Support non-zero offsets. (We don't currently generate them)
		return (granularity * degree + lonOffset) * .000000001;
	}

	private OsmInfo createOsmInfo(boolean hasOsmInfo, Osmformat.Info info) {
		OsmInfoReader osmInfoReader = hasOsmInfo ? new OsmInfoReader(info, dateGranularity) : new OsmInfoReader();
		String userName = osmInfoReader.hasValidUserId() ? getStringById(osmInfoReader.getUserSid()) : "";
		return new OsmInfo(osmInfoReader.getVersion(), osmInfoReader.getChangeset(), userName, osmInfoReader.getDate());
	}

	public Node createNode(Osmformat.Node pbfNode) {
		OsmInfo osmInfo = createOsmInfo(pbfNode.hasInfo(), pbfNode.getInfo());

		List<Tag> tags = new ArrayList<Tag>();
		for (int j = 0; j < pbfNode.getKeysCount(); j++) {
			tags.add(new Tag(getStringById(pbfNode.getKeys(j)), getStringById(pbfNode.getVals(j))));
		}

		long id = pbfNode.getId();
		double latf = parseLat(pbfNode.getLat());
		double lonf = parseLon(pbfNode.getLon());

		return new Node(id, osmInfo.getVersion(), osmInfo.getChangesetId(), osmInfo.getTimestamp(),
				osmInfo.getUsername(), tags, latf, lonf);
	}

	public List<Node> createNodes(Osmformat.DenseNodes pbfNodes) {
		List<Node> nodes = new ArrayList<Node>();
		long lastId = 0, lastLat = 0, lastLon = 0;

		int j = 0; // Index into the keysvals array.

		// Stuff for dense info
		long lasttimestamp = 0, lastchangeset = 0;
		int lastuserSid = 0, lastuid = 0;
		DenseInfo di = null;
		if (pbfNodes.hasDenseinfo()) {
			di = pbfNodes.getDenseinfo();
		}
		for (int nodeCount = 0; nodeCount < pbfNodes.getIdCount(); nodeCount++) {
			List<Tag> tags = new ArrayList<Tag>(0);
			long lat = pbfNodes.getLat(nodeCount) + lastLat;
			lastLat = lat;
			long lon = pbfNodes.getLon(nodeCount) + lastLon;
			lastLon = lon;
			long id = pbfNodes.getId(nodeCount) + lastId;
			lastId = id;
			double latf = parseLat(lat), lonf = parseLon(lon);
			// If empty, assume that nothing here has keys or vals.
			if (pbfNodes.getKeysValsCount() > 0) {
				while (pbfNodes.getKeysVals(j) != 0) {
					int keyid = pbfNodes.getKeysVals(j++);
					int valid = pbfNodes.getKeysVals(j++);
					tags.add(new Tag(getStringById(keyid), getStringById(valid)));
				}
				j++; // Skip over the '0' delimiter.
			}
			// Handle dense info.
			if (di != null) {
				int uid = di.getUid(nodeCount) + lastuid;
				lastuid = uid;
				int userSid = di.getUserSid(nodeCount) + lastuserSid;
				lastuserSid = userSid;
				long timestamp = di.getTimestamp(nodeCount) + lasttimestamp;
				lasttimestamp = timestamp;
				int version = di.getVersion(nodeCount);
				long changeset = di.getChangeset(nodeCount) + lastchangeset;
				lastchangeset = changeset;

				Date date = new Date(dateGranularity * timestamp);

				String user;
				if (uid < 0) {
					user = "";
				}
				else {
					user = getStringById(userSid);
				}
				nodes.add(new Node(id, version, changeset, date, user, tags, latf, lonf));
			}
			else {
				nodes.add(new Node(id, -1, -1, new Date(-1), "", tags, latf, lonf));
			}
		}

		return nodes;
	}

	public Way createWay(Osmformat.Way pbfWay) {
		OsmInfo osmInfo = createOsmInfo(pbfWay.hasInfo(), pbfWay.getInfo());

		List<Tag> tags = new ArrayList<Tag>();
		for (int j = 0; j < pbfWay.getKeysCount(); j++) {
			tags.add(new Tag(getStringById(pbfWay.getKeys(j)), getStringById(pbfWay.getVals(j))));
		}

		long lastId = 0;
		List<Long> nodes = new ArrayList<Long>();
		for (long ref : pbfWay.getRefsList()) {
			long nodeId = ref + lastId;
			nodes.add(Long.valueOf(nodeId));
			lastId = nodeId;
		}

		long id = pbfWay.getId();

		return new Way(id, osmInfo.getVersion(), osmInfo.getChangesetId(), osmInfo.getTimestamp(),
				osmInfo.getUsername(), tags, nodes);
	}

	public Relation createRelation(Osmformat.Relation pbfRelation) {
		OsmInfo osmInfo = createOsmInfo(pbfRelation.hasInfo(), pbfRelation.getInfo());

		List<Tag> tags = new ArrayList<Tag>();
		for (int j = 0; j < pbfRelation.getKeysCount(); j++) {
			tags.add(new Tag(getStringById(pbfRelation.getKeys(j)), getStringById(pbfRelation.getVals(j))));
		}

		long id = pbfRelation.getId();
		List<RelationMember> members = convertMembers(pbfRelation);
		Relation relation = new Relation(id, osmInfo.getVersion(), osmInfo.getChangesetId(), osmInfo.getTimestamp(),
				osmInfo.getUsername(), tags, members);
		return relation;
	}

	private List<RelationMember> convertMembers(Osmformat.Relation pbfRelation) {
		long lastMid = 0;
		List<RelationMember> nodes = new ArrayList<RelationMember>();
		for (int j = 0; j < pbfRelation.getMemidsCount(); j++) {
			long mid = lastMid + pbfRelation.getMemids(j);
			lastMid = mid;
			String role = getStringById(pbfRelation.getRolesSid(j));
			MemberType memberType = decideMemberType(pbfRelation.getTypes(j));
			nodes.add(new RelationMember(mid, role, memberType));
		}
		return nodes;
	}

	private MemberType decideMemberType(Osmformat.Relation.MemberType memberType) {
		MemberType etype = null;

		if (memberType == Osmformat.Relation.MemberType.NODE) {
			etype = MemberType.NODE;
		}
		else if (memberType == Osmformat.Relation.MemberType.WAY) {
			etype = MemberType.WAY;
		}
		else if (memberType == Osmformat.Relation.MemberType.RELATION) {
			etype = MemberType.RELATION;
		}
		else {
			assert false; // TODO; Illegal file?
		}
		return etype;
	}

}
