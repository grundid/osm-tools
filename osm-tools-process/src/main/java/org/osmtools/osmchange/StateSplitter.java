package org.osmtools.osmchange;

public class StateSplitter {

	public static State split(int state) {
		return new State((int)(Math.floor(state / 1000000.0) % 1000), (int)(Math.floor(state / 1000.0) % 1000),
				state % 1000);
	}
}
