package com.ajlopez.ajlisp;

import com.ajlopez.ajlisp.primitives.Cons;
import com.ajlopez.ajlisp.primitives.Define;
import com.ajlopez.ajlisp.primitives.Definef;
import com.ajlopez.ajlisp.primitives.Definem;
import com.ajlopez.ajlisp.primitives.Do;
import com.ajlopez.ajlisp.primitives.FLambda;
import com.ajlopez.ajlisp.primitives.First;
import com.ajlopez.ajlisp.primitives.If;
import com.ajlopez.ajlisp.primitives.Lambda;
import com.ajlopez.ajlisp.primitives.Let;
import com.ajlopez.ajlisp.primitives.LetRec;
import com.ajlopez.ajlisp.primitives.MLambda;
import com.ajlopez.ajlisp.primitives.Nilp;
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
		this.environment.setValue("definef", Definef.getInstance());
		this.environment.setValue("definem", Definem.getInstance());
		this.environment.setValue("do", Do.getInstance());
		this.environment.setValue("first", First.getInstance());
		this.environment.setValue("flambda", FLambda.getInstance());
		this.environment.setValue("if", If.getInstance());
		this.environment.setValue("lambda", Lambda.getInstance());
		this.environment.setValue("let", Let.getInstance());
		this.environment.setValue("letrec", LetRec.getInstance());
		this.environment.setValue("list", com.ajlopez.ajlisp.primitives.List.getInstance());
		this.environment.setValue("mlambda", MLambda.getInstance());
		this.environment.setValue("nil?", Nilp.getInstance());
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

	public static List evaluateList(Environment environment, List list)
	{
		if (list == null)
			return null;
		
		return new List(Machine.evaluate(environment, list.first()), evaluateList(environment, (List) list.rest()));   
	}
}
