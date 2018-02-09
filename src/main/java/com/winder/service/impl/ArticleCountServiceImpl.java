package com.winder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winder.bean.entity.ArticleCount;
import com.winder.dao.mapper.ArticleCountMapper;
import com.winder.service.ArticleCountService;

@Service
public class ArticleCountServiceImpl implements ArticleCountService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ArticleCountServiceImpl.class);
	@Autowired
	private ArticleCountMapper countMapper;
	
	@Override
	public int save(ArticleCount count) {
		return countMapper.insertSelective(count);
	}
	
	@Override
	public ArticleCount query(Long artId) {
		return countMapper.selectByPrimaryKey(artId);
	}

	@Override
	public int modify(ArticleCount count) {
		return countMapper.updateByPrimaryKeySelective(count);
	}

	@Override
	public int modifyAuto(ArticleCount count) {
		return countMapper.updateCountAuto(count);
	}
}
