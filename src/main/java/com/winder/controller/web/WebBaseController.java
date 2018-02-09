package com.winder.controller.web;

import org.springframework.web.bind.annotation.RestController;

import com.winder.controller.BaseController;

/**
 * pc controller
 * @author user
 *
 */
@RestController
public class WebBaseController extends BaseController {

	@Override
	public String getView(String view) {
		return VIEW_WEP_BASE_ROUTE + view;
	}
}
