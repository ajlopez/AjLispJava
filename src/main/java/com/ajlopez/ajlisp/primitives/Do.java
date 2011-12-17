package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class Do implements IForm {
	private static Do instance = new Do();
	
	private Do() {		
	}
	
	public static Do getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		Object result = null;
		
		while (arguments != null) {
			result = Machine.evaluate(environment, arguments.first());
			arguments = (List)arguments.rest();
		}
		
		return result;
	}

}
