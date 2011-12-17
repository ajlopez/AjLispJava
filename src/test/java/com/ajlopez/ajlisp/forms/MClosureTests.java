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
import com.ajlopez.ajlisp.primitives.Quote;

public class MClosureTests {

	@Test
	public void evaluateCons() throws IOException, ParseException, LexerException {
		Object names = (new Parser("(a b)")).parseExpression();
		List body = (List)(new Parser("((cons a b))")).parseExpression();
		Bind bind = new Bind(names, body);
		List arguments = (List)(new Parser("(cons (1 2))")).parseExpression();

		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		
		MClosure closure = new MClosure(environment, bind);
		
		Object result = closure.evaluate(environment, arguments);
		
		assertEquals("(1 . 2)", Machine.printString(result));
	}

	@Test
	public void evaluateQuote() throws IOException, ParseException, LexerException {
		Object names = (new Parser("(a b)")).parseExpression();
		List body = (List)(new Parser("((cons a b))")).parseExpression();
		Bind bind = new Bind(names, body);
		List arguments = (List)(new Parser("(quote ((1 2)))")).parseExpression();

		Environment environment = new Environment();
		environment.setValue("cons", Cons.getInstance());
		environment.setValue("quote", Quote.getInstance());
		
		MClosure closure = new MClosure(environment, bind);
		
		Object result = closure.evaluate(environment, arguments);
		
		assertEquals("(1 2)", Machine.printString(result));
	}
}
