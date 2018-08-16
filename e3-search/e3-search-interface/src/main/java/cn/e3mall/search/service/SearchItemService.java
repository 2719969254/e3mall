package cn.e3mall.search.service;

import cn.e3mall.common.utils.E3Result;

/**
 * 一键导入索引接口
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/14
 */
public interface SearchItemService {

	/**
	 * 实现
	 * @return
	 */
	E3Result importAllItems();
}
