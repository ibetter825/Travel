package com.winder.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winder.bean.constant.AuthConstant;
import com.winder.bean.constant.CacheConstant;
import com.winder.bean.dto.UserDto;
import com.winder.bean.entity.User;
import com.winder.bean.entity.UserInfo;
import com.winder.bean.form.LoginForm;
import com.winder.bean.rq.QueryRq;
import com.winder.dao.mapper.UserInfoMapper;
import com.winder.dao.mapper.UserMapper;
import com.winder.service.UserService;
import com.winder.utils.DateUtil;
import com.winder.utils.Md5Util;
import com.winder.utils.SecretUtil;
import com.winder.utils.WebUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class UserServiceImpl implements UserService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public User queryById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public String valiLoginUser(HttpServletRequest request, HttpServletResponse response, LoginForm form) {
		//判断验证码是否出错
		String captchaString = WebUtil.getCookieValueByName(request, AuthConstant.COOKIE_CAPTCHA_NAME);
		if(!Md5Util.md5(form.getCaptcha().toUpperCase() + AuthConstant.COOKIE_CAPTCHA_SALT).equals(captchaString))
			return AuthConstant.WRONG_CAPTCHA_MSG;//验证码不对
		WebUtil.addCookie(response, null, AuthConstant.COOKIE_CAPTCHA_PATH, true, AuthConstant.COOKIE_CAPTCHA_NAME, null, 0);
		String res = null;
		UserDto user = null;
		QueryRq rq = new QueryRq();
		switch (form.getType()) {
			case LoginForm.LOGIN_TYPE_EMAIL:
				rq.getQrq().put("userEmail", form.getEmail());
				user = userMapper.selectUserWithInfoByRq(rq.getQrq());
				break;
			case LoginForm.LOGIN_TYPE_PHONE:
				rq.getQrq().put("userPhone", form.getPhonenum());
				user = userMapper.selectUserWithInfoByRq(rq.getQrq());
				break;
			default:
				res = AuthConstant.WRONG_USER_ACCOUNT_MSG;
				break;
		}
		if(user != null){
			long time = DateUtil.getDateByTime();
			UserInfo info = user.getUserInfo();
			short ec = info.getErrCount();//登录错误次数
			if(ec >= 3){//登录出错的次数超过三次，限制登录24小时
				long stopTime = info.getStopTime();
				int stopExpires = AuthConstant.LOGIN_ERR_STOP_EXPIRES;
				long stop = stopTime + stopExpires;
				
				if(stop > time){
					res = AuthConstant.USER_LOCKED_MSG + "[" + ((stop - time) % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) + "]小时后解锁";//账户被锁定，12小时，
					return res;
				}
			}
			String salt = user.getUserSalt();
			String pwd = user.getUserPwd();
			
			if(Md5Util.md5(form.getPassword() + salt).equals(pwd)){
				int maxAge = new Short((short) 1).equals(form.getRemember()) ? 	AuthConstant.COOKIE_VALIDITY_SECONDS : -1;//保存14天 或者 0
				Integer userId = user.getUserId();
				//修改Info信息
				info.setLoginIp(WebUtil.getIpAddr(request));
				info.setLoginTime(time);
				info.setErrCount((short) 0);
				userInfoMapper.insert(info);
				
				String userLoginInfo = SecretUtil.encrypt(userId + "@" + time + "@" + maxAge + "@" + Md5Util.md5(userId + "@" + pwd + "@" + salt + "@" + time + "@" + maxAge));
				if(WebUtil.getBrowserName(request).equals("webkit"))
					WebUtil.addCookie(response, null, null, true, AuthConstant.COOKIE_USER_INFO, userLoginInfo, maxAge);
				else{
					long expires = DateUtil.getDateByTime() + maxAge * 1000;
					WebUtil.addCookie(response, null, null, true, AuthConstant.COOKIE_USER_INFO, userLoginInfo, expires, maxAge);
				}
				//登录成功
				//将sessionId存放在缓存中
				Cache cache = CacheManager.getInstance().getCache(CacheConstant.LOGIN_USER_INFO_CACHE_NAME);
				cache.put(new Element(userId, user));
			}else{
				res = AuthConstant.WRONG_LOGIN_MSG;
				ec++;
				if(ec > 3)//登录出错次数
					info.setStopTime(time);//限制登录开始时间
				else
					info.setErrCount(ec);
				userInfoMapper.insert(info);
			}
		}else
			res = res == null ? AuthConstant.USER_NOT_FOUND_MSG : res;
		return res;
	}
	
}
