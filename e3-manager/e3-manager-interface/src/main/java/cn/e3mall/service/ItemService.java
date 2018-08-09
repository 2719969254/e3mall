package cn.e3mall.service;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

/**
 * @author 田祚
 */
public interface ItemService {
    /**
     * 查询所有商品项
     *
     * @param itemId
     * @return
     */
    TbItem getItemById(long itemId);

    /**
     * 分页
     *
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);
}
