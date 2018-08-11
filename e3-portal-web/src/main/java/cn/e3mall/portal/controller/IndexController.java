package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: e3
 * @description: 展示首页
 * @author:Mr.Tian
 * @Company： www.stxkfzx.com
 * @Time: 2018/8/1018:12
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
