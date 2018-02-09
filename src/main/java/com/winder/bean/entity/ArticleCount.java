package com.winder.bean.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章相关数据统计
 * @author user
 *
 */
@Entity
@Table(name = "article_count")
public class ArticleCount extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	private Long artId;
	private Integer likeCount;
	private Integer comtCount;
	private Integer scanCount;
	private Integer shareCount;
	
	public ArticleCount() {
	}
	public ArticleCount(Long artId) {
		this.artId = artId;
	}
	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getComtCount() {
		return comtCount;
	}
	public void setComtCount(Integer comtCount) {
		this.comtCount = comtCount;
	}
	public Integer getScanCount() {
		return scanCount;
	}
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
}
