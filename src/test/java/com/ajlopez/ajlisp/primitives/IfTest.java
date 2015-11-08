package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class IfTest {

	@Test
	public void simpleEvaluateTrue() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		List body = (List)(new Parser("(true (define a 1) (define b 2) (define c 3))")).parseExpression();
		If ifprim = If.getInstance();
		Object result = ifprim.evaluate(environment, body);
		
		assertEquals(1, result);
		assertEquals(1, environment.getValue("a"));
		assertNull(environment.getValue("b"));
		assertNull(environment.getValue("c"));
	}

	@Test
	public void simpleEvaluateFalse() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		List body = (List)(new Parser("(false (define a 1) (define b 2) (define c 3))")).parseExpression();
		If ifprim = If.getInstance();
		Object result = ifprim.evaluate(environment, body);
		
		assertEquals(3, result);
		assertNull(environment.getValue("a"));
		assertEquals(2, environment.getValue("b"));
		assertEquals(3, environment.getValue("c"));
	}
}
