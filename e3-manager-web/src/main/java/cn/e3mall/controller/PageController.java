package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: e3
 * @description: 显示后台首页
 * @author: Mr.Tian
 * @version 1.0
 * @date 2018/8/8
 */
@Controller
public class PageController {
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
