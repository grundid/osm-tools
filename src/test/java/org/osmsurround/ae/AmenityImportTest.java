package org.osmsurround.ae;

import java.io.InputStream;

import org.junit.Test;
import org.osmsurround.TestBase;
import org.osmsurround.dataimport.ImportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AmenityImportTest extends TestBase {

	@Autowired
	@Qualifier("amenityImportHandler")
	private ImportHandler<Amenity> importHandler;

	@Test
	public void testImport() throws Exception {
		InputStream in = ClassLoader.getSystemResourceAsStream("bbox.xml");
		importHandler.setInputStream(in);
		importHandler.run();
	}

}
