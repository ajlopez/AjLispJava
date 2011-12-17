package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class Rest extends Primitive {
	private static Rest instance = new Rest();
	
	private Rest() {		
	}
	
	public static Rest getInstance() {
		return instance;		
	}

	public Object apply(Environment environment, List arguments) {
		List argument = (List) arguments.first();
		
		return argument.rest();
	}
}
