package com.travel.bean.entity;
/**
 * 文章entity
 * @author user
 *
 */
public class Article extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 文章id
	 */
	private Integer artId;
	/**
	 * 文章作者
	 */
	private String authorId;
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
	private String artContent;
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
	public Integer getArtId() {
		return artId;
	}
	public void setArtId(Integer artId) {
		this.artId = artId;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
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
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
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
