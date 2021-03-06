package com.ajlopez.ajlisp.forms;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class Closure implements IForm {
	private Environment closure;
	private Bind form;
	
	public Closure(Environment closure, Bind form) {
		this.closure = closure;
		this.form = form;
	}

	public Object evaluate(Environment environment, List arguments) {
		return this.form.evaluate(this.closure, Machine.evaluateList(environment, arguments));
	}
}
