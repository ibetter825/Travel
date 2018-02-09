package com.winder.interceptor;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.common.collect.Maps;
import com.winder.annotation.Validator;
import com.winder.bean.constant.AuthConstant;
import com.winder.exception.MyValidationException;

/**
 * 使用 AOP检查表单对象是否验证通过
 * 在参数中查找BindingResult对象是否包含errors
 * @author user
 *
 */
@Aspect
@Component
public class ValidateInterceptor {
	
	@Before("@annotation(validator)")//会拦截注解Validator的方法，否则不拦截
    public void authority(JoinPoint point, Validator validator) throws Throwable {
        Object[] orgs = point.getArgs();
        BindingResult binding = null;
        Map<String, String> res = null;
        List<ObjectError> errors = null;
        for (Object org : orgs) {
			if(org instanceof BindingResult){
				binding = (BindingResult) org;
				if(binding.hasErrors()){
					res = Maps.newHashMap();//验证错误结果  feild:message
					errors = binding.getAllErrors();
					for(ObjectError e : errors){
						if(e instanceof FieldError)
							res.put(((FieldError) e).getField(), e.getDefaultMessage());
					}
				}
			}
		}
        if(res != null) throw new MyValidationException(AuthConstant.FORM_VALI_FAIL_MSG, res);
    }
}
