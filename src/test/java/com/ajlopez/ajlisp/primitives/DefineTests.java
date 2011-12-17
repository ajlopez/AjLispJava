package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.forms.Closure;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class DefineTests {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		List list = (List)(new Parser("(define a 1)")).parseExpression();
		Object result = list.evaluate(environment);
		
		assertEquals(1, result);
		assertEquals(1, environment.getValue("a"));
	}

	@Test
	public void simpleEvaluateInChildEnvironment() throws IOException, ParseException, LexerException {
		Environment parent = new Environment();
		Environment environment = new Environment(parent);
		environment.setValue("define", Define.getInstance());
		List list = (List)(new Parser("(define a 1)")).parseExpression();
		Object result = list.evaluate(environment);
		
		assertEquals(1, result);
		assertEquals(1, environment.getValue("a"));
		assertEquals(1, parent.getValue("a"));
	}

	@Test
	public void defineForm() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		environment.setValue("cons", Cons.getInstance());
		environment.setValue("quote", Quote.getInstance());
		
		List list = (List)(new Parser("(define mycons (a b) (cons a b))")).parseExpression();
		Object result = list.evaluate(environment);

		assertTrue(result instanceof Closure);
		
		Closure closure = (Closure)result;
		
		List arguments = (List)(new Parser("(1 (quote (2 3)))")).parseExpression();
		result = closure.evaluate(environment, arguments);
		
		assertEquals("(1 2 3)", Machine.printString(result));
	}
}
