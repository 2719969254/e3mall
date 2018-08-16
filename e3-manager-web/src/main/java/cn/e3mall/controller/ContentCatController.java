package cn.e3mall.controller;

import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.e3mall.content.service.ContentCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理Controller
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/10
 */
@Controller
public class ContentCatController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 查看分类管理
	 *
	 * @param parentId 父节点id
	 * @return List<EasyUITreeNode>
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
		return contentCategoryService.getContentCatList(parentId);
	}

	/**
	 * 添加分类节点
	 *
	 * @param parentId 父节点id
	 * @param name     叶子节点名字
	 * @return E3Result
	 */
	@RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(Long parentId, String name) {
		return contentCategoryService.addContentCategory(parentId, name);
	}

	/**
	 * 修改节点名称
	 *
	 * @param id   节点id
	 * @param name 修改后名称
	 * @return E3Result
	 */
	@RequestMapping(value = "/content/category/update")
	@ResponseBody
	public E3Result editContentCategory(Long id, String name) {
		return contentCategoryService.editContentCategory(id, name);
	}

	/**
	 * 删除节点
	 *
	 * @param id 被删除节点id
	 * @return E3Result
	 */
	@RequestMapping(value = "/content/category/delete", method = RequestMethod.POST)
	@ResponseBody
	public E3Result deleteContentCategory(Long id) {
		return contentCategoryService.deleteContentCategory(id);
	}
}
