package com.travel.controller.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.travel.annotation.Permission;


/**
 * 文章相关控制器
 * @author user
 *
 */
@RestController
public class ArticleController extends WebBaseController {
	
	@RequestMapping("/{id}.html")
	public ModelAndView index(@PathVariable("id") Long id){
		return new ModelAndView(getView("detail"));
	}
	
	@Permission
	@RequestMapping("/write.html")
	public ModelAndView index(){
		return new ModelAndView(getView("write"));
	}
}
