package org.osmtools.srtm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SrtmTile {

	private static final Pattern filePattern = Pattern.compile("(N|S)(..)(W|E)(...).*");

	private File file;
	private ByteBuffer buffer;

	private int lat;
	private int lon;

	public SrtmTile(File file) {
		this.file = file;
		Matcher matcher = filePattern.matcher(file.getName());
		if (matcher.matches()) {
			String latDir = matcher.group(1);
			lat = Integer.parseInt(matcher.group(2));
			if (latDir.equalsIgnoreCase("s"))
				lat *= -1;
			String lonDir = matcher.group(3);
			lon = Integer.parseInt(matcher.group(4));
			if (lonDir.equalsIgnoreCase("w"))
				lon *= -1;
		}
		else {
			throw new IllegalArgumentException("File name does not match pattern");
		}
	}

	public SrtmTile(InputStream in, int lon, int lat) {
		this.lon = lon;
		this.lat = lat;
		try {
			initBuffer(in);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean hasData(double lon, double lat) {
		return (int)lon == this.lon && (int)lat == this.lat;
	}

	private void initData() {
		InputStream in = null;
		try {
			if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
				ZipFile zipFile = new ZipFile(file);
				ZipEntry firstEntry = zipFile.entries().nextElement();
				in = zipFile.getInputStream(firstEntry);
			}
			else {
				in = new FileInputStream(file);
			}

			initBuffer(in);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void initBuffer(InputStream in) throws IOException {
		byte[] readBuffer = new byte[1201 * 1201 * 2];

		int offset = 0;
		while (in.available() > 0) {
			int read = in.read(readBuffer, offset, readBuffer.length - offset);
			offset += read;
		}
		buffer = ByteBuffer.wrap(readBuffer);
		buffer.order(ByteOrder.BIG_ENDIAN);
	}

	public int getElevation(double lon, double lat) {
		if (buffer == null)
			initData();

		int x = (int)Math.round((lon - this.lon) * 2400);
		int y = (int)Math.round((lat - this.lat) * 1200);

		int pos = (1200 - y) * (1201 * 2) + x;
		return buffer.getShort(pos);
	}

	public int getLat() {
		return lat;
	}

	public int getLon() {
		return lon;
	}
}
