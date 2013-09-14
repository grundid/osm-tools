package org.osmtools.osmchange;

public interface SequenceHandler {

	int getKnownSequence();

	void updateSequence(int newSequence);
}
