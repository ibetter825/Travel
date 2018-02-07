package com.travel.service;

import java.util.List;
import java.util.Map;
import com.travel.bean.entity.Article;
import com.travel.bean.rq.PagerRq;
import com.travel.bean.rq.QueryRq;

public interface ArticleService {
	/**
	 * 保存新的文章
	 * @param article
	 * @return
	 * @throws InterruptedException 
	 */
	public Article save(Article article);
	/**
	 * 根据id查询文章
	 * @param id
	 * @return
	 */
	public Article queryArticle(Long id);
	/**
	 * 根据条件查询文章集合
	 * @param rq
	 * @return
	 */
	public List<Map<?, ?>> queryForMapList(QueryRq rq);
	/**
	 * 分页查询文章map集合
	 * @param rq
	 * @param pager
	 * @return
	 */
	public List<Map<?, ?>> queryForMapList(QueryRq rq, PagerRq pager);
}
