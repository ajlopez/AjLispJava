package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;

public class List extends Primitive {
	private static List instance = new List();
	
	private List() {		
	}
	
	public static List getInstance() {
		return instance;		
	}
	public Object apply(Environment environment, com.ajlopez.ajlisp.List arguments) {
		return arguments;
	}
}
