package cn.e3mall.search.service;


import cn.e3mall.pojo.SearchResult;

/**
 * 搜索
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/14
 */
public interface SearchService {

	/**
	 * 首页搜索
	 *
	 * @param keyword 搜索内容
	 * @param page    当前页数
	 * @param rows    每页展示条数
	 * @return SearchResult
	 * @throws Exception
	 */
	SearchResult search(String keyword, int page, int rows) throws Exception;
}
