package org.osmtools.api;

import java.util.Collection;
import java.util.List;

import org.osm.schema.Osm;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmRelation;
import org.osm.schema.OsmWay;
import org.osmtools.oauth.OauthTokens;

public interface OsmOperations {

	/**
	 * The following command returns:
	 * <ul>
	 * <li>All nodes that are inside a given bounding box and any relations that reference them.</li>
	 * <li>All ways that reference at least one node that is inside a given bounding box, any relations that reference
	 * them [the ways], and any nodes outside the bounding box that the ways may reference.</li>
	 * <li>All relations that reference one of the nodes, ways or relations included due to the above rules. (Does not
	 * apply recursively, see explanation below.)</li>
	 * </ul>
	 * 
	 * @see <a
	 *      href="http://wiki.openstreetmap.org/wiki/Api06#Retrieving_map_data_by_bounding_box:_GET_.2Fapi.2F0.6.2Fmap">OSM
	 *      API Wiki</a>
	 * 
	 * @param boundingBox
	 * @return
	 */
	Osm getBBox(BoundingBox boundingBox);

	OsmNode getForNode(long nodeId);

	OsmWay getForWay(long wayId);
	
	List<OsmRelation> getForRelation(long relationId);

	List<OsmWay> getForManyWays(Collection<Long> wayIds);

	ChangesetOperations openChangeset(String comment, OauthTokens oauthTokens);

	UserOperations userOperations(OauthTokens oauthTokens);

}
