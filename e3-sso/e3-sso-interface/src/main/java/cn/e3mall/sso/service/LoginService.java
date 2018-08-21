package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

/**
 * 处理登录
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
public interface LoginService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	E3Result userLogin(String username, String password);
}
