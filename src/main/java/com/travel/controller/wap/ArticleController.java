package com.travel.controller.wap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页控制器
 * @author user
 *
 */
@RestController
public class ArticleController extends WebBaseController {
	
	@RequestMapping("/{id}.html")
	public ModelAndView index(@PathVariable("id") Long id){
		return new ModelAndView("wap/detail");
	}

}
