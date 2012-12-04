package org.osmtools.dataimport;

import org.xml.sax.Attributes;

public class AttributesReader {

	private Attributes attributes;

	public AttributesReader(Attributes attributes) {
		this.attributes = attributes;
	}

	public String getValue(String attrName) {
		return attributes.getValue(attrName);
	}

	public long getLongSafe(String attrName) {
		try {
			return Long.parseLong(getValue(attrName));
		}
		catch (Exception e) {
			return 0;
		}
	}

	public float getFloatSafe(String attrName) {
		try {
			return Float.parseFloat(getValue(attrName));
		}
		catch (Exception e) {
			return 0;
		}
	}

}
