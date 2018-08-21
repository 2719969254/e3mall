package cn.e3mall.sso.service.impl;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户注册实现
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/21
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public E3Result checkData(String param, int type) {
		//根据不同情况生成不同的查询条件
		TbUserExample tbUserExample = new TbUserExample();
		TbUserExample.Criteria criteria = tbUserExample.createCriteria();
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else {
			return E3Result.build(400, "输入信息有误");
		}
		// 执行查询
		List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
		//如果查询结果不为空
		if (tbUsers != null && tbUsers.size() > 0) {
			return E3Result.ok(false);
		}
		//如果查询结果为空，则说明用户的数据可用
		return E3Result.ok(true);
	}

	@Override
	public E3Result register(TbUser tbUser) {
		//数据合理性校验
		if (StringUtils.isBlank(tbUser.getUsername()) || StringUtils.isBlank(tbUser.getPassword()) || StringUtils.isBlank(tbUser.getPhone())){
			return E3Result.build(400,"用户数据不完整，注册失败");
		}
		E3Result result = checkData(tbUser.getUsername(), 1);
		if (!(Boolean) result.getData()){
			return E3Result.build(400,"此用户名已经被占用");
		}
		result = checkData(tbUser.getPhone(), 2);
		if (!(Boolean) result.getData()){
			return E3Result.build(400,"此手机号已经被占用");
		}
		//补全pojo属性
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//对密码进行md5加密
		String md5pass = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
		tbUser.setPassword(md5pass);
		//将用户信息存储入数据库中
		tbUserMapper.insert(tbUser);
		return E3Result.ok();
	}
}
