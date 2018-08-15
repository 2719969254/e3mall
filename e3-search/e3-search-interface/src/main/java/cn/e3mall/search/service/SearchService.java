package cn.e3mall.search.service;


import cn.e3mall.pojo.SearchResult;

public interface SearchService {

	/**
	 * @param keyword
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	SearchResult search(String keyword, int page, int rows)  throws Exception;
}
