package com.winder.dao.mapper;

import com.winder.bean.entity.ArticleCount;
import com.winder.dao.BaseMapper;

public interface ArticleCountMapper extends BaseMapper<ArticleCount> {
	/**
	 * 自动修改文章数据
	 * @param count
	 * @return
	 */
	public int updateCountAuto(ArticleCount count);
}
