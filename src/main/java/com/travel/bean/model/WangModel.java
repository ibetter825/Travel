package com.travel.bean.model;

import java.io.Serializable;

/**
 * wangEditor上传文件结果返回
 * @author user
 *
 */
public class WangModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码
	 * errno 即错误代码，0 表示没有错误。
     * 如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
	 */
	private Integer errno = 0;
	/**
	 * data 是一个数组，返回若干图片的线上地址
	 */
	private String[] data;
	public Integer getErrno() {
		return errno;
	}
	public WangModel setErrno(Integer errno) {
		this.errno = errno;
		return this;
	}
	public String[] getData() {
		return data;
	}
	public WangModel setData(String[] data) {
		this.data = data;
		return this;
	}
	
}
