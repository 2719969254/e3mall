package cn.e3mall.sso.controller;

import cn.e3mall.common.utils.E3Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册功能
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
@Controller
public class RegitsterController {

	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkData(@PathVariable String param, @PathVariable int type){

		return null;
	}
}
