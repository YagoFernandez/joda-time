package org.joda.time;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.base.BaseSingleFieldPeriod;

public class PoolManager {

	private static Map<Class<? extends BaseSingleFieldPeriod>, GenericPool> pools = new HashMap<Class<? extends BaseSingleFieldPeriod>, GenericPool>();
	
	public static GenericPool getPool(Class type) {
		
		GenericPool pool = pools.get(type);
		
		if (pool == null) {
			pool = new GenericPool(type);
			register(pool);
		}
		
		return pool;
	}
	
	private static void register(GenericPool pool) {
		pools.put(pool.getContainedClass(), pool);
	}
	
	
}
