package org.osmsurround.api;

import org.osm.schema.OsmRoot;

public interface OsmOperations {

	OsmRoot getBBox(BoundingBox boundingBox);

	OsmChangeset openChangeset(String comment);
}
