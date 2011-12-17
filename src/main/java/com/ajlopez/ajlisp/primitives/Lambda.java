package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.forms.Bind;
import com.ajlopez.ajlisp.forms.Closure;

public class Lambda implements IForm {
	private static Lambda instance = new Lambda();
	
	private Lambda() {		
	}
	
	public static Lambda getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		Object parameters = arguments.first();
		List body = (List)arguments.rest();
		
		Bind bind = new Bind(parameters, body);
		Closure closure = new Closure(environment, bind);
		
		return closure;
	}
}