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

public class DoTest {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		List body = (List)(new Parser("((cons 1 2) (cons 3 4) (cons 5 6))")).parseExpression();
		Do doprim = Do.getInstance();
		Object result = doprim.evaluate(environment, body);
		assertEquals("(5 . 6)", Machine.printString(result));
	}

	@Test
	public void simpleDefines() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		List body = (List)(new Parser("((define a 1) (define b 2) (define c 3))")).parseExpression();
		Do doprim = Do.getInstance();
		Object result = doprim.evaluate(environment, body);
		assertEquals(3, result);
		assertEquals(1, environment.getValue("a"));
		assertEquals(2, environment.getValue("b"));
		assertEquals(3, environment.getValue("c"));
	}
}
