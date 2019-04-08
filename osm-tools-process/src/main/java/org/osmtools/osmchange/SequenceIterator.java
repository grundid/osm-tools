package org.osmtools.osmchange;

import java.text.DecimalFormat;
import java.util.Iterator;

public class SequenceIterator implements Iterator<Sequence>, Iterable<Sequence> {

	private static final String BASE_STATE_URL = "https://planet.openstreetmap.org/replication/";
	private DecimalFormat df = new DecimalFormat("000");
	private Granularity granularity;
	private int knownSequence;
	private int osmSequence;

	public SequenceIterator(Granularity granularity, int knownSequence, int osmSequence) {
		this.granularity = granularity;
		this.knownSequence = knownSequence;
		this.osmSequence = osmSequence;
	}

	@Override
	public boolean hasNext() {
		return knownSequence < osmSequence;
	}

	@Override
	public Sequence next() {
		knownSequence++;
		State state = StateSplitter.split(knownSequence);
		String url = BASE_STATE_URL + granularity.name() + "/" + df.format(state.getA()) + "/"
				+ df.format(state.getB()) + "/" + df.format(state.getC()) + ".osc.gz";
		return new Sequence(knownSequence, url);
	}

	@Override
	public void remove() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public Iterator<Sequence> iterator() {
		return this;
	}
}
