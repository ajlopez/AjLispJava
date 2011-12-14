package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class NamedAtomTests {

	@Test
	public void createNamedAtom() {
		NamedAtom atom = new NamedAtom("foo");
		assertEquals("foo", atom.getName());
	}
}
