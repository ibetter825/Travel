package com.winder.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.winder.controller.BaseController;

/**
 * 后台管理 controller
 * @author user
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminBaseController extends BaseController {
	@Override
	public String getView(String view) {
		return VIEW_ADMIN_BASE_ROUTE + view;
	}
	
}
