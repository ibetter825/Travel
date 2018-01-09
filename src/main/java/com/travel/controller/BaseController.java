package com.travel.controller;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.travel.bean.constant.WebConstant;
import com.travel.context.SessionContextHolder;

@RestController
public class BaseController {
	@Autowired  
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	
	/**
	 * 获取当前登录的用户
	 * @return
	 */
	/*public TkUser getCurrentUser(){
		return SessionContextHolder.getCurrentUser();
	}*/
	/**
	 * 获取系统根目录
	 * @return
	 */
	public String getContextPath(String path){
		return request.getServletContext().getRealPath(path);
	}
	/**
	 * 添加request请求属性
	 * @param key
	 * @param val
	 */
	public void setAttr(String key, Object val){
		request.setAttribute(key, val);
	}
	/**
	 * 获取request请求属性
	 * @param key
	 * @return
	 */
	public Object getAttr(String key){
		return request.getAttribute(key);
	}
	/**
	 * 向前端写出json
	 * @param data
	 * @throws IOException 
	 */
	public void renderJson(Object data) throws IOException{
		ServletOutputStream outer = null;
		try {
			response.reset();
			outer = response.getOutputStream();
			response.setStatus(HttpStatus.OK.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(WebConstant.DEFAULT_ENCODING); //避免乱码
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			String res = JSON.toJSONString(data);
			outer.write(res.getBytes(WebConstant.DEFAULT_ENCODING));
			outer.flush();
		} finally {
			if(outer != null) outer.close();
		}
	}
}
