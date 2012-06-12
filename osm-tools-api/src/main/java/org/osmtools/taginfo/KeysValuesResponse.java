package org.osmtools.taginfo;

import java.util.List;

public class KeysValuesResponse extends TagInfoResponse {

	private List<KeysValues> data;

	public List<KeysValues> getData() {
		return data;
	}

	public void setData(List<KeysValues> data) {
		this.data = data;
	}

}
