package com.winder.service;

import com.winder.bean.entity.ArticleCount;

public interface ArticleCountService {
	/**
	 * 保存
	 * @param count
	 * @return
	 */
	public int save(ArticleCount count);
	/**
	 * 查询文章的article count
	 * @param artId
	 * @return
	 */
	public ArticleCount query(Long artId);
	/**
	 * 修改文章数据
	 * @param count
	 * @return
	 */
	public int modify(ArticleCount count);
	/**
	 * 自动增见文章数据
	 * @param count
	 * @return
	 */
	public int modifyAuto(ArticleCount count);
}
