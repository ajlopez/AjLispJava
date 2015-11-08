package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class NilpTest {

	@Test
	public void simpleEvaluate() {
		Nilp nilp = Nilp.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);

		assertEquals(true, nilp.apply(environment, null));
		assertEquals(true, nilp.evaluate(environment, list));
	}
}
