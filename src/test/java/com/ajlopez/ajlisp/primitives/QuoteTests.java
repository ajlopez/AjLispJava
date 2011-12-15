package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class QuoteTests {

	@Test
	public void simpleEvaluate() {
		Quote quote = new Quote();
		Environment environment = new Environment();
		Atom atom = new Atom("a");
		
		Object result = quote.evaluate(environment, new List(atom));
		
		assertNotNull(result);
		assertEquals(atom, result);
	}
}
