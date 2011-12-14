package com.ajlopez.ajlisp;

import java.util.Dictionary;
import java.util.Hashtable;

public class Environment {
	Dictionary values = new Hashtable();
	
	public Object getValue(String name) {
		return values.get(name);
	}

	public void setValue(String name, Object value) {
		values.put(name, value);
	}

}
