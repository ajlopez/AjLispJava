package com.ajlopez.ajlisp.forms;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;
import com.ajlopez.ajlisp.parser.LexerException;
import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;
import com.ajlopez.ajlisp.primitives.Cons;

public class FClosureTests {


	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Object names = (new Parser("(a b)")).parseExpression();
		List body = (List)(new Parser("((cons a b))")).parseExpression();
		Bind bind = new Bind(names, body);
		List arguments = (List)(new Parser("(a (b c))")).parseExpression();

		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		
		FClosure closure = new FClosure(environment, bind);
		
		Object result = closure.evaluate(environment, arguments);
		
		assertEquals("(a b c)", Machine.printString(result));
	}

}
