package com.winder.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 * @author user
 *
 */
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer userId;
	private String userAvatar;
	private String userIntro;
	private String nickName;
	private Short userSex;
	private Long loginTime;
	private String loginIp;
	private Long regTime;
	private Short errCount;
	private Long stopTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
