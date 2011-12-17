package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.forms.Bind;
import com.ajlopez.ajlisp.forms.MClosure;

public class MLambda implements IForm {
	private static MLambda instance = new MLambda();
	
	private MLambda() {		
	}
	
	public static MLambda getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		Object parameters = arguments.first();
		List body = (List)arguments.rest();
		
		Bind bind = new Bind(parameters, body);
		MClosure closure = new MClosure(environment, bind);
		
		return closure;
	}
}