package com.packtpub.e4.clock.ui.internal;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeZoneComparator<T> implements Comparator<T> {

	@Override
	public int compare(T arg0, T arg1) {
		
		if(arg0 instanceof TimeZone && arg1 instanceof TimeZone) {
			return ((TimeZone)arg0).getID().compareTo(((TimeZone)arg1).getID());
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static Map<String, Set<TimeZone>> getTimeZones() {
		String[] ids = TimeZone.getAvailableIDs();
		Map<String, Set<TimeZone>> timeZones = new TreeMap<String, Set<TimeZone>>();
		for (int i = 0 ; i < ids.length ; i++) {
			String parts[] = ids[i].split("/");
			if (parts.length == 2) {
				String region = parts[0];
				Set<TimeZone> zones = timeZones.get(region);
				if (zones == null) {
					zones = new TreeSet<TimeZone>(new TimeZoneComparator());
					timeZones.put(region, zones);
				}
				TimeZone timeZone = TimeZone.getTimeZone(ids[i]);
				zones.add(timeZone);
			}
		}
		return timeZones;
	}
}
