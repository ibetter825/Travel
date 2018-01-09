package com.travel.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.travel.interceptor.PreposeInterceptor;

/**
 * 配置拦截器
 * @author user
 *
 */
@Configuration
public class InterceptorConfigure extends WebMvcConfigurerAdapter {
	
	/**
	 * 添加此方法后才能在PreposeInterceptor中注入service
	 * http://blog.csdn.net/mjlfto/article/details/65635135
	 * @return
	 */
	@Bean
    PreposeInterceptor preposeInterceptor() {
        return new PreposeInterceptor();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册前置拦截器
		registry.addInterceptor(preposeInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/sign/**","/error/**");
	}
}
