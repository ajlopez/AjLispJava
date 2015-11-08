package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class RestTest {

	@Test
	public void applyToSimpleList() {
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		
		assertNull(Rest.getInstance().apply(environment, new List(list)));
	}

	@Test
	public void evaluateSimpleList() {
		Environment environment = new Environment();
		Atom a = new Atom("a");
		List list = new List(a);
		Atom b = new Atom("b");
		environment.setValue("b", list);
		
		assertNull(Rest.getInstance().evaluate(environment, new List(b)));
	}
}
