package org.osmtools.osmchange;

import org.osmtools.osc.OsmChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

public class OsmChangeSyncService implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());
	private RestOperations restOperations;
	private OsmChangeService osmChangeService;
	private SequenceHandler sequenceHandler;

	public OsmChangeSyncService(RestOperations restOperations, OsmChangeService osmChangeService,
			SequenceHandler sequenceHandler) {
		this.restOperations = restOperations;
		this.osmChangeService = osmChangeService;
		this.sequenceHandler = sequenceHandler;
	}

	@Override
	public void run() {
		OsmChangeResource osmChangeResource = new OsmChangeResource(restOperations);
		int knownSequence = sequenceHandler.getKnownSequence();
		int osmSequence = osmChangeResource.getOsmState(Granularity.hour);
		SequenceIterator sequenceIterator = new SequenceIterator(Granularity.hour, knownSequence, osmSequence);
		for (Sequence sequence : sequenceIterator) {
			log.debug("Getting sequence [{}] from url [{}]", sequence.getSequenceNo(), sequence.getUrl());
			OsmChange osmChange = osmChangeResource.getOsmChange(sequence.getUrl());
			osmChangeService.processOsmChange(osmChange);
			sequenceHandler.updateSequence(sequence.getSequenceNo());
		}
	}
}
