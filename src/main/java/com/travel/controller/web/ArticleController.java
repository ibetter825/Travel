package com.travel.controller.web;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Maps;
import com.travel.annotation.Permission;
import com.travel.bean.entity.Article;
import com.travel.bean.entity.ArticleCount;
import com.travel.bean.model.ResultModel;
import com.travel.service.ArticleCountService;
import com.travel.service.ArticleService;


/**
 * 文章相关控制器
 * @author user
 *
 */
@RestController
public class ArticleController extends WebBaseController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleCountService articleCountService;
	
	@RequestMapping("/{id}.html")
	public ModelAndView index(@PathVariable("id") Long id){
		Article article = articleService.queryArticle(id);
		if(article == null){
			//跳转到404页面
		}
		Map<String, Article> model = Maps.newHashMap();
		model.put("article", article);
		ArticleCount count = new ArticleCount();
		count.setArtId(id);
		count.setScanCount(1);
		articleCountService.modifyAuto(count);//修改文章的浏览量
		return new ModelAndView(getView("detail"), model);
	}
	
	@Permission
	@RequestMapping("/write.html")
	public ModelAndView index(){
		return new ModelAndView(getView("write"));
	}
	
	/**
	 * 查询文章的统计数据
	 * @param id
	 * @throws IOException 
	 */
	@RequestMapping("/{id}/count.json")
	public void count(@PathVariable("id") Long id) throws IOException{
		ResultModel model = new ResultModel();
		ArticleCount count = articleCountService.query(id);
		model.getData().put("count", count);
		renderJson(model);
	}
}
