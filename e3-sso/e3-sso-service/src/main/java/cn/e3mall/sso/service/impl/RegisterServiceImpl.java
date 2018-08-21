package cn.e3mall.sso.service.impl;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if(type == 1){
			criteria.andUsernameEqualTo(param);
		}else if (type == 2){
			criteria.andPhoneEqualTo(param);
		}else {
			return E3Result.build(400,"输入信息有误");
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
}
