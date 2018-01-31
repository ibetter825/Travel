package com.travel.bean.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 * @author user
 *
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String userPwd;
	private String userSalt;
	private Long userPhone;
	private String userEmail;
	private Short userStatus;
	private UserInfo info;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public UserInfo getInfo() {
		return info;
	}
	public void setInfo(UserInfo info) {
		this.info = info;
	}
}
