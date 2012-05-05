package org.osmsurround.ae;

import org.osmsurround.dataimport.ImportHandler;
import org.osmsurround.dataimport.SimpleImporter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AmenityImport {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: " + AmenityImport.class.getSimpleName() + " <some-planet-like-osm-file>.osm");
		}
		else {
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.osmsurround.ae");
			ImportHandler<?> importHandler = applicationContext.getBean("amenityImportHandler", ImportHandler.class);

			SimpleImporter simpleImporter = new SimpleImporter();
			simpleImporter.run(args[0], importHandler);
		}
	}

}
