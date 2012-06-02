package org.osmtools.api;

import java.math.BigDecimal;

import org.osm.schema.ObjectFactory;
import org.osm.schema.Osm;
import org.osm.schema.OsmChangeset;
import org.osm.schema.OsmTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OsmSchemaService {

	private ObjectFactory of = new ObjectFactory();
	private String applicationName;

	@Autowired
	public OsmSchemaService(@Value("${applicationName}") String applicationName) {
		this.applicationName = applicationName;
	}

	public Osm createOsmNode() {
		Osm osm = of.createOsm();
		osm.setVersion(BigDecimal.valueOf(0.6));
		osm.setGenerator(applicationName);
		return osm;
	}

	public Osm createChangeset(String comment) {
		Osm osm = createOsmNode();
		OsmChangeset changeset = of.createOsmChangeset();
		osm.getChangeset().add(changeset);
		addTagValue("comment", comment, changeset);
		if (StringUtils.hasText(applicationName)) {
			addTagValue("created_by", applicationName, changeset);
		}

		return osm;
	}

	private void addTagValue(String key, String value, OsmChangeset changeset) {
		OsmTag tag = of.createOsmTag();
		tag.setK(key);
		tag.setV(value);
		changeset.getTag().add(tag);
	}

}
