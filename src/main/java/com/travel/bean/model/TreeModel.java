package com.travel.bean.model;

import java.io.Serializable;
import java.util.List;

/**
 * 树形数据结果
 * @author ibett
 *
 */
public class TreeModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private String icon;
	private String par;//参数
	private List<TreeModel> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPar() {
		return par;
	}
	public void setPar(String par) {
		this.par = par;
	}
	public List<TreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}
}
