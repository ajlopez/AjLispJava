package com.ajlopez.ajlisp.parser;

import java.io.IOException;
import java.util.Stack;

import com.ajlopez.ajlisp.Atom;
import com.ajlopez.ajlisp.List;

public class Parser {
	private static Atom quoteAtom = new Atom("quote");
	
	private Lexer lexer;
	private Stack<Token> tokens = new Stack<Token>();
	
	public Parser(String string) {
		this(new Lexer(string));
	}
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public Object parseExpression() throws IOException, ParseException, LexerException {
		Token token = this.nextToken();
		
		if (token == null)
			return null;
		
		switch (token.getType())
		{
			case NAME:
				if (token.getValue().equals("nil"))
					return null;
				if (token.getValue().equals("false"))
					return false;
				if (token.getValue().equals("true"))
					return true;
				return new Atom(token.getValue());
			case SEPARATOR:
				if (token.getValue().equals("("))
					return this.parseList();
			case STRING:
				return token.getValue();
			case INTEGER:
				return Integer.parseInt(token.getValue());
			case OPERATOR:
				if (token.getValue().equals("'")) {
					return new List(quoteAtom, new List(this.parseExpression()));
				}
		}
		
		throw new ParseException("Unexpected Token");
	}
	
	private Object parseList() throws IOException, ParseException, LexerException
	{
		Token token = this.nextToken();
		Stack<Object> expressions = new Stack<Object>();	
		Object result = null;
		
		while (!(token.getType() == TokenType.SEPARATOR && token.getValue().equals(")")))
		{
			this.pushToken(token);
			expressions.push(this.parseExpression());
			token = this.nextToken();
			
			if (token.getType() == TokenType.OPERATOR && token.getValue().equals(".")) {
				result = this.parseExpression();
				break;
			}
		}
				
		while (!expressions.empty())
			result = new List(expressions.pop(), result);
		
		return result;
	}
	
	private Token nextToken() throws IOException, LexerException
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

