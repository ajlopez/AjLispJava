package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.forms.MClosure;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class MLambdaTests {

	@Test
	public void evaluateCons() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		List arguments = (List)(new Parser("((a b) (cons a b))")).parseExpression();
		MLambda lambda = MLambda.getInstance();
		Object result = lambda.evaluate(environment, arguments);
		
		assertTrue(result instanceof MClosure);

		arguments = (List)(new Parser("(cons (1 2))")).parseExpression();
		
		MClosure closure = (MClosure)result;
		result = closure.evaluate(environment, arguments);
		assertEquals("(1 . 2)", Machine.printString(result));
	}

	@Test
	public void evaluateQuote() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		environment.setValue("quote", Quote.getInstance());
		List arguments = (List)(new Parser("((a b) (cons a b))")).parseExpression();
		MLambda lambda = MLambda.getInstance();
		Object result = lambda.evaluate(environment, arguments);
		
		assertTrue(result instanceof MClosure);

		arguments = (List)(new Parser("(quote ((1 2)))")).parseExpression();
		
		MClosure closure = (MClosure)result;
		result = closure.evaluate(environment, arguments);
		assertEquals("(1 2)", Machine.printString(result));
	}
}
