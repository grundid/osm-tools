package org.osmsurround.ra.dao;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.osmsurround.ra.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.stereotype.Repository;

@Repository
public class RelationInsert extends BatchSqlUpdate {

	@Autowired
	public RelationInsert(DataSource dataSource) {
		setDataSource(dataSource);
		setBatchSize(100);
		setSql("INSERT INTO relation (relation_id,relation_type,name,route,ref,network,operator) VALUES (?,?,?,?,?,?,?)");
		setTypes(new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR });
	}

	public void insert(Relation relation) {
		Map<String, String> tags = relation.getTags();
		update(new Long(relation.getRelationId()), assertNotNull(tags.get("type")), assertNotNull(tags.get("name")),
				assertNotNull(tags.get("route")), assertNotNull(tags.get("ref")), assertNotNull(tags.get("network")),
				assertNotNull(tags.get("operator")));
	}

	private String assertNotNull(String value) {
		if (value == null)
			return "";
		else
			return value;
	}
}
