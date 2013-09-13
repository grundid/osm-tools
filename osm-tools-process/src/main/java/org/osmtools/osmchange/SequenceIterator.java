package org.osmtools.osmchange;

import java.text.DecimalFormat;
import java.util.Iterator;

public class SequenceIterator implements Iterator<String> {

	private static final String BASE_STATE_URL = "http://planet.openstreetmap.org/replication/";
	private DecimalFormat df = new DecimalFormat("000");
	private String granularity;
	private int knownSequence;
	private int currentSequence;

	public SequenceIterator(String granularity, int knownSequence, int currentSequence) {
		this.granularity = granularity;
		this.knownSequence = knownSequence;
		this.currentSequence = currentSequence;
	}

	@Override
	public boolean hasNext() {
		return knownSequence < currentSequence;
	}

	@Override
	public String next() {
		State state = StateSplitter.split(++knownSequence);
		return BASE_STATE_URL + granularity + "/" + df.format(state.getA()) + "/" + df.format(state.getB()) + "/"
				+ df.format(state.getC()) + ".osc.gz";
	}

	@Override
	public void remove() {
		throw new RuntimeException("not implemented");
	}
}
