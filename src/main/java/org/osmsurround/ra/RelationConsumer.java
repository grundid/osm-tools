package org.osmsurround.ra;

import org.osmsurround.dataimport.Consumer;
import org.osmsurround.ra.dao.RelationInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RelationConsumer extends Consumer<Relation> {

	@Autowired
	private RelationInsert relationInsert;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	protected void beforeConsuming() {
		jdbcTemplate.execute("DELETE FROM relation");
	}

	@Override
	protected void consume(Relation element) {
		relationInsert.insert(element);
	}

	@Override
	protected void finishConsuming() {
		relationInsert.flush();
	}
}
