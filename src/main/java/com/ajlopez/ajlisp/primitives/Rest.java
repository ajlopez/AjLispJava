package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class Rest implements IForm {

	public Object apply(Environment environment, List arguments) {
		List argument = (List) arguments.first();
		
		return argument.rest();
	}
	
	public Object evaluate(Environment environment, List arguments) {
		return apply(environment, evaluateList(environment, arguments));
	}

	private static List evaluateList(Environment environment, List list)
	{
		if (list == null)
			return null;
		
		return new List(Machine.evaluate(environment, list.first()), evaluateList(environment, (List) list.rest()));   
	}
}
