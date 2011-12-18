package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class LetTests {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("define", Define.getInstance());
		List arguments = (List)(new Parser("(((a 1) (b 2)) (define one a) (define two b))")).parseExpression();
		Let let = Let.getInstance();
		Object result = let.evaluate(environment, arguments);
		
		assertEquals(2, result);
		
		assertEquals(1, environment.getValue("one"));
		assertEquals(2, environment.getValue("two"));
		assertNull(environment.getValue("a"));
		assertNull(environment.getValue("b"));
	}

}
