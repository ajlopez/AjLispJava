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
		
		assertEquals(1, evaluateExpression("a"));
		assertEquals(2, evaluateExpression("b"));
		assertEquals(3, evaluateExpression("c"));
	}
	
	@Test
	public void evaluateCons() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define a (quote one))");
		evaluateExpression("(define b (quote two))");
		assertEquals("(one . two)", evaluateExpressionAsString("(cons a b)"));
	}
	
	@Test
	public void evaluateFirst() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define a (quote (b c)))");
		assertEquals("b", evaluateExpressionAsString("(first a)"));
	}
	
	@Test
	public void evaluateRest() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define a (quote (b c)))");
		assertEquals("(c)", evaluateExpressionAsString("(rest a)"));
	}
	
	private Object evaluateExpression(String text) throws IOException, ParseException, LexerException
	{
		Parser parser = new Parser(text);
		Object expr = parser.parseExpression();
		return this.machine.evaluate(expr);
	}
	
	private String evaluateExpressionAsString(String text) throws IOException, ParseException, LexerException {
		return Machine.printString(this.evaluateExpression(text));
	}
}
