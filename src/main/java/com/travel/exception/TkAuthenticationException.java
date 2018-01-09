package com.travel.exception;

/**
 * 权限异常
 * @author user
 *
 */
public class TkAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TkAuthenticationException(String msg) {
		super(msg);
	}
	
	public TkAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
