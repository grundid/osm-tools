package org.osmsurround.ae;

import org.osmsurround.ae.dao.NodeInsert;
import org.osmsurround.ae.dao.NodeTagInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AmenityInsert {

	@Autowired
	private NodeInsert nodeInsert;
	@Autowired
	private NodeTagInsert nodeTagInsert;

	public void insertAmenity(Amenity amenity) {
		nodeInsert.insert(amenity);
		nodeTagInsert.insert(amenity.getNodeId(), amenity.getKeyValues());
	}

	public void flush() {
		nodeInsert.flush();
		nodeTagInsert.flush();
	}
}
