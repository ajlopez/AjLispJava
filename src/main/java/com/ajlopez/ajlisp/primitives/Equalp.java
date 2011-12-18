package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Predicates;

public class Equalp extends Primitive {
	private static Equalp instance = new Equalp();
	
	private Equalp() {		
	}
	
	public static Equalp getInstance() {
		return instance;		
	}
	public Object apply(Environment environment, List arguments) {
		Object first = arguments.first();
		Object second = ((List) arguments.rest()).first();
		
		return Predicates.equals(first,  second);
	}
}
