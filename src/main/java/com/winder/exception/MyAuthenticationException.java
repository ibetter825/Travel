package com.winder.exception;

/**
 * 权限异常
 * @author user
 *
 */
public class MyAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyAuthenticationException(String msg) {
		super(msg);
	}
	
	public MyAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
