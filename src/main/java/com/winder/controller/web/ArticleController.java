package com.winder.controller.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Maps;
import com.winder.annotation.Permission;
import com.winder.bean.entity.Article;
import com.winder.service.ArticleService;


/**
 * 文章相关控制器
 * @author user
 *
 */
@RestController
public class ArticleController extends WebBaseController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/{id}.html")
	public ModelAndView index(@PathVariable("id") Long id){
		Article article = articleService.queryArticle(id);
		if(article == null){
			//跳转到404页面
		}
		Map<String, Article> model = Maps.newHashMap();
		model.put("article", article);
		return new ModelAndView(getView("detail"), model);
	}
	
	@Permission
	@RequestMapping("/write.html")
	public ModelAndView index(){
		return new ModelAndView(getView("write"));
	}
}
