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

import java.util.Collection;
import java.util.Set;

import org.osmtools.ra.analyzer.ConnectableNode;
import org.osmtools.ra.data.Node;

public interface ConnectableSegment {

	SegmentNodes getSegmentNodes();

	ConnectableNode getEndpointNodes();

	Set<Node> getCommonNode(ConnectableSegment otherSegment);

	/**
	 * Returns true if the given node can connect to this segment's start or end nodes.
	 * 
	 */
	boolean canConnect(ConnectableSegment otherSegment);

	boolean canConnectNodesInDirection(Node startNode, Node endNode);

	void appendNodesBetween(Collection<Node> nodes, Node startNode, Node endNode);

	boolean containsNodes(Node... nodes);

	/**
	 * 
	 * @return the length of the segment in KM.
	 */
	double getLength();
}
