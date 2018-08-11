package cn.e3mall.controller;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: e3
 * @description: 内容管理Controller
 * @author: Mr.Tian
 * @Company: www.stxkfzx.com
 * @Time: 2018/8/11
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 分页显示内容管理数据
	 *
	 * @param categoryId 内容id
	 * @param page       页数
	 * @param rows       行数
	 * @return EasyUIDataGridResult
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Long categoryId, Integer page, Integer rows) {
		return contentService.getItemList(categoryId, page, rows);
	}

	/**
	 * 新增内容管理信息
	 *
	 * @param content 内容管理信息
	 * @return E3Result
	 */
	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content) {
		return contentService.addContent(content);
	}

	/**
	 * 修改内容
	 *
	 * @param content 修改内容pojo
	 * @return E3Result
	 */
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public E3Result editContent(TbContent content) {
		return contentService.editContent(content);
	}

	/**
	 * 回写内容描述
	 *
	 * @param id 内容id
	 * @return TbContent
	 */
	@RequestMapping("/query/content/{id}")
	@ResponseBody
	public TbContent selectContent(@PathVariable Long id) {
		return contentService.selectByIdContent(id);
	}

}
