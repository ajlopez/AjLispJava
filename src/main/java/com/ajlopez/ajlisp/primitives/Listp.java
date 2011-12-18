package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Predicates;

public class Listp extends Primitive {
	private static Listp instance = new Listp();
	
	private Listp() {		
	}
	
	public static Listp getInstance() {
		return instance;		
	}
	public Object apply(Environment environment, List arguments) {
		if (arguments == null)
			return false;
		
		return Predicates.isList(arguments.first());
	}
}
