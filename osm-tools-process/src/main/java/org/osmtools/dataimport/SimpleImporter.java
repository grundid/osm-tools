package org.osmtools.dataimport;

import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleImporter {

	private Logger log = LoggerFactory.getLogger(getClass());

	public void run(String importFile, ImportHandler<?> importHandler) {
		try {
			log.info("Importing " + importFile);
			importHandler.setInputStream(new FileInputStream(importFile));
			importHandler.run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
