package com.winder.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.winder.bean.constant.AuthConstant;
import com.winder.bean.constant.WebConstant;
import com.winder.bean.enums.ResultMessageEnum;
import com.winder.bean.model.ResultModel;
import com.winder.exception.MyAuthenticationException;
import com.winder.exception.MyValidationException;
import com.winder.utils.WebUtil;

/**
 * 全局异常管理
 * 不会与springboot的BasicErrorController发生冲突
 * 替换掉HandlerExceptionResolver的方式，该方式与springboot中会发生冲突
 * 只能处理异常 500错误等，不能对404等错误进行管理
 * 对404错误需要使用 MyBasicErrorController该类目前只处理404页面的问题，根据路径不同跳转不同目录下的错误页面
 * @author user
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	public static final String DEFAULT_ERROR_PATH = "/web/error";
	
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
    	try {
    		if(WebUtil.isAjax(req))
        		exceptionHandle(resp, e);
    		else{
    	        ModelAndView mav = new ModelAndView();
    	        if(e instanceof MyAuthenticationException){
    	        	String view = ((MyAuthenticationException) e).getView();
    	        	if(StringUtils.isNotEmpty(view))
    	        		mav.setViewName(view);
    	        	else
    	        		mav.setViewName("/web/sign");
    	        }else{
	    	        mav.addObject("exception", e);
	    	        mav.addObject("url", req.getRequestURL());
	    	        mav.setViewName(DEFAULT_ERROR_PATH+"/500");
    	        }
    	        return mav;
    		}
		} finally {
			if(!(e instanceof MyAuthenticationException))
				logger.error("GlobalExceptionHandler.class", e);
		}
        return null;
    }
    
    public void exceptionHandle(HttpServletResponse resp, Exception e) throws IOException{
    	ServletOutputStream outer = null;
    	boolean isTkValiEx = e instanceof MyValidationException;//是否是表单验证错误
		try {
			ResultModel model = null;
			resp.reset();
			outer = resp.getOutputStream();
			if(isTkValiEx){
				resp.setStatus(HttpStatus.OK.value());
				model = new ResultModel(e.getMessage());
				model.getData().put(AuthConstant.FORM_VALI_FAIL_NAME, ((MyValidationException) e).getError());
			}else{
				resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				model = new ResultModel(ResultMessageEnum.SYSTEM_EXCEPTION);
			}
			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
			resp.setCharacterEncoding(WebConstant.DEFAULT_ENCODING); //避免乱码
			resp.setHeader("Cache-Control", "no-cache, must-revalidate");
			String res = JSON.toJSONString(model);
			outer.write(res.getBytes(WebConstant.DEFAULT_ENCODING));
			outer.flush();
		} finally {
			if(outer != null) outer.close();
		}
    }
}
