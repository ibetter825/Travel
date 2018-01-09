package com.travel.exception;

import java.util.Map;

/**
 * 表单验证异常
 * @author user
 *
 */
public class TkValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Map<String, String> error; //错误信息
	
	public TkValidationException(String msg) {
		super(msg);
	}
	
	public TkValidationException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public TkValidationException(String msg, Map<String, String> error) {
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
