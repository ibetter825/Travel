package com.winder.controller.admin;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.winder.annotation.Validator;
import com.winder.bean.constant.AuthConstant;
import com.winder.bean.form.LoginForm;
import com.winder.bean.model.ResultModel;
import com.winder.service.UserService;
import com.winder.utils.Md5Util;
import com.winder.utils.VerifyCodeUtil;
import com.winder.utils.WebUtil;

/**
 * 首页控制器
 * @author user
 *
 */
@RestController(value = "AdminLoginController")
public class LoginController extends AdminBaseController {
	@Autowired
	private UserService userService;
	/**
	 * 进入登录页面
	 * @return
	 */
	@RequestMapping("/login.html")
	public ModelAndView index(){
		return new ModelAndView(getView("login"));
	}
	/**
	 * 执行登录动作
	 * @return
	 */
	@RequestMapping("/login.json")
	@Validator
	public ResultModel login(@Valid LoginForm form, BindingResult binding){
		ResultModel model = null;
		String res = userService.valiLoginUser(request, response, form);
		if(res == null){
			System.err.println("登录成功");
			model = new ResultModel();
			return model;
		}
		System.err.println("登录失败");
		model = new ResultModel(res);
		return model;
	}
	
	/**
	 * 获取验证码
	 * @throws IOException 
	 */
	@RequestMapping("/captcha.jpg")
	public void captcha() throws IOException{
		//需要限制客户端的请求次数，不能无休止的请求
		response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
        //生成随机字串 
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        String captchaString = Md5Util.md5(verifyCode.toUpperCase() + AuthConstant.COOKIE_CAPTCHA_SALT);
        WebUtil.addCookie(response, null, AuthConstant.COOKIE_CAPTCHA_PATH, true, AuthConstant.COOKIE_CAPTCHA_NAME, captchaString, -1);
        //生成图片
        int w = 150, h = 40;
        VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
}
