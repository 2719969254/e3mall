package cn.e3mall.controller;

import cn.e3mall.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.e3mall.content.service.ContentCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: e3
 * @description: 内容分类管理Controller
 * @author:Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/10/20:28
 */
@Controller
public class ContentCatController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
		return contentCategoryService.getContentCatList(parentId);
	}

}
