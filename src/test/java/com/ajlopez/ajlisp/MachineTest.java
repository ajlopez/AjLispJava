package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.primitives.First;

public class MachineTest {

	@Test
	public void evaluateObjects() {
		Environment environment = new Environment();
		
		First first = First.getInstance();
		
		assertEquals(1, Machine.evaluate(environment, 1));
		assertEquals("foo", Machine.evaluate(environment, "foo"));
		assertEquals(first, Machine.evaluate(environment, first));
	}

	@Test
	public void evaluateAtoms() {
		Environment environment = new Environment();
		environment.setValue("one", 1);
		environment.setValue("two", 2);
		
		Atom one = new Atom("one");
		Atom two = new Atom("two");
		
		assertEquals(1, Machine.evaluate(environment, one));
		assertEquals(2, Machine.evaluate(environment, two));
	}
}
