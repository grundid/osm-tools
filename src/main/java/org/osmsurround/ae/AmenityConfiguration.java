package org.osmsurround.ae;

import org.osmsurround.DbConfiguration;
import org.osmsurround.dataimport.ImportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AmenityConfiguration extends DbConfiguration {

	@Bean(name = "amenityImportHandler")
	@Scope("prototype")
	@Autowired
	public ImportHandler<Amenity> getAmenityImportHandler(AmenityInsert amenityInsert) {
		ImportHandler<Amenity> importHandler = new ImportHandler<Amenity>();

		importHandler.setConsumer(new AmenityWriter(amenityInsert));
		importHandler.setProducer(new OsmAmenityReader());

		return importHandler;
	}

}
