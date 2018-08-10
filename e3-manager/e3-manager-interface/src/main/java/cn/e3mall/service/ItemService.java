package cn.e3mall.service;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.utils.E3Result;

/**
 * @author 田祚
 */
public interface ItemService {
    /**
     * 查询所有商品项
     *
     * @param itemId 商品订单
     * @return TbItem
     */
    TbItem getItemById(long itemId);

    /**
     * 分页
     *
     * @param page 总页数
     * @param rows 行数
     * @return EasyUIDataGridResult
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     *  添加商品接口
     * @param tbItem 商品项
     * @param desc 商品描述
     * @return E3Result
     */
    E3Result addItem(TbItem tbItem,String desc);
}
