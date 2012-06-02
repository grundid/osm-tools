package org.osmtools.data;

import java.util.ArrayList;
import java.util.List;

import org.osmtools.api.LonLat;
import org.osmtools.api.Section;

public class SimpleSection implements Section {

	private String name;
	private List<Node> nodes = new ArrayList<Node>();

	public SimpleSection(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	@Override
	public List<Iterable<? extends LonLat>> getCoordinateLists() {
		List<Iterable<? extends LonLat>> nodeList = new ArrayList<Iterable<? extends LonLat>>();
		nodeList.add(nodes);
		return nodeList;
	}
}
