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

public class DoTests {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("cons", new Cons());
		List body = (List)(new Parser("((cons 1 2) (cons 3 4) (cons 5 6))")).parseExpression();
		Do doprim = new Do();
		Object result = doprim.evaluate(environment, body);
		assertEquals("(5 . 6)", Machine.printString(result));
	}
}
