package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;

/**
 * 用户注册
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
public interface RegisterService {
	/**
	 * 检验用户数据可用性
	 * @param name 检验的数据
	 * @param type 检验的类型 1：用户名 2：手机号
	 * @return E3Result
	 */
	E3Result checkData(String name,int type);

	/**
	 * 实现注册
	 * @param tbUser 注册用户数据
	 * @return E3Result
	 */
	E3Result register(TbUser tbUser);
}
