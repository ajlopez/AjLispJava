package com.ajlopez.ajlisp.parser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LexerTests {

	@Test
	public void getName() throws IOException, LexerException {
		Lexer lexer = new Lexer("foo");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("foo", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}


	@Test
	public void getNameWithSpaces() throws IOException, LexerException {
		Lexer lexer = new Lexer("  foo  ");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("foo", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getNameWithSpecialChar() throws IOException, LexerException {
		Lexer lexer = new Lexer("nil?");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("nil?", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getParenthesis() throws IOException, LexerException {
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

	@Test
	public void getSimpleList() throws IOException, LexerException {
		Lexer lexer = new Lexer("(a)");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("(", token.getValue());
		assertEquals(TokenType.SEPARATOR, token.getType());
		
		token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("a", token.getValue());
		assertEquals(TokenType.NAME, token.getType());
		
		token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals(")", token.getValue());
		assertEquals(TokenType.SEPARATOR, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getString() throws IOException, LexerException {
		Lexer lexer = new Lexer("\"foo\"");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("foo", token.getValue());
		assertEquals(TokenType.STRING, token.getType());
		
		assertNull(lexer.nextToken());
	}

	@Test
	public void getInteger() throws IOException, LexerException {
		Lexer lexer = new Lexer("123");
		
		Token token = lexer.nextToken();
		
		assertNotNull(token);
		assertEquals("123", token.getValue());
		assertEquals(TokenType.INTEGER, token.getType());
		
		assertNull(lexer.nextToken());
	}
}
