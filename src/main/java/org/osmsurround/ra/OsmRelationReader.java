package org.osmsurround.ra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.osmsurround.dataimport.OsmDefaultHandler;
import org.osmsurround.utils.OsmUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

@Service
public class OsmRelationReader extends OsmDefaultHandler<Relation> {

	private Relation relation;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		char c = name.charAt(0);
		if (c == 't') // tag
		{
			if (relation != null) {
				String k = attributes.getValue("k");
				String v = attributes.getValue("v");
				relation.getTags().put(k, v);
			}
		}
		else if (c == 'r') // relation
		{
			relation = new Relation();
			relation.setRelationId(OsmUtils.parseLongSafe(attributes.getValue("id")));
			relation.setTimestamp(parseDateSafe(attributes.getValue("timestamp")));
			relation.setUser(attributes.getValue("user"));
			relation.setTags(new HashMap<String, String>());
		}
	}

	private Calendar parseDateSafe(String string) {
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = dateFormat.parse(string);
			calendar.setTime(date);
		}
		catch (ParseException e) {
		}
		return calendar;
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		if (relation == null)
			return;

		char c = name.charAt(0);
		if (c == 'r') {
			if (relation != null) {

				try {
					queue.put(relation);
				}
				catch (InterruptedException e) {
				}
			}
			relation = null;
		}
	}
}