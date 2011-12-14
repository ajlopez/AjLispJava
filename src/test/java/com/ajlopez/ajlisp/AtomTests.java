package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtomTests {

	@Test
	public void createNamedAtom() {
		Atom atom = new Atom("foo");
		assertEquals("foo", atom.getName());
	}
}
