package com.ajlopez.ajlisp.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Lexer {
	private Reader reader;

	public Lexer(String text) {
		this.reader = new StringReader(text);
	}

	public Token nextToken() throws IOException  {
		int ich = this.reader.read();
		
		while (ich != -1 && Character.isSpaceChar((char) ich))
			ich = this.reader.read();
		
		if (ich == -1)
			return null;
		
		char ch = (char)ich;
		
		if (ch == '(' || ch ==')')
			return new Token("" + ch, TokenType.SEPARATOR);
		
		String value = "";
		
		while (ich != -1 && !Character.isSpaceChar((char) ich)) {
			value += (char)ich;
			
			ich = this.reader.read();
		}
		
		return new Token(value, TokenType.NAME);
	}
}
