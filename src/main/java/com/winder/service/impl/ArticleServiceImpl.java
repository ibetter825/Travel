package com.winder.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winder.bean.entity.Article;
import com.winder.bean.rq.PagerRq;
import com.winder.bean.rq.QueryRq;
import com.winder.dao.mapper.ArticleMapper;
import com.winder.dao.mapper.TagMapper;
import com.winder.service.ArticleService;
import com.winder.task.AsyncTask;

@Service
public class ArticleServiceImpl implements ArticleService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private AsyncTask task;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private TagMapper tagMapper;
	
	@Override
	public Article save(Article article) {
		articleMapper.insertSelectiveUseGeneratedKeys(article);
		System.err.println("生成的文章ID:" + article.getArtId());
		try {
			task.doTaskArtExtra(article);//异步处理文章标签相关问题
		} catch (InterruptedException e) {
			//如果报错先不做处理
			e.printStackTrace();
		}
		return article;
	}
	
	@Override
	public Article queryArticle(Long id) {
		Article article = articleMapper.selectByPrimaryKey(id);
		try {
			task.doTaskArtCountExtra(article);//异步处理文章标签相关问题
		} catch (InterruptedException e) {
			//如果报错先不做处理
			e.printStackTrace();
		}
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
