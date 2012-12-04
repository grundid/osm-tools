package org.osmtools.dataimport;

import java.util.ArrayList;
import java.util.List;

import org.osmtools.dataimport.Consumer;

public class OsmObjectListConsumer<T> extends Consumer<T> {

	private List<T> data = new ArrayList<T>();

	@Override
	protected void consume(T dataEntry) {
		data.add(dataEntry);
	}

	public List<T> getData() {
		return data;
	}
}
