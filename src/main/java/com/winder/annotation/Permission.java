package com.winder.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限,需要登录后操作
 * @author user
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
	/**
	 * 该方法需要的权限值
	 * 在后台登录中使用
	 * @return
	 */
	String value() default "";
}
