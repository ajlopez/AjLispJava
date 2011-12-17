package com.ajlopez.ajlisp;

public class Machine {
	private Environment environment = new Environment();
	
	public static Object evaluate(Environment environment, Object object) {
		if (object == null)
			return null;
		
		if (object instanceof IExpression)
			return ((IExpression) object).evaluate(environment);
		
		return object;
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	public Object evaluate(Object object) {
		return evaluate(this.environment, object);
	}
	
	public static String printString(Object object) {
		if (object == null)
			return "nil";
		
		if (object instanceof IExpression)
			return ((IExpression) object).printString();
		
		// TODO review escape sequence
		if (object instanceof String)
			return "\"" + ((String) object) + "\"";
		
		return object.toString();
	}
}
