package com.winder.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winder.bean.entity.Article;
import com.winder.bean.model.PageModel;
import com.winder.bean.model.ResultModel;
import com.winder.bean.rq.PagerRq;
import com.winder.bean.rq.QueryRq;
import com.winder.service.ArticleService;
import com.winder.utils.DateUtil;

/**
 * 首页控制器
 * @author user
 *
 */
@RestController(value = "AdminArticleController")
public class ArticleController extends AdminBaseController {
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 保存文章
	 * @param article
	 * @return
	 */
	@RequestMapping("/art/save.json")
	public ResultModel save(Article article){
		System.err.println(article.getArtTitle());
		//获取当前登录用户
		article.setAddTime(DateUtil.getDateByTime());
		article.setAuthorId((long) 10000);
		articleService.save(article);
		return new ResultModel();
	}
	/**
	 * 查询文章列表
	 * @param pager
	 * @param rq
	 * @return
	 */
	@RequestMapping("/art/list.json")
	public PageModel list(PagerRq page, QueryRq rq){
		Page<Map<?, ?>> pager = PageHelper.startPage(page.getPage(), page.getSize());
		PageHelper.orderBy(page.getOrder());
		articleService.queryForMapList(rq, page);
		return new PageModel(pager);
	}
}
