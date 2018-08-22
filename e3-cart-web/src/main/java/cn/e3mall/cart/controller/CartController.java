package cn.e3mall.cart.controller;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车处理
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/22
 */
@Controller
public class CartController {
	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_CART_EXPIRE}")
	private Integer COOKIE_CART_EXPIRE;

	/**
	 * 添加商品到购物车
	 *
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
	                      HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取出购物车
		List<TbItem> cartList = getCartListFromCookie(request);
		//判断商品在购物车中是否存在
		boolean flag = false;
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				flag = true;
				tbItem.setNum(tbItem.getNum() + num);
				//跳出循环
				break;
			}
		}
		//如果不存在
		if (!flag) {
			//根据商品id查询商品信息，得到一个item对象，并添加到cookie中
			TbItem itemById = itemService.getItemById(itemId);
			itemById.setNum(num);
			String image = itemById.getImage();
			if (StringUtils.isNotBlank(image)) {
				itemById.setImage(image.split(",")[0]);
			}
			//把商品添加到购物车中
			cartList.add(itemById);
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);

		return "cartSuccess";
	}

	/**
	 * 从cookie中取出购物车的处理
	 *
	 * @param request
	 * @return
	 */
	private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, "cart", true);
		//判断json是否为空，如果为空则依旧返回一个集合
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		//如果不为空则返回带数据的list集合
		return JsonUtils.jsonToList(json, TbItem.class);
	}

	/**
	 * 展示购物车
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String showCatList(HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		//把列表传递给页面
		request.setAttribute("cartList", cartList);
		//返回逻辑视图
		return "cart";
	}
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
	                              HttpServletRequest request , HttpServletResponse response) {
		//从cookie中获取购物车
		List<TbItem> cartList = getCartListFromCookie(request);
		//遍历商品列表找到对应商品
		for (TbItem tbItem : cartList) {
			if (tbItem.getId().longValue() == itemId.longValue()){
				//更新数量
				tbItem.setNum(num);
				break;
			}
		}
		//把购物车列表回写到cookie中
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		//返回成功
		return E3Result.ok();
	}
}
