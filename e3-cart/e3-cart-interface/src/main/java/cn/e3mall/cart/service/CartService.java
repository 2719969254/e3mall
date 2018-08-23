package cn.e3mall.cart.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;

import java.util.List;

/**
 * 购物车服务
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/23
 */
public interface CartService {
	/**
	 * 添加购物车
	 *
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	E3Result addCart(long userId, long itemId, int num);

	/**
	 * 合并购物车
	 *
	 * @param userId
	 * @param itemList
	 * @return
	 */
	E3Result mergeCart(long userId, List<TbItem> itemList);

	/**
	 * 展示购物车列表
	 *
	 * @param userId
	 * @return
	 */
	List<TbItem> getCartList(long userId);

	/**
	 * 更新购物车
	 *
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	E3Result updateCartNum(long userId, long itemId, int num);

	/**
	 * 删除购物车
	 *
	 * @param userId
	 * @param itemId
	 * @return
	 */
	E3Result deleteCartItem(long userId, long itemId);
}
