package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class EqualpTests {

	@Test
	public void simpleEvaluate() {
		Equalp equalp = Equalp.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a, new List(a));
		List list2 = new List(a, new List(1));
		List list3 = new List(1, new List(1));
		List list4 = new List("foo", new List("bar"));
		List list5 = new List("foo", new List("foo"));

		assertEquals(true, equalp.apply(environment, list));
		assertEquals(false, equalp.apply(environment, list2));
		assertEquals(true, equalp.apply(environment, list3));
		assertEquals(false, equalp.apply(environment, list4));
		assertEquals(true, equalp.apply(environment, list5));
	}
}
