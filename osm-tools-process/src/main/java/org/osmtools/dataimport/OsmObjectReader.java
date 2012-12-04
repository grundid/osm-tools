package org.osmtools.dataimport;

import org.osmtools.dataimport.BlockingQueueDefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class OsmObjectReader<T extends OsmObject> extends BlockingQueueDefaultHandler<T> {

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		char c = qName.charAt(0);
		AttributesReader reader = new AttributesReader(attributes);
		if (c == 't') {// tag
			onTagStart(reader);
		}
		else if (c == 'n' && !qName.equals("nd")) { // node
			onNodeStart(reader);
		}
		else if (c == 'n' && qName.equals("nd")) { // nd within way
			onWayNodeStart(reader);
		}
		else if (c == 'r') {
			onRelationStart(reader);
		}
		else if (c == 'm') { // member
			onRelationMemberStart(reader);
		}
		else if (c == 'w') { // way
			onWayStart(reader);
		}
	}

	protected void onTagStart(AttributesReader reader) {
	}

	protected void onNodeStart(AttributesReader reader) {
	}

	protected void onWayNodeStart(AttributesReader reader) {
	}

	protected void onRelationStart(AttributesReader reader) {
	}

	protected void onRelationMemberStart(AttributesReader reader) {
	}

	protected void onWayStart(AttributesReader reader) {
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		char c = qName.charAt(0);
		if (c == 't') // tag
		{
			onTagEnd();
		}
		else if (c == 'n' && !qName.equals("nd")) // node
		{
			onNodeEnd();
		}
		else if (c == 'n' && qName.equals("nd")) // nd within way
		{
			onWayNodeEnd();
		}
		else if (c == 'r') {
			onRelationEnd();
		}
		else if (c == 'm') { // member
			onRelationMemberEnd();
		}
		else if (c == 'w') { // way
			onWayEnd();
		}
	}

	protected void onWayEnd() {
	}

	protected void onRelationMemberEnd() {
	}

	protected void onRelationEnd() {
	}

	protected void onWayNodeEnd() {
	}

	protected void onNodeEnd() {
	}

	protected void onTagEnd() {
	}
}
