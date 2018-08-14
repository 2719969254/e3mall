package cn.e3mall.search.service.impl;

import cn.e3mall.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MR.Tian
 * @version V1.0
 * @Date 2018/8/14
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//创建一个SolrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(keyword);
		//设置分页
		if (page <= 0) {
			page = 1;
		}
		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);
		//设置默认搜索域
		solrQuery.set("df", "item_title");
		//开启高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style = \"color:red\" >");
		solrQuery.setHighlightSimplePost("</em>");
		//调用dao查询
		SearchResult searchResult = searchDao.search(solrQuery);
		//计算总页数
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int) (recordCount / rows);
		if (recordCount % rows > 0) {
			totalPage ++;
		}
		searchResult.setTotalPages(totalPage);
		return searchResult;
	}
}
