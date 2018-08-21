package cn.e3mall.sso.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis中查询信息
		String json = jedisClient.get("SESSION:" + token);
		//判断用户信息是否过期
		//如果过期
		if(StringUtils.isBlank(json)){
			return E3Result.build(400,"用户登录信息过期");
		}
		//取用户信息转换为对象返回前端
		jedisClient.expire("SESSION"+token,SESSION_EXPIRE);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		return E3Result.ok(tbUser);
	}
}
