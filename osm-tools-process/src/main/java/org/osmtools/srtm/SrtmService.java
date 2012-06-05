package org.osmtools.srtm;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class SrtmService {

	private Map<Integer, SrtmTile> tileMap = new HashMap<Integer, SrtmTile>();

	public void scanDirectory(String directory) {

		File startDir = new File(directory);

		File[] files = startDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".hgt") || name.endsWith(".zip");
			}
		});

		for (File file : files) {
			SrtmTile srtmTile = new SrtmTile(file);
			int key = (srtmTile.getLat() + 90) * 360 + (srtmTile.getLon() + 180);
			tileMap.put(key, srtmTile);
		}
	}

	public void insertElevation(Iterable<Elevationable> iterable) {
		for (Elevationable elevationable : iterable) {
			int key = (((int)elevationable.getLat()) + 90) * 360 + (((int)elevationable.getLon()) + 180);
			SrtmTile srtmTile = tileMap.get(key);
			elevationable.setElevation(srtmTile.getElevation(elevationable.getLon(), elevationable.getLat()));
		}
	}

	public int getElevation(double lon, double lat) {
		int key = (((int)lat) + 90) * 360 + (((int)lon) + 180);
		SrtmTile srtmTile = tileMap.get(key);
		return srtmTile.getElevation(lon, lat);
	}
}
