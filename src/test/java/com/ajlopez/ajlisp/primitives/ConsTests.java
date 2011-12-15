package com.ajlopez.ajlisp.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.List;

public class ConsTests {

	@Test
	public void applySimple() {
		Cons cons = new Cons();
		Environment environment = new Environment();
		List list = new List("bar");
		
		Object result = cons.apply(environment, new List("foo",list));
		
		assertNotNull(result);
		assertTrue(result instanceof List);
		assertEquals("foo", ((List)result).first());
	}

}
