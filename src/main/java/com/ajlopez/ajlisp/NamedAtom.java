package com.ajlopez.ajlisp;

public class NamedAtom {
	private String name;

	public NamedAtom(String name) {
		this.name = name;
	}

	public Object getName() {
		return this.name;
	}

}
