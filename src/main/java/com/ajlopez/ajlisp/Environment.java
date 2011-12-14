package com.ajlopez.ajlisp;

import java.util.Dictionary;
import java.util.Hashtable;

public class Environment {
	Dictionary<String, Object> values = new Hashtable<String, Object>();
	
	public Object getValue(String name) {
		return values.get(name);
	}

	public void setValue(String name, Object value) {
		values.put(name, value);
	}

}
