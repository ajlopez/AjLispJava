package com.ajlopez.ajlisp.parser;

import java.io.IOException;
import java.util.Stack;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.List;

public class Parser {
	private Lexer lexer;
	private Stack<Token> tokens = new Stack<Token>();
	
	public Parser(String string) {
		this.lexer = new Lexer(string);
	}

	public Object parseExpression() throws IOException, ParseException {
		Token token = this.nextToken();
		
		if (token == null)
			return null;
		
		switch (token.getType())
		{
			case NAME:
				return new Atom(token.getValue());
			case SEPARATOR:
				if (token.getValue().equals("("))
					return this.parseList();
		}
		
		throw new ParseException("Unexpected Token");
	}
	
	private Object parseList() throws IOException, ParseException
	{
		Token token = this.nextToken();
		Stack<Object> expressions = new Stack<Object>();		
		
		while (!(token.getType() == TokenType.SEPARATOR && token.getValue().equals(")")))
		{
			this.pushToken(token);
			expressions.push(this.parseExpression());
			token = this.nextToken();
		}
		
		Object result = null;
		
		while (!expressions.empty())
			result = new List(expressions.pop(), result);
		
		return result;
	}
	
	private Token nextToken() throws IOException
	{
		if (!this.tokens.empty())
			return this.tokens.pop();
		
		return this.lexer.nextToken();
	}
	
	private void pushToken(Token token)
	{
		this.tokens.push(token);
	}
}

