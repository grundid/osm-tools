package org.osmtools.utils;

public class OsmUtils {

	public static long parseLongSafe(String s) {
		try {
			return Long.parseLong(s);
		}
		catch (Exception e) {
			return 0;
		}
	}

}
