package com.ajlopez.ajlisp;

public class Atom implements IExpression {
	private String name;

	public Atom(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public Object evaluate(Environment environment) {
		return environment.getValue(this.name);
	}

	public String printString() {
		return this.name;
	}
}
