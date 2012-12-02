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
package org.osmtools.ra.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osm.schema.Osm;
import org.osm.schema.OsmMember;
import org.osm.schema.OsmNd;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmRelation;
import org.osm.schema.OsmTag;
import org.osm.schema.OsmType;
import org.osm.schema.OsmWay;
import org.osmtools.ra.data.Member;
import org.osmtools.ra.data.Node;
import org.osmtools.ra.data.Relation;
import org.osmtools.ra.data.Way;
import org.springframework.stereotype.Service;

@Service
public class OsmSchemaConverterService {

	public List<Relation> convert(Osm osmRoot) {
		Map<Long, Node> knownNodes = createNodes(osmRoot);
		Map<Long, Way> knownWays = createWays(osmRoot, knownNodes);
		return createRelations(osmRoot, knownWays);
	}

	private List<Relation> createRelations(Osm osmRoot, Map<Long, Way> knownWays) {

		List<Relation> relations = new ArrayList<Relation>();

		for (OsmRelation osmRelation : osmRoot.getRelation()) {
			List<Member> members = createMembers(osmRelation, knownWays);
			Map<String, String> tags = createTags(osmRelation);

			relations.add(new Relation(osmRelation.getId().longValue(), members, osmRelation.getTimestamp()
					.toGregorianCalendar(), osmRelation.getUser(), tags));
		}

		return relations;
	}

	private Map<String, String> createTags(OsmRelation osmRelation) {
		Map<String, String> tags = new HashMap<String, String>();

		for (OsmTag osmTag : osmRelation.getTag()) {
			tags.put(osmTag.getK(), osmTag.getV());
		}

		return tags;
	}

	private List<Member> createMembers(OsmRelation osmRelation, Map<Long, Way> knownWays) {
		List<Member> knownMembers = new ArrayList<Member>();
		for (OsmMember osmMember : osmRelation.getMember()) {
			if (OsmType.WAY.equals(osmMember.getType()))
				knownMembers.add(new Member(knownWays.get(osmMember.getRef().longValue()), osmMember.getRole()));
		}
		return knownMembers;
	}

	private Map<Long, Way> createWays(Osm osmRoot, Map<Long, Node> knownNodes) {
		Map<Long, Way> ways = new HashMap<Long, Way>(osmRoot.getWay().size());
		for (OsmWay way : osmRoot.getWay()) {
			ways.put(way.getId().longValue(), createWay(way, knownNodes));
		}
		return ways;
	}

	private Way createWay(OsmWay way, Map<Long, Node> knownNodes) {
		Way newWay = new Way(way.getId().longValue(), way.getNd().size());

		for (OsmNd nd : way.getNd()) {
			newWay.addNode(knownNodes.get(nd.getRef().longValue()));
		}

		for (OsmTag tag : way.getTag()) {
			newWay.setTag(tag.getK(), tag.getV());
		}

		return newWay;
	}

	private Map<Long, Node> createNodes(Osm osmRoot) {
		Map<Long, Node> nodes = new HashMap<Long, Node>(osmRoot.getNode().size());
		for (OsmNode node : osmRoot.getNode()) {
			nodes.put(node.getId().longValue(), createNode(node));
		}
		return nodes;
	}

	private Node createNode(OsmNode node) {
		return new Node(node.getId().longValue(), node.getLat(), node.getLon());
	}
}
