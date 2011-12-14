package com.ajlopez.ajlisp;

public class Atom implements IExpression {
	private String name;

	public Atom(String name) {
		this.name = name;
	}

	public Object getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.ajlopez.ajlisp.IExpression#evaluate(com.ajlopez.ajlisp.Environment)
	 */
	@Override
	public Object evaluate(Environment environment) {
		return environment.getValue(this.name);
	}

}
