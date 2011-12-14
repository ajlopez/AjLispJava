package com.ajlopez.ajlisp;

public interface IForm {
	Object evaluate(Environment environment, List arguments);
}
