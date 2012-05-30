package org.osmsurround.spring;

import org.osmsurround.api.OsmOperations;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class OsmAdapter implements ApiAdapter<OsmOperations> {

	@Override
	public boolean test(OsmOperations api) {
		return true;
	}

	@Override
	public void setConnectionValues(OsmOperations api, ConnectionValues values) {
	}

	@Override
	public UserProfile fetchUserProfile(OsmOperations api) {
		return null;
	}

	@Override
	public void updateStatus(OsmOperations api, String message) {
	}
}
