package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;

public class LetRec implements IForm {
	private static LetRec instance = new LetRec();
	private static Do doprim = Do.getInstance();
	
	private LetRec() {		
	}
	
	public static LetRec getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		List pairs = (List) arguments.first();
		List body = (List)arguments.rest();
		
		List names = getNames(pairs);
		List expressions = getExpressions(pairs);
		
		Environment newenv = new Environment(environment);
		newenv.setExpressions(names, expressions);
		
		return doprim.evaluate(newenv, body);
	}
	
	private static List getNames(List pairs)
	{
		if (pairs == null)
			return null;
		
		List pair = (List) pairs.first();
		Object name = pair.first();
		
		return new List(name, getNames((List) pairs.rest()));
	}
	
	private static List getExpressions(List pairs)
	{
		if (pairs == null)
			return null;
		
		List pair = (List) pairs.first();
		Object expression = ((List)pair.rest()).first();
		
		return new List(expression, getExpressions((List) pairs.rest()));
	}
}
