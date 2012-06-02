package org.osmsurround.api;

import org.osm.schema.OsmNode;
import org.osm.schema.OsmRelation;
import org.osm.schema.OsmWay;

public interface ChangesetOperations {

	long getChangesetId();

	long getCreated();

	/**
	 * Updates data from a preexisting element. A full representation of the element as it should be after the update
	 * has to be provided. So any tags that remain unchanged have to be in the update as well. A version number has to
	 * be provided as well.
	 * 
	 * @param osmWay
	 * @return the new version number
	 */
	long putWay(OsmWay osmWay);
	
	long putNode(OsmNode osmNode);
	
	long putRelation(OsmRelation osmRelation);
	
	long putNewWay(OsmWay osmWay);
	
	long putNewNode(OsmNode osmNode);
	
	long putNewRelation(OsmRelation osmRelation);
	
	void deleteNode(long nodeId);
	
	void deleteWay(long wayId);
	
	void deleteRelation(long deleteRelation);
	
	void closeChangeset();
}