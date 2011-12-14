package com.ajlopez.ajlisp;

public class Machine {
	public static Object evaluate(Environment environment, Object object)
	{
		if (object == null)
			return null;
		
		if (object instanceof IExpression)
			return ((IExpression) object).evaluate(environment);
		
		return object;
	}
}
