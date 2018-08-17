package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/15
 */
public class TestSolrCloud {
	@Test
	public void testAddDocument() throws Exception {

		//创建一个集群的连接，使用SolrCloudServer创建
		HttpSolrClient solrServer = new HttpSolrClient.Builder("http://192.168.25.132:8180/solr/collection2")
				.withConnectionTimeout(10000)
				.withSocketTimeout(60000)
				.build();
		//[2]创建文档doc
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", "solrcloud1");
		document.setField("item_title", "cewssss");
		solrServer.add(document);
		solrServer.commit();

	}

	@Test
	public void querySolr() throws Exception {
		//[1]获取连接
		String solrUrl = "http://192.168.25.132:8180/solr/collection2";
		//创建solrClient同时指定超时时间，不指定走默认配置
		HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
				.withConnectionTimeout(10000)
				.withSocketTimeout(60000)
				.build();
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		//执行查询
		QueryResponse response = client.query(query);
		//取出查询结果
		SolrDocumentList results = response.getResults();
		System.out.println(results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("title"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("id"));

		}

	}

	public static final String BASE_URL = "http://192.168.25.132:8180/solr/collection2";

	@Test
	public void test() throws IOException, SolrServerException {
		SolrClient solrClient = new HttpSolrClient.Builder(BASE_URL)
				.withConnectionTimeout(1000)
				.withSocketTimeout(6000)
				.build();
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", "id222");
		document.setField("name", "solrJ-test11111");
		solrClient.add(document);
		solrClient.commit();
	}
}

