package org.osmtools.osmchange;

import org.osmtools.osc.Create;
import org.osmtools.osc.Delete;
import org.osmtools.osc.Modify;
import org.osmtools.osc.Node;
import org.osmtools.osc.OsmChange;
import org.osmtools.osc.Relation;
import org.osmtools.osc.Way;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
public abstract class AbstractOsmChangeService implements OsmChangeService {

	@Override
	@Transactional
	public void processOsmChange(OsmChange osmChange) {
		prepareProcess(osmChange);
		processCreates(osmChange);
		processModifications(osmChange);
		processDeletes(osmChange);
		finishProcess(osmChange);
	}

	protected void prepareProcess(OsmChange osmChange) {
	}

	protected void finishProcess(OsmChange osmChange) {
	}

	private void processCreates(OsmChange osmChange) {
		for (Create create : osmChange.getCreate()) {
			for (Relation relation : create.getRelation()) {
				createRelation(relation);
			}
			for (Node node : create.getNode()) {
				createNode(node);
			}
			for (Way way : create.getWay()) {
				createWay(way);
			}
		}
	}

	private void processModifications(OsmChange osmChange) {
		for (Modify modify : osmChange.getModify()) {
			for (Relation relation : modify.getRelation()) {
				modifyRelation(relation);
			}
			for (Node node : modify.getNode()) {
				modifyNode(node);
			}
			for (Way way : modify.getWay()) {
				modifyWay(way);
			}
		}
	}

	private void processDeletes(OsmChange osmChange) {
		for (Delete delete : osmChange.getDelete()) {
			for (Relation relation : delete.getRelation()) {
				deleteRelation(relation);
			}
			for (Node node : delete.getNode()) {
				deleteNode(node);
			}
			for (Way way : delete.getWay()) {
				deleteWay(way);
			}
		}
	}

	protected void createRelation(Relation relation) {
	};

	protected void modifyRelation(Relation relation) {
	};

	protected void deleteRelation(Relation relation) {
	};

	protected void createNode(Node node) {
	};

	protected void modifyNode(Node node) {
	};

	protected void deleteNode(Node node) {
	};

	protected void createWay(Way way) {
	};

	protected void modifyWay(Way way) {
	};

	protected void deleteWay(Way way) {
	};
}
