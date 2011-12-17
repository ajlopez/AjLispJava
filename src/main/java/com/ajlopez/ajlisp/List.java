package com.ajlopez.ajlisp;

public class List implements IExpression {
	private Object first;
	private Object rest;
	
	public List(Object first)
	{
		this(first, null);
	}
	
	public List(Object first, Object rest)
	{
		this.first = first;
		this.rest = rest;
	}
	
	public Object first()
	{
		return this.first;
	}
	
	public Object rest()
	{
		return this.rest;
	}

	public Object evaluate(Environment environment) {
		IForm form = (IForm) Machine.evaluate(environment, this.first);
		return form.evaluate(environment, (List) this.rest);
	}

	public String printString() {
		// TODO use string buffer
		String result = "(";
		
		result += Machine.printString(this.first);
		
		Object rest = this.rest;
		
		while (rest != null) 
		{
			result += " ";
			List list = (List)rest;
			result += Machine.printString(list.first());
			rest = list.rest();
		}
		
		result += ")";
		
		return result;
	}
}
