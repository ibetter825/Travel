package com.winder.exception;

/**
 * 权限异常
 * @author user
 *
 */
public class MyAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 如果出错，告诉错误处理程序应该跳转的页面
	 */
	private String view;
	
	public MyAuthenticationException(String msg) {
		super(msg);
	}
	
	public MyAuthenticationException(String msg, String view) {
		super(msg);
		this.view = view;
	}

	public MyAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
}
