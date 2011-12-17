package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class Cons extends Primitive {
	private static Cons instance = new Cons();
	
	private Cons() {		
	}
	
	public static Cons getInstance() {
		return instance;		
	}
	
	public Object apply(Environment environment, List arguments) {
		Object first = arguments.first();
		Object rest;
		
		if (arguments.rest() == null)
			rest = null;
		else
			rest = ((List)arguments.rest()).first();
		
		return new List(first, rest);
	}
}
