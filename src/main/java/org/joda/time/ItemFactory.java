package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;

public class ItemFactory {

	public static BaseSingleFieldPeriod getInstance(int numeral, Class<BaseSingleFieldPeriod> type) {

		if (type.equals(Minutes.class)) {
			return new Minutes(numeral);
		}

		if (type.equals(Days.class)) {
			return new Days(numeral);
		}

		if (type.equals(Months.class)) {
			return new Months(numeral);
		}

		return null; //TODO: InvalidClassTypeException
	}

}