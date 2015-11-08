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

public class LambdaTest {

	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		environment.setValue("quote", Quote.getInstance());
		List arguments = (List)(new Parser("((a b) (cons a b))")).parseExpression();
		Lambda lambda = Lambda.getInstance();
		Object result = lambda.evaluate(environment, arguments);
		
		assertTrue(result instanceof Closure);

		arguments = (List)(new Parser("((quote a) (quote (b c)))")).parseExpression();
		
		Closure closure = (Closure)result;
		result = closure.evaluate(environment, arguments);
		assertEquals("(a b c)", Machine.printString(result));
	}

}
