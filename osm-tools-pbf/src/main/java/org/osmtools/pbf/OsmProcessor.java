package org.osmtools.pbf;

import org.osmtools.pbf.data.Bounds;
import org.osmtools.pbf.data.Node;
import org.osmtools.pbf.data.Relation;
import org.osmtools.pbf.data.Way;

public interface OsmProcessor {

	void process(Node mode);

	void process(Way way);

	void process(Relation relation);

	void process(Bounds bounds);
}
