package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public abstract class Primitive implements IForm {

	public abstract Object apply(Environment environment, List arguments);
	
	public Object evaluate(Environment environment, List arguments) {
		return apply(environment, Machine.evaluateList(environment, arguments));
	}
}
