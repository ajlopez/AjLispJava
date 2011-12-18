package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class ListpTests {

	@Test
	public void simpleEvaluate() {
		Listp listp = Listp.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);

		assertEquals(false, listp.apply(environment, null));
		assertEquals(false, listp.apply(environment, list));
		assertEquals(true, listp.apply(environment, new List(list)));
	}
}
