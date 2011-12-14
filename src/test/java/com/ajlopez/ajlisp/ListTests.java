package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListTests {

	@Test
	public void createWithConstant() {
		List list = new List(1);
		assertEquals(1, list.first());
		assertNull(list.rest());
	}

	@Test
	public void createWithTwoAtoms() {
		Atom a = new Atom("a");
		Atom b = new Atom("b");
		
		List list = new List(a, new List(b));
		assertEquals(a, list.first());
		assertNotNull(list.rest());
		assertEquals(b, ((List)list.rest()).first());
	}

}
