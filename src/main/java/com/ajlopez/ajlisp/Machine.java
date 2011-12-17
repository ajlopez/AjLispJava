package com.ajlopez.ajlisp;

import com.ajlopez.ajlisp.primitives.Cons;
import com.ajlopez.ajlisp.primitives.Define;
import com.ajlopez.ajlisp.primitives.Do;
import com.ajlopez.ajlisp.primitives.First;
import com.ajlopez.ajlisp.primitives.Quote;
import com.ajlopez.ajlisp.primitives.Rest;

public class Machine {
	private Environment environment = new Environment();
	
	public static Object evaluate(Environment environment, Object object) {
		if (object == null)
			return null;
		
		if (object instanceof IExpression)
			return ((IExpression) object).evaluate(environment);
		
		return object;
	}
	
	public Machine() {
		this.environment.setValue("cons", Cons.getInstance());
		this.environment.setValue("define", Define.getInstance());
		this.environment.setValue("do", Do.getInstance());
		this.environment.setValue("first", First.getInstance());
		this.environment.setValue("quote", Quote.getInstance());
		this.environment.setValue("rest", Rest.getInstance());
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
