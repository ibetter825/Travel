package com.travel.service;

import com.travel.bean.entity.ArticleCount;

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
}
