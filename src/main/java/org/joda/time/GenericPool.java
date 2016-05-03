package org.joda.time;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.base.BaseSingleFieldPeriod;

public class GenericPool {

	private Class<BaseSingleFieldPeriod> classType;
	private Map<Integer, BaseSingleFieldPeriod> values;

	public GenericPool(Class<BaseSingleFieldPeriod> classType) {
		this.classType = classType;
		this.values = new HashMap<Integer, BaseSingleFieldPeriod>();
	}

	public BaseSingleFieldPeriod retrieve(int numeral) {
		
		BaseSingleFieldPeriod result = values.get(numeral);

		if (result == null) {
			result = get(numeral);
			add(numeral, result);
		}

		return result;
	}
	
	public Class<BaseSingleFieldPeriod> getContainedClass() {
		return classType;
	}
	
	private void add(int numeral, BaseSingleFieldPeriod value) {
		values.put(new Integer(numeral), value);
	}

	private BaseSingleFieldPeriod get(int numeral) {
		return ItemFactory.getInstance(numeral, classType);
	}
	
}
