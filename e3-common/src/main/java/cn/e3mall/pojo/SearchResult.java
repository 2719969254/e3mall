package cn.e3mall.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @Date 2018/8/9
 */
public class SearchResult implements Serializable {

	private static final long serialVersionUID = 5975090274695066182L;
	private long recordCount;
	private int totalPages;
	private List<SearchItem> itemList;

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

}
