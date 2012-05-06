package org.osmsurround.api;

import org.osm.schema.OsmWay;

public interface OsmChangeset {

	long getChangesetId();

	long updateWay(OsmWay osmWay);
}
