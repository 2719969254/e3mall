package cn.e3mall.search.controller;

import cn.e3mall.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @author MR.Tian
 * @version V1.0
 * @Date 2018/8/14
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	@RequestMapping("/search")
	public String searchItemList(String keyword, @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
		try {
			keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查询商品列表
		SearchResult search = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages",search.getTotalPages());
		model.addAttribute("page",page);
		model.addAttribute("recourdCount",search.getRecordCount());
		model.addAttribute("itemList",search.getItemList());
		//返回逻辑视图
		return "search";
	}
}
