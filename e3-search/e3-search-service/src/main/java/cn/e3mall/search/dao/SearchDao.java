package cn.e3mall.search.dao;

import cn.e3mall.pojo.SearchItem;
import cn.e3mall.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索Dao
 *
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/14
 */
@Repository
public class SearchDao {

	public SearchResult search(SolrQuery query) throws Exception {
		//根据query查询索引库
		String solrUrl = "http://192.168.25.132:8180/solr/collection2";
		//创建solrClient同时指定超时时间，不指定走默认配置
		HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
				.withConnectionTimeout(10000)
				.withSocketTimeout(60000)
				.build();
		//创建solrClient同时指定超时时间，不指定走默认配置
		QueryResponse queryResponse = client.query(query);
		//取查询结果
		SolrDocumentList solrDocuments = queryResponse.getResults();
		//取出查询结果总记录数
		long numFound = solrDocuments.getNumFound();
		//放入返回对象中
		SearchResult searchResult = new SearchResult();
		searchResult.setRecordCount(numFound);
		//取出商品列表，并需要高亮显示
		//设置高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocuments) {
			SearchItem item = new SearchItem();
			item.setId((String) solrDocument.get("id"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			//高亮显示
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			itemList.add(item);
		}
		searchResult.setItemList(itemList);
		return searchResult;
	}
}
