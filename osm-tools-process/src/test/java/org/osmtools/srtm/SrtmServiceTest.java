package org.osmtools.srtm;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.osmtools.utils.LonLatMath;

import com.topografix.gpx._1._1.GpxType;
import com.topografix.gpx._1._1.WptType;

public class SrtmServiceTest {

	public Unmarshaller createOsmUnmarshaller() {
		try {
			return JAXBContext.newInstance(GpxType.class).createUnmarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public Marshaller createOsmMarshaller() {
		try {
			return JAXBContext.newInstance(GpxType.class).createMarshaller();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public GpxType fromStream(InputStream inputStream) {
		try {
			JAXBElement<GpxType> jaxbElement = createOsmUnmarshaller().unmarshal(new StreamSource(inputStream),
					GpxType.class);
			return jaxbElement.getValue();
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetElevation() throws Exception {
		GpxType gpxType = fromStream(SrtmServiceTest.class.getResourceAsStream("/relation_49176.gpx"));

		SrtmService srtmService = new SrtmService();
		srtmService.scanDirectory("\\\\alpha\\Data\\data\\srtm\\dds.cr.usgs.gov\\srtm\\version2_1\\SRTM3\\Eurasia");

		List<WptType> trkpt = gpxType.getTrk().get(0).getTrkseg().get(0).getTrkpt();

		double length = 0;
		double asc = 0;
		double desc = 0;
		WptType lastPoint = trkpt.get(0);

		DecimalFormat df = new DecimalFormat("0.00");

		long time = System.currentTimeMillis();

		int lastEle = -1;
		for (WptType wptType : trkpt) {
			int elevation = srtmService.getElevation(wptType.getLon().doubleValue(), wptType.getLat().doubleValue());

			if (lastEle != -1) {

				int diff = lastEle - elevation;

				if (Math.abs(diff) > 10) {
					if (diff > 0)
						asc += diff;
					else
						desc += diff;
					lastEle = elevation;
				}

			}
			else
				lastEle = elevation;
			double distance = LonLatMath.distance(lastPoint.getLon().doubleValue(), lastPoint.getLat().doubleValue(),
					wptType.getLon().doubleValue(), wptType.getLat().doubleValue());
			length += distance;

			System.out.println(df.format(length) + "\t" + elevation);

			lastPoint = wptType;
		}

		System.out.println("Asc:" + asc + " Desc: " + desc);

		//		System.out.println("Time: " + (System.currentTimeMillis() - time));

	}
}
