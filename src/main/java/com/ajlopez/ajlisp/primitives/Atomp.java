package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Predicates;

public class Atomp extends Primitive {
	private static Atomp instance = new Atomp();
	
	private Atomp() {		
	}
	
	public static Atomp getInstance() {
		return instance;		
	}
	public Object apply(Environment environment, List arguments) {
		if (arguments == null)
			return false;
		
		return Predicates.isAtom(arguments.first());
	}
}
