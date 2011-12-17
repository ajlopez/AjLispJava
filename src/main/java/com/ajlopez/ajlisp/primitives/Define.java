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
		List list = (List)arguments.rest();
		Object value = null;
		
		if (list.rest() == null) {		
			Object object = list.first();
			value = Machine.evaluate(environment, object);
		}
		else {
			value = Lambda.getInstance().evaluate(environment, list);
		}
		
		environment.getTopEnvironment().setValue(atom.getName(), value);

		return value;
	}
}