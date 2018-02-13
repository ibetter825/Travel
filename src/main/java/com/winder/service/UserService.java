package com.winder.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.winder.bean.entity.User;
import com.winder.bean.form.LoginForm;

public interface UserService {
	/**
	 * 根据主键查询后台用户
	 * @param userId
	 * @return
	 */
	public User queryById(Integer userId);
	/**
	 * 登录认证
	 * 返回错误信息
	 * 返回空表示登录成功
	 * @param form
	 * @return
	 */
	public String valiLoginUser(HttpServletRequest request, HttpServletResponse response, LoginForm form);
}
