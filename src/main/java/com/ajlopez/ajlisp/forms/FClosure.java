package com.ajlopez.ajlisp.forms;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class FClosure implements IForm {
	private Environment closure;
	private IForm form;
	
	public FClosure(Environment closure, IForm form) {
		this.closure = closure;
		this.form = form;
	}

	public Object evaluate(Environment environment, List arguments) {
		return this.form.evaluate(this.closure, arguments);
	}
}
