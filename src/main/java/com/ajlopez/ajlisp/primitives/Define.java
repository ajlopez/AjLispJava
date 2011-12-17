package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class Define implements IForm {

	public Object evaluate(Environment environment, List arguments) {
		Atom atom = (Atom)arguments.first();
		Object object = ((List)arguments.rest()).first();
		Object value = Machine.evaluate(environment, object);
		
		environment.setValue(atom.getName(), value);
		
		return value;
	}
}