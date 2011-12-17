package com.ajlopez.ajlisp.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

public class Lexer {
	private static String separators = "()";
	private static String operators = ".";
	private Reader reader;
	private Stack<Character> characters = new Stack<Character>();

	public Lexer(String text) {
		this.reader = new StringReader(text);
	}

	public Token nextToken() throws IOException, LexerException  {
		int ich;
		
		// TODO review the use of stack/queue
		if (this.characters.isEmpty())		
			ich = this.reader.read();
		else
			ich = this.characters.pop();
		
		while (ich != -1 && Character.isSpaceChar((char) ich))
			ich = this.reader.read();
		
		if (ich == -1)
			return null;
		
		char ch = (char)ich;
		
		if (separators.indexOf(ch) >= 0)
			return new Token("" + ch, TokenType.SEPARATOR);

		if (operators.indexOf(ch) >= 0)
			return new Token("" + ch, TokenType.OPERATOR);
		
		if (ch == '"')
			return nextString();
		
		String value = "";
		
		while (ich != -1 && !Character.isSpaceChar((char) ich) && !(separators.indexOf((char)ich)>=0)) {
			value += (char)ich;
			
			ich = this.reader.read();
		}
		
		if (ich != -1)
			this.characters.push((char)ich);
		
		return new Token(value, TokenType.NAME);
	}
	
	private Token nextString() throws LexerException, IOException {
		String value = "";
		int ich;
		
		for (ich = this.reader.read(); ich != -1 && ((char)ich)!='"'; ich = this.reader.read())
			value += (char)ich;
		
		if (ich == -1)
			throw new LexerException("Unexpected end of input");
		
		return new Token(value, TokenType.STRING);
	}
}
