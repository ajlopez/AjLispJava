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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Atom))
			return false;
		
		return this.getName().equals(((Atom)obj).getName());
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
