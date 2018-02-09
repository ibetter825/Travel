package com.winder.exception;

import java.util.Map;

/**
 * 表单验证异常
 * @author user
 *
 */
public class MyValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Map<String, String> error; //错误信息
	
	public MyValidationException(String msg) {
		super(msg);
	}
	
	public MyValidationException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public MyValidationException(String msg, Map<String, String> error) {
		super(msg);
		this.error = error;
	}

	public Map<String, String> getError() {
		return error;
	}

	public void setError(Map<String, String> error) {
		this.error = error;
	}
}
