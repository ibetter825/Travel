package com.travel.dao.mapper;

import com.travel.bean.entity.ArticleCount;
import com.travel.dao.BaseMapper;

public interface ArticleCountMapper extends BaseMapper<ArticleCount> {
	/**
	 * 自动修改文章数据
	 * @param count
	 * @return
	 */
	public int updateCountAuto(ArticleCount count);
}
