package com.ajlopez.ajlisp.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.List;

public class ParserTests {

	@Test
	public void parseAtom() throws IOException, ParseException {
		Parser parser = new Parser("a");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof Atom);
		
		Atom atom = (Atom) result;
		assertEquals("a", atom.getName());
	}

	@Test
	public void parseSimpleList() throws IOException, ParseException {
		Parser parser = new Parser("(a)");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		
		List list = (List)result;
		
		Object first = list.first();
		
		assertNotNull(first);
		assertTrue(first instanceof Atom);
		assertEquals("a", ((Atom)first).getName());
	}
}

