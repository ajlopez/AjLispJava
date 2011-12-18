package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.Predicates;

public class If implements IForm {
	private static Do doprim = Do.getInstance();
	private static If instance = new If();
	
	private If() {		
	}
	
	public static If getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		Object condition = arguments.first();
		Object then = ((List)arguments.rest()).first();
		List elsebody = (List)((List)arguments.rest()).rest();
		
		Object result = Machine.evaluate(environment, condition);
		
		if (Predicates.isTrue(result))
			return Machine.evaluate(environment, then);
		
		return doprim.evaluate(environment, elsebody);
	}

}
