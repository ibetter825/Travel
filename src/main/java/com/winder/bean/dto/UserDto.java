package com.winder.bean.dto;

import com.winder.bean.entity.User;
import com.winder.bean.entity.UserInfo;

public class UserDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userPwd;
	private String userSalt;
	private Long userPhone;
	private String userEmail;
	private Short userStatus;
	private String userAvatar;
	private String userIntro;
	private String nickName;
	private Short userSex;
	private Long loginTime;
	private String loginIp;
	private Long regTime;
	private Short errCount;
	private Long stopTime;
	public User getUser(){
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPhone(userPhone);
		user.setUserPwd(userPwd);
		user.setUserSalt(userSalt);
		user.setUserStatus(userStatus);
		return user;
	}
	public UserInfo getUserInfo(){
		UserInfo info = new UserInfo();
		info.setErrCount(errCount);
		info.setLoginIp(loginIp);
		info.setLoginTime(loginTime);
		info.setNickName(nickName);
		info.setRegTime(regTime);
		info.setStopTime(stopTime);
		info.setUserAvatar(userAvatar);
		info.setUserId(userId);
		info.setUserIntro(userIntro);
		info.setUserSex(userSex);
		return info;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public Long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Short getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Short userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getUserIntro() {
		return userIntro;
	}
	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Short getUserSex() {
		return userSex;
	}
	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}
	public Long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Long getRegTime() {
		return regTime;
	}
	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}
	public Short getErrCount() {
		return errCount;
	}
	public void setErrCount(Short errCount) {
		this.errCount = errCount;
	}
	public Long getStopTime() {
		return stopTime;
	}
	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}
}
