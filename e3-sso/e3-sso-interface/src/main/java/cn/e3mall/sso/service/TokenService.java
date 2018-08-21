package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

/**
 * 根据token查询用户信息
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
public interface TokenService {
	E3Result getUserByToken(String token);
}
