package org.osmtools.pbf;

import org.osmtools.pbf.data.Bounds;
import org.osmtools.pbf.data.Node;
import org.osmtools.pbf.data.Relation;
import org.osmtools.pbf.data.Way;

public class DummyOsmProcessor implements OsmProcessor {

	private int nodes;
	private int ways;
	private int relations;
	private int bounds;

	@Override
	public void process(Node mode) {
		nodes++;
	}

	@Override
	public void process(Way way) {
		ways++;
	}

	@Override
	public void process(Relation relation) {
		relations++;
	}

	@Override
	public void process(Bounds bounds) {
		this.bounds++;
	}

	public int getNodes() {
		return nodes;
	}

	public int getWays() {
		return ways;
	}

	public int getRelations() {
		return relations;
	}

	public int getBounds() {
		return bounds;
	}

}
