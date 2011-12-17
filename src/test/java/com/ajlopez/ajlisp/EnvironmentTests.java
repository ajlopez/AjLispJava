package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class EnvironmentTests {

	@Test
	public void createAndGetValue() {
		Environment environment = new Environment();
		assertNull(environment.getValue("foo"));
	}

	@Test
	public void setAndGetIntegerValue()	{
		Environment environment = new Environment();
		environment.setValue("one", 1);
		assertEquals(1, environment.getValue("one"));
	}

	@Test
	public void setAndGetValueAtParent() {
		Environment parent = new Environment();
		Environment environment = new Environment(parent);
		parent.setValue("one", 1);
		assertEquals(1, parent.getValue("one"));
		assertEquals(1, environment.getValue("one"));
	}

	@Test
	public void setAndGetValueAtChildEnvironment() {
		Environment parent = new Environment();
		Environment environment = new Environment(parent);
		environment.setValue("one", 1);
		assertNull(parent.getValue("one"));
		assertEquals(1, environment.getValue("one"));
	}

	@Test
	public void setAndGetValueAtChildAndParentEnvironments() {
		Environment parent = new Environment();
		Environment environment = new Environment(parent);
		parent.setValue("one", 2);
		environment.setValue("one", 1);
		assertEquals(2, parent.getValue("one"));
		assertEquals(1, environment.getValue("one"));
	}
	
	@Test
	public void setValues() throws IOException, ParseException, LexerException {
		List names = (List) (new Parser("(a b c)")).parseExpression();
		List values = (List) (new Parser("(1 2 3)")).parseExpression();
		
		Environment environment = new Environment();
		environment.setValues(names, values);
		
		assertEquals(1, environment.getValue("a"));
		assertEquals(2, environment.getValue("b"));
		assertEquals(3, environment.getValue("c"));
	}
}
