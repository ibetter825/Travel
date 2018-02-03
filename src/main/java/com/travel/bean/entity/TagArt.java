package com.travel.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章与标签的关联关系
 * @author user
 *
 */
@Entity
@Table(name = "tag_art")
public class TagArt extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 标签主键
	 */
	@Id
	private String tagNm;
	/**
	 * 标签的文章数量
	 */
	private Long artId;
	
	public TagArt() {
	}
	public TagArt(String tagNm, Long artId) {
		this.tagNm = tagNm;
		this.artId = artId;
	}
	public String getTagNm() {
		return tagNm;
	}
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	
}
