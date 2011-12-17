package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;

public class Quote implements IForm {
	private static Quote instance = new Quote();
	
	private Quote() {		
	}
	
	public static Quote getInstance() {
		return instance;		
	}

	public Object evaluate(Environment environment, List arguments) {
		if (arguments == null)
			return null;
		
		return arguments.first();
	}

}
