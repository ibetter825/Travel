package com.travel.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travel.bean.entity.Article;
import com.travel.bean.model.ResultModel;
import com.travel.service.ArticleService;
import com.travel.utils.DateUtil;

/**
 * 首页控制器
 * @author user
 *
 */
@RestController(value = "AdminArticleController")
public class ArticleController extends AdminBaseController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/art/save.json")
	public ResultModel save(Article article){
		System.err.println(article.getArtTitle());
		//获取当前登录用户
		article.setAddTime(DateUtil.getDateByTime());
		article.setAuthorId((long) 10000);
		articleService.save(article);
		return new ResultModel();
	}
}
