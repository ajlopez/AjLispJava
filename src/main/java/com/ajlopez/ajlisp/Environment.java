package com.ajlopez.ajlisp;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	private Environment parent;
	private Map<String, Object> values = new HashMap<String, Object>();
	
	public Environment() {
		this(null);
	}
	
	public Environment(Environment parent) {
		this.parent = parent;
	}
	
	public Object getValue(String name) {
		if (!this.values.containsKey(name) && this.parent != null)
			return this.parent.getValue(name);
		
		return this.values.get(name);
	}

	public void setValue(String name, Object value) {
		this.values.put(name, value);
	}
	
	public void setValues(List names, List values) {
		while (names != null) {			
			Atom atom = (Atom)names.first();
			Object value = null;
			
			if (values != null) {
				value = values.first();
				values = (List)values.rest();
			}
			
			this.setValue(atom.getName(), value);
			names = (List)names.rest();
		}
	}
}
