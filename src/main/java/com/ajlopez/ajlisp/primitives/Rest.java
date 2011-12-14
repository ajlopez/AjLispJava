package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class Rest extends Primitive {

	public Object apply(Environment environment, List arguments) {
		List argument = (List) arguments.first();
		
		return argument.rest();
	}
}
