package com.travel.bean.rq;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 查询条件对象
 * @author user
 *
 */
public class QueryRq {
	/**
	 * 查询的参数封到rq中
	 */
	private Map<String, Object> qrq = Maps.newHashMap();
	/**
	 * 排序对象
	 * 使用时需要将or转成为字符串的order以供Pagerhelper使用
	 */
	private Map<String, String> or;
	/**
	 * 排序
	 * eg: menu_id desc, menu_seq desc
	 */
	private String order;
	
	public Map<String, Object> getQrq() {
		return qrq;
	}
	public void setQrq(Map<String, Object> qrq) {
		this.qrq = qrq;
	}
	public Map<String, String> getOr() {
		return or;
	}
	public void setOr(Map<String, String> or) {
		this.or = or;
	}
	public String getOrder() {
		if(this.or != null){
			Iterator<String> it = or.keySet().iterator();
			StringBuffer buffer = null;
			String key = null, val = null;
			while (it.hasNext()) {
				key = it.next();
				val = or.get(key);
				if(buffer == null) buffer = new StringBuffer();
				buffer.append(key + " " + val + ",");
			}
			if(buffer != null)
				this.order = buffer.substring(0, buffer.length() - 1).toString();
		}
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
