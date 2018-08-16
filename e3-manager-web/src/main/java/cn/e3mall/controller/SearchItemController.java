package cn.e3mall.controller;

import cn.e3mall.search.service.SearchItemService;
import cn.e3mall.common.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 一键导入搜索索引
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/14
 */
@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList() {
		E3Result e3Result = searchItemService.importAllItems();
		return e3Result;

	}
}
