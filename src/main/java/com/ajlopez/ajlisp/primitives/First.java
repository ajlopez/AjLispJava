package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IExpression;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;

public class First implements IForm {

	public Object apply(Environment environment, List arguments) {
		List argument = (List) arguments.first();
		
		return argument.first();
	}
	
	public Object evaluate(Environment environment, List arguments) {
		return apply(environment, evaluateList(environment, arguments));
	}

	private static List evaluateList(Environment environment, List list)
	{
		if (list == null)
			return null;
		
		return new List(evaluateObject(environment, list.first()), evaluateList(environment, (List) list.rest()));   
	}
	
	private static Object evaluateObject(Environment environment, Object object)
	{
		if (object == null)
			return null;
		
		if (object instanceof IExpression)
			return ((IExpression) object).evaluate(environment);
		
		return object;
	}
}
