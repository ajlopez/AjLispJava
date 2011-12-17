package com.ajlopez.ajlisp.forms;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.primitives.Do;

public class Bind implements IForm {
	private static Do doprim = Do.getInstance();
	private Object names;
	private List body;
	
	public Bind(Object names, List body) {
		this.names = names;
		this.body = body;
	}

	public Object evaluate(Environment environment, List arguments) {
		Environment newenv = environment;
		
		if (names != null) {
			newenv = new Environment(environment);
			newenv.setValues(this.names, arguments);
		}
		
		return doprim.evaluate(newenv, this.body);
	}

}
