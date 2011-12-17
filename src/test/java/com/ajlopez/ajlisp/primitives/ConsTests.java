package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class ConsTests {

	@Test
	public void applySimple() {
		Environment environment = new Environment();
		List list = new List("bar");
		
		Object result = Cons.getInstance().apply(environment, new List("foo",list));
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		
		List lresult = (List) result;
		assertEquals("foo", lresult.first());
		
		Object rest = lresult.rest();
		
		assertNotNull(rest);
		
		assertEquals("bar", rest);
	}
}
