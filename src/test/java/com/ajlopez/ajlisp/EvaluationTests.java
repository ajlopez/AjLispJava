package com.ajlopez.ajlisp;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ajlopez.ajlisp.parser.ParseException;
import com.ajlopez.ajlisp.parser.Parser;

public class EvaluationTests {

	@Test
	public void evaluateAtom() throws IOException, ParseException {
		Machine machine = new Machine();
		machine.getEnvironment().setValue("one", 1);
		Parser parser = new Parser("one");
		
		Object result = machine.evaluate(parser.parseExpression());
		
		assertEquals(1, result);
	}
}
