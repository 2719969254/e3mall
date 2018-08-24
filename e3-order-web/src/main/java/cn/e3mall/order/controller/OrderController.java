package cn.e3mall.order.controller;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单管理
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/24
 */
@Controller
public class OrderController {
	@Autowired
	private CartService cartService;
	@RequestMapping ("/order/order-cart")
	public String showOrderCart(HttpServletRequest request){
		//获取用户id
		TbUser user = (TbUser) request.getAttribute("user");
//		Long id = user.getId();
		//获取用户购物车列表
		List<TbItem> cartList = cartService.getCartList(1);
		//根据用户id获取收货地址列表，取支付方式列表，先使用静态数据
		//回写到页面
		request.setAttribute("cartList",cartList);
		//返回逻辑视图
		return "order-cart";
	}
}
