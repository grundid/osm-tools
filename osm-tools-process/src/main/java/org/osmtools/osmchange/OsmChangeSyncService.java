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
	private Granularity granularity;

	public OsmChangeSyncService(Granularity granularity, RestOperations restOperations,
			OsmChangeService osmChangeService, SequenceHandler sequenceHandler) {
		this.granularity = granularity;
		this.restOperations = restOperations;
		this.osmChangeService = osmChangeService;
		this.sequenceHandler = sequenceHandler;
	}

	@Override
	public void run() {
		OsmChangeResource osmChangeResource = new OsmChangeResource(restOperations);
		int knownSequence = sequenceHandler.getKnownSequence();
		int osmSequence = osmChangeResource.getOsmState(granularity);
		SequenceIterator sequenceIterator = new SequenceIterator(granularity, knownSequence, osmSequence);
		log.debug("Known sequence is {}, current osm sequence is {}. {} sequences to catch up.", new Object[] {
				knownSequence, osmSequence, (osmSequence - knownSequence) });
		for (Sequence sequence : sequenceIterator) {
			log.debug("Getting sequence [{}] from url [{}]", sequence.getSequenceNo(), sequence.getUrl());
			OsmChange osmChange = osmChangeResource.getOsmChange(sequence.getUrl());
			osmChangeService.processOsmChange(osmChange);
			sequenceHandler.updateSequence(sequence.getSequenceNo());
		}
	}
}
