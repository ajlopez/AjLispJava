package com.ajlopez.ajlisp.forms;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.primitives.Do;

public class Closure implements IForm {
	private Environment closure;
	private IForm form;
	
	public Closure(Environment closure, IForm form) {
		this.closure = closure;
		this.form = form;
	}

	public Object evaluate(Environment environment, List arguments) {
		return this.form.evaluate(this.closure, arguments);
	}

}
