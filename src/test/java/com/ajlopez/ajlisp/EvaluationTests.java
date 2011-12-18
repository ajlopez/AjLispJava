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
	public void evaluateQuotedList() throws IOException, ParseException, LexerException
	{
		assertEquals("(a b)", evaluateExpressionAsString("(quote (a b))"));
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
	
	@Test
	public void evaluateDo() throws IOException, ParseException, LexerException
	{
		assertEquals(3, evaluateExpression("(do (define a 1) (define b 2) (define c 3))"));
		
		assertEquals(1, evaluateExpression("a"));
		assertEquals(2, evaluateExpression("b"));
		assertEquals(3, evaluateExpression("c"));
	}

	@Test
	public void defineAndEvaluateFCons() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(definef fcons (a b) (cons a b))");
		assertEquals("(a b c)", evaluateExpressionAsString("(fcons a (b c))"));
	}

	@Test
	public void defineAndEvaluateFConsUsingFLambda() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define fcons (flambda (a b) (cons a b)))");
		assertEquals("(a b c)", evaluateExpressionAsString("(fcons a (b c))"));
	}

	@Test
	public void defineAndEvaluateFList() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(definef flist x x)");
		assertEquals("(a b c)", evaluateExpressionAsString("(flist a b c)"));
	}

	@Test
	public void defineAndEvaluateFListUsinfFLambda() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define flist (flambda x x))");
		assertEquals("(a b c)", evaluateExpressionAsString("(flist a b c)"));
	}

	@Test
	public void defineAndEvaluateMyList() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define mylist x x)");
		assertEquals("(a b c)", evaluateExpressionAsString("(mylist (quote a) (quote b) (quote c))"));
	}

	@Test
	public void defineAndEvaluateMyListUsingLambda() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define mylist (lambda x x))");
		assertEquals("(a b c)", evaluateExpressionAsString("(mylist (quote a) (quote b) (quote c))"));
	}

	@Test
	public void defineAndEvaluateMCons() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(definem mcons (a b) (cons a b))");
		assertEquals(1, evaluateExpression("(mcons first ((quote (1 2))))"));
	}

	@Test
	public void defineAndEvaluateMConsUsingMLambda() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define mcons (mlambda (a b) (cons a b)))");
		assertEquals(1, evaluateExpression("(mcons first ((quote (1 2))))"));
	}

	@Test
	public void defineAndEvaluateAppend() throws IOException, ParseException, LexerException
	{
		evaluateExpression("(define append (x y) (if (nil? x) y (cons (first x) (append (rest x) y))))");
		assertEquals("(1 2 3 4)", evaluateExpressionAsString("(append '(1 2) '(3 4))"));
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
