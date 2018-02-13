package com.winder.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.winder.annotation.Permission;
import com.winder.bean.entity.User;
import com.winder.context.SessionContextHolder;
import com.winder.controller.BaseController;
import com.winder.exception.MyAuthenticationException;

/**
 * 使用 AOP检查是否登录
 * @author user
 *
 */
@Aspect
@Component
public class AuthorityInterceptor {
	
	@Before("@annotation(permission)")//会拦截注解Permission的方法，否则不拦截
    public void authority(JoinPoint point, Permission permission) throws Throwable {
		String value = permission.value();
		if(StringUtils.isNotEmpty(value)){
			System.err.println("----后台操作需要的权限");
		}
		
        User user = SessionContextHolder.getCurrentUser();
        if(user == null){
        	System.err.println("用户尚未登录");
        	throw new MyAuthenticationException("未登录", BaseController.VIEW_ADMIN_BASE_ROUTE + "login");
        }
    }
}
