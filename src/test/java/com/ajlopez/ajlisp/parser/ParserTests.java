package com.ajlopez.ajlisp.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.List;

public class ParserTests {

	@Test
	public void parseAtom() throws IOException, ParseException, LexerException {
		Parser parser = new Parser("a");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof Atom);
		
		Atom atom = (Atom) result;
		assertEquals("a", atom.getName());
	}

	@Test
	public void parseSimpleList() throws IOException, ParseException, LexerException {
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

	@Test
	public void parseNestedList() throws IOException, ParseException, LexerException {
		Parser parser = new Parser("(a (b c))");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		
		List list = (List)result;
		
		Object first = list.first();
		
		assertNotNull(first);
		assertTrue(first instanceof Atom);
		assertEquals("a", ((Atom)first).getName());
		
		assertNotNull(list.rest());
		assertTrue(list.rest() instanceof List);
		
		list = (List)list.rest();		
		first = list.first();
		
		assertNotNull(first);
		assertTrue(first instanceof List);
		
		list = (List)first;
		first = list.first();
		
		assertNotNull(first);
		assertTrue(first instanceof Atom);
		assertEquals("b", ((Atom)first).getName());
		
		assertNotNull(list.rest());
		assertTrue(list.rest() instanceof List);
		
		list = (List)list.rest();		
		first = list.first();
		
		assertNotNull(first);
		assertTrue(first instanceof Atom);
		assertEquals("c", ((Atom)first).getName());
	}

	@Test
	public void parseNestedListMixedValues() throws IOException, ParseException, LexerException {
		Parser parser = new Parser("(a (123 \"foo\") c d)");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		
		List list = (List)result;
		
		assertEquals("(a (123 \"foo\") c d)", list.printString());
	}

	@Test
	public void parseDottedList() throws IOException, ParseException, LexerException {
		Parser parser = new Parser("(a . b)");
		
		Object result = parser.parseExpression();
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		
		List list = (List)result;
		Object first = list.first();
		Object rest = list.rest();
		
		assertNotNull(first);
		assertTrue(first instanceof Atom);
		assertEquals("a", ((Atom)first).getName());
		
		assertNotNull(rest);
		assertTrue(rest instanceof Atom);
		assertEquals("b", ((Atom)rest).getName());
	}
	
	@Test
	public void parseNil() throws IOException, ParseException, LexerException {
		Parser parser = new Parser("nil");
		assertNull(parser.parseExpression());
	}
}

