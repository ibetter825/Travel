package com.winder.bean.constant;

public class AuthConstant {
	/**
	 * cookie中的sessionid名
	 */
	public final static String COOKIE_USER_INFO = "TKSESSIONINFO";
	/**
	 * 验证码cookie名
	 */
	public final static String COOKIE_CAPTCHA_NAME = "TKSIGNTOKEN";
	/**
	 * 登录限制过期时间
	 * 12小时
	 */
	public final static int LOGIN_ERR_STOP_EXPIRES = 1000 * 60 * 60 * 12; 
	/**
	 * 验证码cookie path
	 */
	public final static String COOKIE_CAPTCHA_PATH = "/sign";
	/**
	 * 解密cookie时使用的默认字符串
	 */
	public final static String COOKIE_SESSION_SALT = "__TKTICK_YT__";
	/**
	 * 解密cookie 验证码 时使用的默认字符串
	 */
	public final static String COOKIE_CAPTCHA_SALT = "__TKTICK_CAPTCHA__";
	/**
	 * cookie的有效期
	 */
	public final static int COOKIE_VALIDITY_SECONDS = 1209600;//14天
	/**
	 * 登录成功后存默认跳转页面
	 */
	public final static String LOGIN_SUCCESS_DEFAULT_TARGET_URL = "/";
	/**
	 * 登录失败后默认跳转的页面
	 */
	public final static String LOGIN_FAILURE_DEFAULT_TARGET_URL = "/sign/in.html";
	/**
	 * 登出URL地址
	 */
	public final static String LOGOUT_PAGE_URL = "/sign/out.html";
	/**
	 * 用户不存在
	 */
	public final static String USER_NOT_FOUND_MSG = "用户不存在";
	/**
	 * 需要登录
	 */
	public final static String USER_NOT_LOGIN = "请先登录";
	/**
	 * 用户名或者密码错误
	 */
	public final static String WRONG_LOGIN_MSG = "用户名或者密码错误";
	/**
	 * 账户被锁
	 */
	public final static String USER_LOCKED_MSG = "账户已被锁定";
	/**
	 * 账户被禁用
	 */
	public final static String USER_DISABLED_MSG = "账户被禁用";
	/**
	 * 密码过期
	 */
	public final static String TIME_OUT_PASSWORD_MSG = "密码过期";
	/**
	 * 登录过期
	 */
	public final static String TIME_OUT_SESSION_MSG = "登录过期";
	/**
	 * 验证码错误
	 */
	public final static String WRONG_CAPTCHA_MSG = "验证码错误";
	/**
	 * 登录表单出错
	 */
	public final static String FORM_VALI_FAIL_MSG = "表单验证失败";
	/**
	 * 表单验证失败返回给前端的字段名
	 */
	public final static String FORM_VALI_FAIL_NAME = "errors";
	/**
	 * 登录账号出错
	 */
	public final static String WRONG_USER_ACCOUNT_MSG = "只能使用手机或者邮箱登录";
	/**
	 * 后台路径根目录
	 */
	public final static String AUTH_ADMIN_ROOR_PATH = "/admin";
}
