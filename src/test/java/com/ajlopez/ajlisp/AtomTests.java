package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtomTests {

	@Test
	public void createAtom() {
		Atom atom = new Atom("foo");
		assertEquals("foo", atom.getName());
	}

	@Test
	public void evaluateAtom() {
		IExpression atom = new Atom("foo");
		Environment environment = new Environment();
		
		assertNull(atom.evaluate(environment));
		
		environment.setValue("foo", 1);
		
		assertEquals(1, atom.evaluate(environment));
	}
}
