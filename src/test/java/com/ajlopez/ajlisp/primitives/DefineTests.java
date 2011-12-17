package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class DefineTests {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", new Define());
		List list = (List)(new Parser("(define a 1)")).parseExpression();
		Object result = list.evaluate(environment);
		
		assertEquals(1, result);
		assertEquals(1, environment.getValue("a"));
	}

	@Test
	public void simpleEvaluateInChildEnvironment() throws IOException, ParseException, LexerException {
		Environment parent = new Environment();
		Environment environment = new Environment(parent);
		environment.setValue("define", new Define());
		List list = (List)(new Parser("(define a 1)")).parseExpression();
		Object result = list.evaluate(environment);
		
		assertEquals(1, result);
		assertEquals(1, environment.getValue("a"));
		assertEquals(1, parent.getValue("a"));
	}
}
