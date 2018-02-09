package com.winder.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页控制器
 * @author user
 *
 */
@RestController(value = "AdminIndexController")
public class IndexController extends AdminBaseController {
	
	@RequestMapping("/index.html")
	public ModelAndView index(){
		return new ModelAndView(getView("index"));
	}

}
