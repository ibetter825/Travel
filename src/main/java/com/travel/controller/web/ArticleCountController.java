package com.travel.controller.web;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travel.bean.entity.ArticleCount;
import com.travel.bean.model.ResultModel;
import com.travel.service.ArticleCountService;


/**
 * 文章相关数据控制器
 * @author user
 *
 */
@RestController
public class ArticleCountController extends WebBaseController {

	@Autowired
	private ArticleCountService articleCountService;
	
	/**
	 * 查询文章的统计数据
	 * @param id
	 * @throws IOException 
	 */
	@RequestMapping("/{id}/count.json")
	public void getCount(@PathVariable("id") Long id) throws IOException{
		ResultModel model = new ResultModel();
		ArticleCount count = articleCountService.query(id);
		model.getData().put("count", count);
		renderJson(model);
	}
	
	/**
	 * 自动增加一个count数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/art/count.json")
	public ResultModel addCount(ArticleCount count){
		ResultModel result = null;
		int r = articleCountService.modifyAuto(count);
		if(r == 1)
			result = new ResultModel();
		else
			result = new ResultModel("操作失败");
		return result;
	}
}
