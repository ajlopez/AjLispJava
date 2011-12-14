package com.ajlopez.ajlisp;

public class List {
	private Object first;
	private Object rest;
	
	public List(Object first)
	{
		this(first, null);
	}
	
	public List(Object first, Object rest)
	{
		this.first = first;
		this.rest = rest;
	}
	
	public Object first()
	{
		return this.first;
	}
	
	public Object rest()
	{
		return this.rest;
	}
}
