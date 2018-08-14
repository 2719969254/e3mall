package cn.e3mall.search.mapper;


import cn.e3mall.pojo.SearchItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MR.Tian
 */
@Repository
public interface ItemMapper {

	/**
	 * 哈哈
	 * @return
	 */
	List<SearchItem> getItemList();
}
