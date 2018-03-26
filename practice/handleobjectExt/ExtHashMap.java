package com.sjh.thinkinginjava.handleobjectExt;

import java.util.HashMap;

@SuppressWarnings("serial")
public final class ExtHashMap<K, V> extends HashMap<K, V> {
	public ExtHashMap() {
		super();
	}
	
	public ExtHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity,loadFactor);
	} 
	
	public ExtHashMap(int initialCapacity) {
		super(initialCapacity);
	} 
	
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		if(key instanceof String) {
			return super.put((K)((String)key).toLowerCase(), value);
		}
		return super.put(key, value);
	}
}
