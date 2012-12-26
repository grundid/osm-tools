package org.osmtools.pbf;

import org.osm.schema.OsmUser;

public interface OsmObjectFactory {

	OsmUser createOsmUser(Long userId, String userName);
}
