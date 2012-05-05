package org.osmsurround.ra;

import org.osmsurround.DbConfiguration;
import org.osmsurround.dataimport.ImportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RelationConfiguration extends DbConfiguration {

	@Bean(name = "relationImportHandler")
	@Scope("prototype")
	@Autowired
	public ImportHandler<Relation> getRelationImportHandler(RelationConsumer relationConsumer,
			OsmRelationReader osmRelationReader) {
		ImportHandler<Relation> importHandler = new ImportHandler<Relation>();

		importHandler.setConsumer(relationConsumer);
		importHandler.setProducer(osmRelationReader);

		return importHandler;
	}
}
