/**
 * This file is part of Relation Analyzer for OSM.
 * Copyright (c) 2001 by Adrian Stabiszewski, as@grundid.de
 *
 * Relation Analyzer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Relation Analyzer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Relation Analyzer.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.osmtools.ra.segment;

import org.osmtools.ra.data.Node;

public class SegmentNodes {

	private Node thisNode;
	private Node otherNode;

	public SegmentNodes(Node thisNode, Node otherNode) {
		this.thisNode = thisNode;
		this.otherNode = otherNode;
	}

	public Node getThisNode() {
		return thisNode;
	}

	public Node getOtherNode() {
		return otherNode;
	}
}
