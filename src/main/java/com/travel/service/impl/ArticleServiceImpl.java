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
import com.travel.dao.mapper.TagMapper;
import com.travel.service.ArticleService;
import com.travel.task.AsyncTask;

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
		return articleMapper.selectByPrimaryKey(id);
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
