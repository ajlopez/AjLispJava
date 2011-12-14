package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class PredicatesTests {

	@Test
	public void isAtom() {
		assert Predicates.isAtom(1);
		assert Predicates.isAtom("foo");
		assert Predicates.isAtom(new Atom("bar"));
		
		assertFalse(Predicates.isAtom(new List(1)));
	}

	@Test
	public void isList() {
		assertFalse(Predicates.isList(1));
		assertFalse(Predicates.isList("foo"));
		assertFalse(Predicates.isList(new Atom("bar")));
		
		assertTrue(Predicates.isList(new List(1)));
	}

}
