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
package org.osmtools.ra.analyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osmtools.ra.segment.ConnectableSegment;

/**
 * A segment that contains only segments that can be connected.
 * 
 */
public class AggregatedSegment {

	private List<ConnectableSegment> segments = new ArrayList<ConnectableSegment>();

	public AggregatedSegment(ConnectableSegment segment) {
		segments.add(segment);
	}

	public AggregatedSegment(AggregatedSegment aggregatedSegment) {
		addSegment(aggregatedSegment);
	}

	public Collection<ConnectableNode> getEndpointNodes() {
		Collection<ConnectableNode> nodes = new ArrayList<ConnectableNode>();
		for (ConnectableSegment segment : segments)
			nodes.add(segment.getEndpointNodes());
		return nodes;
	}

	public void addSegment(AggregatedSegment segment) {
		addAllSegments(segment.getSegments());
	}

	public void addAllSegments(Collection<ConnectableSegment> segments) {
		this.segments.addAll(segments);
	}

	public List<ConnectableSegment> getSegments() {
		return segments;
	}
}
