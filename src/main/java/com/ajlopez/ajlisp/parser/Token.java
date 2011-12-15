package com.ajlopez.ajlisp.parser;

public class Token {
	private String value;
	private TokenType type;
	
	public Token(String value, TokenType type)
	{
		this.value = value;
		this.type = type;
	}
	
	public String getValue() {
		return this.value;
	}

	public TokenType getType() {
		return this.type;
	}

}
