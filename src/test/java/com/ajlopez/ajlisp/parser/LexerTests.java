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


	@Test
	public void getNameWithSpaces() throws IOException {
		Lexer lexer = new Lexer("  foo  ");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("foo", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getNameWithSpecialChar() throws IOException {
		Lexer lexer = new Lexer("nil?");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("nil?", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getParenthesis() throws IOException {
		Lexer lexer = new Lexer("()");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("(", token.getValue());
		assertEquals(TokenType.SEPARATOR, token.getType());
		
		token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals(")", token.getValue());
		assertEquals(TokenType.SEPARATOR, token.getType());
		
		assertNull(lexer.nextToken());
	}
}
