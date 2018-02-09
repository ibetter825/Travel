package com.winder.controller.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页控制器
 * @author user
 *
 */
@RestController(value = "AdminCommonController")
public class CommonController extends AdminBaseController {
	
	@RequestMapping("/forward/{view}.html")
	public ModelAndView index(@PathVariable("view")	String view){
		return new ModelAndView(getView(view));
	}

}
