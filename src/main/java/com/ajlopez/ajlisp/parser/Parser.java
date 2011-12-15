package com.ajlopez.ajlisp.parser;

import java.io.IOException;

import com.ajlopez.ajlisp.Atom;

public class Parser {
	private Lexer lexer;
	
	public Parser(String string) {
		this.lexer = new Lexer(string);
	}

	public Object parseExpression() throws IOException {
		Token token = this.nextToken();
		
		if (token == null)
			return null;
		
		return new Atom(token.getValue());
	}
	
	private Token nextToken() throws IOException
	{
		return this.lexer.nextToken();
	}
}
