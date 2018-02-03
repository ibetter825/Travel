package com.travel.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章entity
 * @author user
 *
 */
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 标签主键
	 */
	@Id
	private String tagNm;
	/**
	 * 标签的文章数量
	 */
	private Integer tagNum;
	/**
	 * 新增时间
	 */
	private Long addTime;
	
	public Tag() {
		super();
	}
	public Tag(String tagNm, Integer tagNum, Long addTime) {
		this.tagNm = tagNm;
		this.tagNum = tagNum;
		this.addTime = addTime;
	}
	public String getTagNm() {
		return tagNm;
	}
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	public Integer getTagNum() {
		return tagNum;
	}
	public void setTagNum(Integer tagNum) {
		this.tagNum = tagNum;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
}
