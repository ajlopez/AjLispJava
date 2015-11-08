package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class ListTest {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		List body = (List)(new Parser("(1 2 3)")).parseExpression();
		com.ajlopez.ajlisp.primitives.List prim = com.ajlopez.ajlisp.primitives.List.getInstance();
		Object result = prim.evaluate(environment, body);
		
		assertEquals("(1 2 3)", Machine.printString(result));
	}
	
	@Test
	public void listEquality() throws IOException, ParseException, LexerException
	{
		List list = (List)(new Parser("(a b (c d))")).parseExpression();
		List list2 = (List)(new Parser("(a b (c d))")).parseExpression();
		List list3 = (List)(new Parser("(a c d)")).parseExpression();
		
		assertEquals(list, list2);
		assertEquals(list.hashCode(), list2.hashCode());
		assertFalse(list.equals(list3));
		assertFalse(list3.equals(list));
	}
}
