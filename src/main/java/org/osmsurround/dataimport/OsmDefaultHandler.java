package org.osmsurround.dataimport;

import java.util.concurrent.BlockingQueue;

import org.xml.sax.helpers.DefaultHandler;

public class OsmDefaultHandler<T> extends DefaultHandler {

	protected BlockingQueue<T> queue;

	public void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}
}
