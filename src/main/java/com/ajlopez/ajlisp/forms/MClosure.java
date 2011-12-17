package com.ajlopez.ajlisp.forms;

import com.ajlopez.ajlisp.Environment;
import com.ajlopez.ajlisp.IForm;
import com.ajlopez.ajlisp.List;
import com.ajlopez.ajlisp.Machine;

public class MClosure implements IForm {
	private Environment closure;
	private IForm form;
	
	public MClosure(Environment closure, IForm form) {
		this.closure = closure;
		this.form = form;
	}

	public Object evaluate(Environment environment, List arguments) {
		return Machine.evaluate(environment, this.form.evaluate(this.closure, arguments));
	}
}
