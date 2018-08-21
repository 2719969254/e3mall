package cn.e3mall.sso.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * 登录
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public E3Result userLogin(String username, String password) {
		//判断用户和密码是否正确
		//根据用户名查找用户信息
		TbUserExample tbUserExample = new TbUserExample();
		TbUserExample.Criteria criteria = tbUserExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		//执行查询
		List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
		if (tbUsers == null) {
			return E3Result.build(400, "用户名或密码错误");
		}
		//取出用户信息
		TbUser tbUser = tbUsers.get(0);
		//根据用户信息核对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
			//如果密码不正确，则返回登录失败
			return E3Result.build(400,"用户名或密码错误");
		}
		//如果正确生成token
		String token = UUID.randomUUID().toString();
		//将用户信息写入redis，key：token value：用户信息
		tbUser.setPassword(null);
		jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(tbUser));
		//设置过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		return E3Result.ok(token);
	}
}
