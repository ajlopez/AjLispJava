package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class AtompTest {

	@Test
	public void simpleEvaluate() {
		Atomp atomp = Atomp.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);

		assertEquals(false, atomp.apply(environment, null));
		assertEquals(true, atomp.apply(environment, list));
		assertEquals(false, atomp.apply(environment, new List(list)));
	}
}
