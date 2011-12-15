package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class Cons extends Primitive {
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
