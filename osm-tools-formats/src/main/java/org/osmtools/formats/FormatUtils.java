package org.osmtools.formats;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.osm.schema.Osm;

import com.topografix.gpx._1._1.GpxType;

public class FormatUtils {

	public static Unmarshaller createGpxUnmarshaller() {
		try {
			return JAXBContext.newInstance(GpxType.class).createUnmarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static Unmarshaller createOscUnmarshaller() {
		try {
			return JAXBContext.newInstance("org.osmtools.osc").createUnmarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public static Unmarshaller createOsmUnmarshaller() {
		try {
			return JAXBContext.newInstance(Osm.class).createUnmarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
