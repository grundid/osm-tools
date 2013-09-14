package org.osmtools.formats;

import org.osmtools.osc.Relation;
import org.osmtools.osc.Tag;

public class OsmChangeUtils {

	public static String getTagValue(Relation relation, String key) {
		return getTagValue(relation.getTag(), key);
	}

	public static String getTagValue(Iterable<Tag> tags, String key) {
		for (Tag tag : tags) {
			if (tag.getK().equals(key))
				return tag.getV();
		}
		return "";
	}
}
