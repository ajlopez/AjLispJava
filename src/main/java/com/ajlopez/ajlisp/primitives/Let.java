package com.ajlopez.ajlisp.primitives;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.forms.Bind;
import com.ajlopez.ajlisp.forms.Closure;

public class Let implements IForm {
	private static Let instance = new Let();
	private static Do doprim = Do.getInstance();
	
	private Let() {		
	}
	
	public static Let getInstance() {
		return instance;		
	}
	
	public Object evaluate(Environment environment, List arguments) {
		List pairs = (List) arguments.first();
		List body = (List)arguments.rest();
		
		List names = getNames(pairs);
		List expressions = getExpressions(pairs);
		
		List values = Machine.evaluateList(environment, expressions);
		
		Environment newenv = new Environment(environment);
		newenv.setValues(names, values);
		
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