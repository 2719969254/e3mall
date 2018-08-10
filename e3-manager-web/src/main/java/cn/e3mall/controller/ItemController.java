package cn.e3mall.controller;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import cn.e3mall.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *  商品controller
 * @author MR.Tian
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 根据id查询商品信息
     * @param itemId 商品id
     * @return TbItem
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    /**
     * 分页显示商品信息
     * @param page 页数
     * @param rows  显示行数
     * @return EasyUIDataGridResult
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //调用服务查询商品列表
        return itemService.getItemList(page, rows);
    }

    /**
     * 添加商品
     * @param tbItem 商品表
     * @param desc 商品描述表
     * @return E3Result
     */
    @RequestMapping(value="/item/save",method= RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(TbItem tbItem,String desc){
        return itemService.addItem(tbItem, desc);
    }
}
