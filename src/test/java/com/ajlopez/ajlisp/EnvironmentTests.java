package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnvironmentTests {

	@Test
	public void createAndGetValue() {
		Environment environment = new Environment();
		assertNull(environment.getValue("foo"));
	}

	@Test
	public void setAndGetIntegerValue()
	{
		Environment environment = new Environment();
		environment.setValue("one", 1);
		assertEquals(1, environment.getValue("one"));
	}
}
