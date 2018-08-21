package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

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
	 * @param name
	 * @param type
	 * @return
	 */
	E3Result checkData(String name,int type);
}
