package com.winder.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页控制器
 * @author user
 *
 */
@RestController
public class IndexController extends WebBaseController {
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView(getView("index"));
	}

}
