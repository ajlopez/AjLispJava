package com.ajlopez.ajlisp;

public class Atom {
	private String name;

	public Atom(String name) {
		this.name = name;
	}

	public Object getName() {
		return this.name;
	}

}
