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
			
			Object rest = names.rest();
			
			if (rest instanceof Atom) {
				this.setValue(((Atom)rest).getName(), values);
				return;
			}
			
			names = (List)rest;
		}
	}
	
	public void setValues(Object names, List values) {
		if (names == null)
			return;
		
		if (names instanceof List) {
			this.setValues((List) names, values);
			return;
		}
		
		Atom atom = (Atom) names;
		
		this.setValue(atom.getName(), values);
	}
	
	public void setExpressions(List names, List expressions) {
		while (names != null) {			
			Atom atom = (Atom)names.first();
			Object value = null;
			
			if (expressions != null) {
				Object expression = expressions.first();
				value = Machine.evaluate(this,  expression);
				expressions = (List)expressions.rest();
			}
			
			this.setValue(atom.getName(), value);
			
			Object rest = names.rest();
			
			if (rest instanceof Atom) {
				this.setValue(((Atom)rest).getName(), Machine.evaluateList(this, expressions));
				return;
			}
			
			names = (List)rest;
		}
	}
	
	public Environment getTopEnvironment(){
		if (this.parent == null)
			return this;
		
		return this.parent.getTopEnvironment();
	}
}
