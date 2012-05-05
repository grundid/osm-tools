package org.osmsurround.ra;

import java.io.InputStream;

import org.junit.Test;
import org.osmsurround.TestBase;
import org.osmsurround.dataimport.ImportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RelationImportTest extends TestBase {

	@Autowired
	@Qualifier("relationImportHandler")
	private ImportHandler<Relation> importHandler;

	@Test
	public void testImport() throws Exception {
		InputStream in = ClassLoader.getSystemResourceAsStream("12320.xml");
		importHandler.setInputStream(in);
		importHandler.run();
	}

}
