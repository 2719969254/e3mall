package cn.e3mall.item.controller;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/17
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model){
		//调用服务取出基本信息
		TbItem itemById = itemService.getItemById(itemId);
		Item item = new Item(itemById);
		//去商品描述信息
		TbItemDesc itemDescById = itemService.getItemDescById(itemId);
		//信息传递给页面
		model.addAttribute("item",item);
		model.addAttribute("itemDesc",itemDescById);
		return "item";
	}
}
