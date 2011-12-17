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

public class ClosureTests {


	@Test
	public void simpleEvaluate() throws IOException, ParseException, LexerException {
		Object names = (new Parser("(a)")).parseExpression();
		List body = (List)(new Parser("((cons a b))")).parseExpression();
		Bind bind = new Bind(names, body);
		List arguments = (List)(new Parser("(1 2)")).parseExpression();

		Environment environment = new Environment();
		environment.setValue("b", 2);
		environment.setValue("cons", Cons.getInstance());
		Environment closureenv = new Environment(environment);
		closureenv.setValue("b", 3);
		
		Closure closure = new Closure(closureenv, bind);
		
		Object result = closure.evaluate(environment, arguments);
		
		assertEquals(2, environment.getValue("b"));
		assertEquals(3, closureenv.getValue("b"));
		assertEquals("(1 . 3)", Machine.printString(result));
	}

}
