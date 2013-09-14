package org.osmtools.osmchange;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

public class SimpleFileSequenceHandler implements SequenceHandler {

	private File sequenceFile;

	public SimpleFileSequenceHandler(File sequenceFile) {
		this.sequenceFile = sequenceFile;
	}

	@Override
	public int getKnownSequence() {
		try {
			String sequence = FileUtils.readFileToString(sequenceFile);
			return Integer.parseInt(StringUtils.trimAllWhitespace(sequence));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateSequence(int newSequence) {
		try {
			FileUtils.writeStringToFile(sequenceFile, newSequence + "");
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
