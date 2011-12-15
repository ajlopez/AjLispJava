package com.ajlopez.ajlisp.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LexerTests {

	@Test
	public void getName() throws IOException {
		Lexer lexer = new Lexer("foo");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("foo", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}

}
