package org.joda.time;

import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultDateTimeZone {

	private AtomicReference<DateTimeZone> cDefault;

	public DefaultDateTimeZone(AtomicReference<DateTimeZone> cDefault) {
		this.cDefault = cDefault;
	}

	public DateTimeZone getDefault() {
		
		DateTimeZone zone = cDefault.get();
		
		if (zone == null) {
			
			zone = getUserTimeZone();
			
			if (zone == null) {
				zone = getDefaultTimeZone();
			}
			
			if (zone == null) {
				zone = DateTimeZone.UTC;
			}
			
			if (!cDefault.compareAndSet(null, zone)) {
				zone = cDefault.get();
			}
		}
		
		return zone;
	}

	private DateTimeZone getDefaultTimeZone() {
		try {
			DateTimeZone zone = DateTimeZone.forTimeZone(TimeZone.getDefault());
			return zone;
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	private DateTimeZone getUserTimeZone() {
		try {
			String id = getUserTimeZoneId();
			DateTimeZone zone = getDateTimeZoneFromId(id);
			return zone;
		} 
		catch (IllegalArgumentException ex) {
			return null;
		}
		catch (RuntimeException ex) {
			return null;
		}
		
	}

	private String getUserTimeZoneId() {
		String id = System.getProperty("user.timezone");
		return id;
	}

	private DateTimeZone getDateTimeZoneFromId(String id) {
		DateTimeZone zone = null;
		if (id != null) { // null check avoids stack overflow
			zone = DateTimeZone.forID(id);
		}
		return zone;
	}
}
