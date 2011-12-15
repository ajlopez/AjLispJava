package com.ajlopez.ajlisp.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;

public class ParserTests {

	@Test
	public void parseAtom() throws IOException {
		Parser parser = new Parser("a");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof Atom);
		
		Atom atom = (Atom) result;
		assertEquals("a", atom.getName());
	}

}
