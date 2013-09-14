package org.osmtools.osmchange;

public class Sequence {

	private int sequenceNo;
	private String url;

	public Sequence(int sequenceNo, String url) {
		this.sequenceNo = sequenceNo;
		this.url = url;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public String getUrl() {
		return url;
	}
}
