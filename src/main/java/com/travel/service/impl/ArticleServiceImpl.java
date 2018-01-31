package com.travel.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.entity.Article;
import com.travel.bean.rq.PagerRq;
import com.travel.bean.rq.QueryRq;
import com.travel.dao.mapper.ArticleMapper;
import com.travel.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public Article save(Article article) {
		articleMapper.insertSelectiveUseGeneratedKeys(article);
		System.err.println("生成的文章ID:" + article.getArtId());
		return article;
	}
	
	@Override
	public List<Map<?, ?>> queryForMapList(QueryRq rq) {
		return articleMapper.selectListByRq(rq.getQrq());
	}
	
	@Override
	public List<Map<?, ?>> queryForMapList(QueryRq rq, PagerRq pager){
		return articleMapper.selectListByRqAndPager(rq.getQrq(), pager);
	}
	
}
