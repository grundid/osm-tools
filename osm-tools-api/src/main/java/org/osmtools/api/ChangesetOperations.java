package org.osmtools.api;

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

	void closeChangeset();
}
