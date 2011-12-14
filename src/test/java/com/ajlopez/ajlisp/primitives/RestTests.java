package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class RestTests {

	@Test
	public void applyToSimpleList() {
		Rest rest = new Rest();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		
		assertNull(rest.apply(environment, new List(list)));
	}

	@Test
	public void evaluateSimpleList() {
		Rest rest = new Rest();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		Atom b = new Atom("b");
		environment.setValue("b", list);
		
		assertNull(rest.evaluate(environment, new List(b)));
	}
}
