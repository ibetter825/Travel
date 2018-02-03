package com.travel.task;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.travel.bean.entity.Article;
import com.travel.bean.entity.Tag;
import com.travel.bean.entity.TagArt;
import com.travel.dao.mapper.TagArtMapper;
import com.travel.dao.mapper.TagMapper;
import com.travel.utils.DateUtil;

@Component
public class AsyncTask {
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TagArtMapper tagArtMapper;
	
	@Async("taskAsyncPool")
    public void doTaskArtTag(Article article) throws InterruptedException{
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
}
