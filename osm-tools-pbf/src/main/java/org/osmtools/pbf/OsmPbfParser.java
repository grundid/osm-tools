package org.osmtools.pbf;

import java.util.List;

import org.osmtools.pbf.data.Bounds;
import org.osmtools.pbf.data.Node;

import crosby.binary.BinaryParser;
import crosby.binary.OsmObjectFactory;
import crosby.binary.Osmformat;

public class OsmPbfParser extends BinaryParser {

	private OsmProcessor sink;

	public OsmPbfParser(OsmProcessor sink) {
		this.sink = sink;
	}

	@Override
	public void complete() {
	}

	@Override
	protected void parseNodes(List<Osmformat.Node> nodes, OsmObjectFactory blockContext) {
		for (Osmformat.Node pbfNode : nodes) {
			sink.process(blockContext.createNode(pbfNode));
		}
	}

	@Override
	protected void parseWays(List<Osmformat.Way> ways, OsmObjectFactory blockContext) {
		for (Osmformat.Way way : ways) {
			sink.process(blockContext.createWay(way));
		}
	}

	@Override
	public void parse(Osmformat.HeaderBlock block) {
		for (String s : block.getRequiredFeaturesList()) {
			if (s.equals("OsmSchema-V0.6")) {
				continue; // We can parse this.
			}
			if (s.equals("DenseNodes")) {
				continue; // We can parse this.
			}
			throw new RuntimeException("File requires unknown feature: " + s);
		}

		if (block.hasBbox()) {
			String source = "myVersion";
			if (block.hasSource()) {
				source = block.getSource();
			}

			double multiplier = .000000001;
			double rightf = block.getBbox().getRight() * multiplier;
			double leftf = block.getBbox().getLeft() * multiplier;
			double topf = block.getBbox().getTop() * multiplier;
			double bottomf = block.getBbox().getBottom() * multiplier;

			sink.process(new Bounds(rightf, leftf, topf, bottomf, source));
		}
	}

	@Override
	protected void parseDense(Osmformat.DenseNodes nodes, OsmObjectFactory blockContext) {
		for (Node node : blockContext.createNodes(nodes)) {
			sink.process(node);
		}
	}

	@Override
	protected void parseRelations(List<Osmformat.Relation> rels, OsmObjectFactory objectFactory) {
		for (Osmformat.Relation relation : rels) {
			sink.process(objectFactory.createRelation(relation));
		}
	}
}
