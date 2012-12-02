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
import java.util.Iterator;
import java.util.List;

import org.osmtools.ra.context.AnalyzerContext;
import org.osmtools.ra.segment.ConnectableSegment;
import org.springframework.stereotype.Service;

@Service
public class AggregationService {

	public void aggregate(AnalyzerContext analyzerContext) {
		List<AggregatedSegment> aggregatedSegments = aggregateSegments(analyzerContext.getSegments());
		analyzerContext.setAggregatedSegments(aggregatedSegments);
	}

	public List<AggregatedSegment> aggregateSegments(List<? extends ConnectableSegment> segments) {
		List<AggregatedSegment> aggregatedSegments = new ArrayList<AggregatedSegment>();
		for (Iterator<? extends ConnectableSegment> it = segments.iterator(); it.hasNext();) {
			ConnectableSegment segment = it.next();

			AggregatedSegment newAggregatedSegment = new AggregatedSegment(segment);
			if (!canConnect(aggregatedSegments, newAggregatedSegment))
				aggregatedSegments.add(newAggregatedSegment);
		}
		return aggregateMore(aggregatedSegments);
	}

	private List<AggregatedSegment> aggregateMore(List<AggregatedSegment> segments) {

		List<AggregatedSegment> aggregatedSegments = new ArrayList<AggregatedSegment>();
		int lastRun = 0;
		do {

			lastRun = segments.size();
			aggregatedSegments = new ArrayList<AggregatedSegment>();
			for (Iterator<AggregatedSegment> it = segments.iterator(); it.hasNext();) {
				AggregatedSegment segment = it.next();

				if (!canConnect(aggregatedSegments, segment))
					aggregatedSegments.add(segment);
			}

			segments = aggregatedSegments;

		} while (aggregatedSegments.size() > 1 && lastRun != aggregatedSegments.size());

		return aggregatedSegments;
	}

	private boolean canConnect(List<AggregatedSegment> connectableSegments, AggregatedSegment segment) {
		Collection<ConnectableNode> newSegmentEndpointNodes = segment.getEndpointNodes();

		for (AggregatedSegment connectableSegment : connectableSegments) {
			Collection<ConnectableNode> endpointNodes = connectableSegment.getEndpointNodes();

			if (isConnectable(endpointNodes, newSegmentEndpointNodes)) {
				connectableSegment.addSegment(segment);
				return true;
			}
		}
		return false;
	}

	private boolean isConnectable(Collection<ConnectableNode> nodes1, Collection<ConnectableNode> nodes2) {
		for (ConnectableNode node1 : nodes1) {
			for (ConnectableNode node2 : nodes2) {
				if (node1.isConnectable(node2))
					return true;
			}
		}
		return false;
	}
}
