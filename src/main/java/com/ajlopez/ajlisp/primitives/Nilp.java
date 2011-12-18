package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Predicates;

public class Nilp extends Primitive {
	private static Nilp instance = new Nilp();
	
	private Nilp() {		
	}
	
	public static Nilp getInstance() {
		return instance;		
	}
	public Object apply(Environment environment, List arguments) {
		if (arguments == null)
			return true;
		
		return Predicates.isNil(arguments.first());
	}
}
