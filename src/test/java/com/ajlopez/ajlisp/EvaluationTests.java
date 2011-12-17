package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class EvaluationTests {
	private Machine machine;
	
	@Before
	public void setup() {
		this.machine = new Machine();
	}
	
	@Test
	public void evaluateAtom() throws IOException, ParseException, LexerException {
		this.machine.getEnvironment().setValue("one", 1);
		
		Object result = evaluateExpression("one");
		
		assertEquals(1, result);
	}
	
	@Test
	public void evaluateDefines() throws IOException, ParseException, LexerException
	{
		assertEquals(1, evaluateExpression("(define a 1)"));
		assertEquals(2, evaluateExpression("(define b 2)"));
		assertEquals(3, evaluateExpression("(define c 3)"));
	}
	
	private Object evaluateExpression(String text) throws IOException, ParseException, LexerException
	{
		Parser parser = new Parser(text);
		Object expr = parser.parseExpression();
		return this.machine.evaluate(expr);
	}
}
