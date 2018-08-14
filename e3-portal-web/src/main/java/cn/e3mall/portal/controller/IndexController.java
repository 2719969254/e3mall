package cn.e3mall.portal.controller;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: e3
 * @description: 展示首页
 * @author: Mr.Tian
 * @Company： www.stxkfzx.com
 * @date 2018/8/10
 */
@Controller
public class IndexController {
	/**
	 * 轮播图内容分类
	 */
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	@Autowired
	private ContentService contentService;

	/**
	 * 显示首页
	 *
	 * @param model 网页model
	 * @return List<TbContent>
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//查询内容列表
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		model.addAttribute("ad1List", ad1List);
		return "index";
	}
}
