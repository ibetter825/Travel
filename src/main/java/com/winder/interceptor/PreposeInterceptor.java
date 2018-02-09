package com.winder.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winder.bean.constant.AuthConstant;
import com.winder.context.SessionContextHolder;
import com.winder.utils.DateUtil;
import com.winder.utils.Md5Util;
import com.winder.utils.SecretUtil;
import com.winder.utils.WebUtil;

/**
 * 前置拦截器
 * 每次从cookie中取出用户登录信息，存入到SessionContextHolder中
 * @author user
 *
 */
public class PreposeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.err.println(request.getRequestURI());
		System.err.println("进入到前置拦截器中");
		//如果存在cookie SESSIONID 则表示已经登录   就从缓存中取出数据
		//如果缓存中没有，则从数据库中查询
		/*Cookie cookie =	WebUtil.getCookieByName(request, AuthConstant.COOKIE_USER_INFO);
		if(cookie != null){
			String userLoginInfo = SecretUtil.decrypt(cookie.getValue());
			//userId+"@"+time+"@"+maxAge+"@"+Md5Util.md5(userId+"@"+pwd+"@"+salt+"@"+time+"@"+maxAge
			String[] userValues = userLoginInfo.split("@");
			if(StringUtils.isNotEmpty(userLoginInfo) && userValues.length > 3){
				Integer userId = Integer.valueOf(userValues[0]);
				long otime = Long.valueOf(userValues[1]);//如果otime==0的话，不手动判断过期时间，不是0的话，需要重新计算剩余cookie剩余时间
				int maxAge = Integer.valueOf(userValues[2]);
				long rtime = DateUtil.getDateByTime();
				String cookieUserinfo = userValues[3];
				
				TkUser user = userService.queryById(userId);
				if(user != null){
					String dbPwd = user.getUserPwd();
					String dbSalt = user.getUserSalt();
					String dbUserInfo = Md5Util.md5(userId + "@" + dbPwd + "@" + dbSalt + "@" + otime + "@" + maxAge);
					if(cookieUserinfo.equals(dbUserInfo)){//已登录的用户
						SessionContextHolder.setCurrentUser(user);
						String newCookieUserInfo = SecretUtil.encrypt(userId + "@" + rtime + "@" + maxAge + "@" + Md5Util.md5(userId + "@" + dbPwd + "@" + dbSalt + "@" + rtime + "@" + maxAge));
						if(maxAge != -1)
							maxAge = (int) (maxAge - (rtime - otime) / 1000);//maxAge-(当前时间戳-老时间戳)/1000得到秒数
						if(WebUtil.getBrowserName(request).equals("webkit"))
							WebUtil.addCookie(response, null, null, true, AuthConstant.COOKIE_USER_INFO, newCookieUserInfo, maxAge);
						else{
							long expires = DateUtil.getDateByTime() + maxAge;
							WebUtil.addCookie(response, null, null, true, AuthConstant.COOKIE_USER_INFO, newCookieUserInfo, expires, maxAge);
						}
					}
				}
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
