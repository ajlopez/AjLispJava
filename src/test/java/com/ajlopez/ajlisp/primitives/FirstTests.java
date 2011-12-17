package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class FirstTests {

	@Test
	public void applyToSimpleList() {
		First first = First.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		
		assertEquals(a, first.apply(environment, new List(list)));
	}

	@Test
	public void evaluateSimpleList() {
		First first = First.getInstance();
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		Atom b = new Atom("b");
		environment.setValue("b", list);
		
		assertEquals(a, first.evaluate(environment, new List(b)));
	}
}
