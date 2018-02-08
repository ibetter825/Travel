package com.travel.task;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.travel.bean.entity.Article;
import com.travel.bean.entity.ArticleCount;
import com.travel.bean.entity.Tag;
import com.travel.bean.entity.TagArt;
import com.travel.dao.mapper.ArticleCountMapper;
import com.travel.dao.mapper.TagArtMapper;
import com.travel.dao.mapper.TagMapper;
import com.travel.utils.DateUtil;

@Component
public class AsyncTask {
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TagArtMapper tagArtMapper;
	@Autowired
	private ArticleCountMapper articleCountMapper;
	
	/**
	 * 新增文章时的额外操作
	 * @param article
	 * @throws InterruptedException
	 */
	@Async("taskAsyncPool")
    public void doTaskArtExtra(Article article) throws InterruptedException{
		//初始化文章统计数据
		articleCountMapper.insertSelective(new ArticleCount(article.getArtId()));
		//这里面的操作可以放在数据库中，使用触发器
		String artTags = article.getArtTags();
		if(StringUtils.isEmpty(artTags)) return;
		String[] arr = artTags.split(",");
		Tag tag = null;
		Long time = DateUtil.getDateByTime();
		for (String nm : arr) {
			tag = new Tag(nm, 1, time);
			if(tagMapper.insertIgnore(tag) == 0){//如果添加不成功
				tagMapper.updateTagNumber(tag);//这里如果出错怎么办，先不管
			}
			//添加文章与tag的关联
			tagArtMapper.insert(new TagArt(tag.getTagNm(), article.getArtId()));//添加失败后怎么办，先不管
		}
    }
	/**
	 * 浏览文章时的额外操作
	 * @param article
	 * @throws InterruptedException
	 */
	@Async("taskAsyncPool")
    public void doTaskArtCountExtra(Article article) throws InterruptedException{
		ArticleCount count = new ArticleCount();
		count.setArtId(article.getArtId());
		count.setScanCount(1);
		articleCountMapper.updateCountAuto(count);//修改文章的浏览量
    }
}
