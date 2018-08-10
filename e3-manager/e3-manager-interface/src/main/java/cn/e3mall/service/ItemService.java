package cn.e3mall.service;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.utils.E3Result;

/**
 * @author 田祚
 */
public interface ItemService {
    /**
     * 查询商品项
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
     * 添加商品接口
     *
     * @param tbItem 商品项
     * @param desc   商品描述
     * @return E3Result
     */
    E3Result addItem(TbItem tbItem, String desc);

    /**
     * 查询商品描述信息
     *
     * @param id 商品id
     * @return tbItem
     */
    TbItemDesc getItemDescById(long id);

    /**
     * 批量删除商品
     * @param ids 批量商品id
     * @return E3Result
     */
    E3Result deleteItems(String ids);

    /**
     * 批量上架产品
     * @param ids 批量商品id
     * @return E3Result
     */
    E3Result groundingItem(String ids);

    /**
     * 批量下架产品
     * @param ids 批量商品id
     * @return E3Result
     */
    E3Result soldOutItem(String ids);


}
