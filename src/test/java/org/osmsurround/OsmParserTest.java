package org.osmsurround;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.osm.schema.Osm;
import org.osm.schema.OsmNd;
import org.osm.schema.OsmNode;
import org.osm.schema.OsmTag;
import org.osm.schema.OsmWay;
import org.osmsurround.api.GeoJsonExport;
import org.osmsurround.api.Section;
import org.osmsurround.data.Node;
import org.osmsurround.data.SimpleSection;

public class OsmParserTest {

	public Unmarshaller createOsmUnmarshaller() {
		try {
			return JAXBContext.newInstance(Osm.class).createUnmarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testOsmParse() throws Exception {

		Unmarshaller unmarshaller = createOsmUnmarshaller();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("map3.xml");
		JAXBElement<Osm> element = unmarshaller.unmarshal(new StreamSource(inputStream), Osm.class);

		Osm osm = element.getValue();

		Map<BigInteger, OsmNode> nodes = new HashMap<BigInteger, OsmNode>();

		for (OsmNode node : osm.getNode()) {
			nodes.put(node.getId(), node);
		}

		List<Section> ways = new ArrayList<Section>();

		for (OsmWay osmWay : osm.getWay()) {
			boolean highwayTrack = false;
			boolean grade = false;
			for (OsmTag tag : osmWay.getTag()) {
				if (tag.getK().equals("highway") && tag.getV().equals("track"))
					highwayTrack = true;
				if (tag.getK().equals("tracktype") && tag.getV().startsWith("grade"))
					grade = true;
			}
			if (highwayTrack) {
				if (!grade) {
					//					System.out.println("Way " + osmWay.getId() + " ohne grade");

					SimpleSection section = new SimpleSection("<a href='javascript:selectWay(" + osmWay.getId()
							+ ");'>Way " + osmWay.getId() + "</a>");

					for (OsmNd nd : osmWay.getNd()) {
						OsmNode node = nodes.get(nd.getRef());
						section.addNode(new Node(node.getLon(), node.getLat()));
					}
					ways.add(section);
				}
			}
		}

		GeoJsonExport export = new GeoJsonExport();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		export.export(ways, baos);

		//		FileOutputStream out = new FileOutputStream("C:\\tracks.json");
		//		baos.writeTo(out);
		//		out.flush();
		//		out.close();

		//		System.out.println(new String(baos.toByteArray()));

	}
}
