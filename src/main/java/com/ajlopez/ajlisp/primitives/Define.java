package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class Define implements IForm {
	private static Define instance = new Define();
	
	private Define() {		
	}
	
	public static Define getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		Atom atom = (Atom)arguments.first();
		Object object = ((List)arguments.rest()).first();
		Object value = Machine.evaluate(environment, object);
		
		environment.getTopEnvironment().setValue(atom.getName(), value);
		
		return value;
	}
}