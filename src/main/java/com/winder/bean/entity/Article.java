package com.winder.bean.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章entity
 * @author user
 *
 */
@Entity
@Table(name = "article")
public class Article extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 文章id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artId;
	/**
	 * 文章作者
	 */
	private Long authorId;
	/**
	 * 文章状态
	 */
	private Short artStatus;
	/**
	 * 文章标题
	 */
	private String artTitle;
	/**
	 * 文章背景音乐
	 */
	private String artSong;
	/**
	 * 文章概要
	 */
	private String artDesc;
	/**
	 * 文章封面
	 */
	private String artCover;//封面
	/**
	 * 文章图片
	 */
	private String artImgs;
	/**
	 * 文章标签
	 */
	private String artTags;
	/**
	 * 文章内容
	 */
	private String artCont;
	/**
	 * 文章新建时间
	 */
	private Long addTime;
	/**
	 * 文章编辑时间
	 */
	private Long editTime;
	/**
	 * 文章软删除时间
	 */
	private Long delTime;
	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public Short getArtStatus() {
		return artStatus;
	}
	public void setArtStatus(Short artStatus) {
		this.artStatus = artStatus;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtSong() {
		return artSong;
	}
	public void setArtSong(String artSong) {
		this.artSong = artSong;
	}
	public String getArtDesc() {
		return artDesc;
	}
	public void setArtDesc(String artDesc) {
		this.artDesc = artDesc;
	}
	public String getArtCover() {
		return artCover;
	}
	public void setArtCover(String artCover) {
		this.artCover = artCover;
	}
	public String getArtImgs() {
		return artImgs;
	}
	public void setArtImgs(String artImgs) {
		this.artImgs = artImgs;
	}
	public String getArtTags() {
		return artTags;
	}
	public void setArtTags(String artTags) {
		this.artTags = artTags;
	}
	public String getArtCont() {
		return artCont;
	}
	public void setArtCont(String artCont) {
		this.artCont = artCont;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public Long getEditTime() {
		return editTime;
	}
	public void setEditTime(Long editTime) {
		this.editTime = editTime;
	}
	public Long getDelTime() {
		return delTime;
	}
	public void setDelTime(Long delTime) {
		this.delTime = delTime;
	}
	
}
